package com.prudential.services;


import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.prudential.model.Param;
import com.prudential.utils.Exceptions;
import com.prudential.utils.Utils;

public class CouchbaseController extends CouchbaseConnection {

	public CouchbaseController() {

	}

//	public CouchbaseController(AppConfig appConfig) {
//		super(appConfig);
//	}

	public String getAuthDocFromBucketError(String username) {
		try {
			String auth = "";
			getConnection();
			N1qlQueryResult result = bucket.query(N1qlQuery
					.simple("select * from `Error` use keys 'RESUBMISSION::USER_" + username.toUpperCase() + "'"));
			for (N1qlQueryRow row : result) {
				auth = new JsonParser().parse(row.toString()).getAsJsonObject().get("Error").getAsJsonObject()
						.get("password").getAsString();
			}
			closeConnection(bucket);
			return auth;
		} catch (Exception e) {
			throw new Exceptions(e.getMessage());
		}
//		return new JsonParser().parse(HttpUtils.getCbDataUserFromRest(username)).getAsJsonObject().get("result")
//				.getAsJsonArray().get(0).getAsJsonObject().get("password").getAsString();

	}

	public String getErrorDocFromBucketError() {
		try {
			JsonArray ret = new JsonArray();
			getConnection();
			N1qlQueryResult result = bucket.query(N1qlQuery.simple(
					"select meta().id,* from `Error` where meta().id not like 'RESUBMISSION::USER%' and TIMESTAMPS > '"
							+ Utils.getDate7Ago() + "' "));
			for (N1qlQueryRow rows : result) {
				JsonObject row = new JsonParser().parse(rows.toString()).getAsJsonObject();
				JsonObject add = row.get("Error").getAsJsonObject();
				add.addProperty("meta_id", row.get("id").getAsString());
				ret.add(add);
			}
			return ret.toString();
		} catch (Exception e) {
			throw new Exceptions(e.getMessage());
		}

	}

	public JsonObject getErrorDocFromBucketErrorByDocId(String docId) {
		try {
			JsonObject add = null;
			getConnection();
			N1qlQueryResult result = bucket
					.query(N1qlQuery.simple("select meta().id,* from `Error` use keys '" + docId + "'"));
			for (N1qlQueryRow rows : result) {
				JsonObject row = new JsonParser().parse(rows.toString()).getAsJsonObject();
				add = row.get("Error").getAsJsonObject();
				add.addProperty("meta_id", row.get("id").getAsString());
			}
			closeConnection(bucket);
			return add;
		} catch (Exception e) {
			throw new Exceptions(e.getMessage());
		}

	}

	public Boolean upsertDocToBucketError(JsonObject document) {
		Boolean ret = false;
		try {
			getConnection();
			String id = document.get("meta_id").getAsString();
			document.remove("meta_id");
			JsonDocument doc = JsonDocument.create(id,
					com.couchbase.client.java.document.json.JsonObject.fromJson(document.toString()));
			bucket.upsert(doc);
			closeConnection(bucket);
			ret = true;
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exceptions(e.getMessage());
		}
	}

	public String getErrorDocFromBucketErrorByDate(String body) {
		try {
			String startDate = new JsonParser().parse(body).getAsJsonObject().get("startDate").getAsString();
			String endDate = new JsonParser().parse(body).getAsJsonObject().get("endDate").getAsString();
			JsonObject add = null;
			JsonArray arrData = new JsonArray();
			getConnection();
			N1qlQueryResult result = bucket.query(N1qlQuery.simple(
					"select meta().id,* from `Error` where meta().id not like 'RESUBMISSION::USER%' and TIMESTAMPS BETWEEN '"
							+ startDate + "' AND '" + endDate + " 23:59:59.999' order by TIMESTAMPS desc"));

			for (N1qlQueryRow rows : result) {
				JsonObject row = new JsonParser().parse(rows.toString()).getAsJsonObject();
				add = row.get("Error").getAsJsonObject();
				add.addProperty("meta_id", row.get("id").getAsString());
				arrData.add(add);

			}
			return arrData.toString();
		} catch (Exception e) {
			throw new Exceptions(e.getMessage());
		}

	}

}
