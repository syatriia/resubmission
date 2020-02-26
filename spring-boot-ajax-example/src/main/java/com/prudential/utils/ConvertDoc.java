package com.prudential.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConvertDoc {
	
	List<String> listDeleteDoc = new ArrayList<String>();
	
	public ConvertDoc() {
		listDeleteKeyJson();
	}
	
	public String convertDocErrorToDebeziumFormat(String doc) throws ParseException {
		long plus7Hours = 25200000;
		JsonObject finalJsonObject = new JsonObject();
//		JsonObject obj = new JsonParser().parse(getDocStreamSource()).getAsJsonObject();
		JsonObject obj = new JsonParser().parse(doc).getAsJsonObject();
		if (obj.get("payload") != null) {
			finalJsonObject.add("payload", obj.get("payload"));
			return finalJsonObject.toString();
		} else {
			JsonObject payload = new JsonObject();
			JsonObject source = new JsonObject();
//			JsonObject body = obj.get("doc").getAsJsonObject();
			String tableName = obj.get("SRC_TABLE_NAME").getAsString();
			String timestamps = obj.get("DEBEZIUM_TIMESTAMP").getAsString();
			String ops = obj.get("SRC_CDC_OPERATION").getAsString();
			if (ops.equals("REFRESH")) {
				ops = "r";
			} else if (ops.equals("INSERT")) {
				ops = "c";
			} else if (ops.equals("UPDATE")) {
				ops = "u";
			} else if (ops.equals("DELETE")) {
				ops = "d";
			}

			for (String key : listDeleteDoc) {
				obj.remove(key);
			}
			
			SimpleDateFormat dfWithMs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Pattern lDatePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
			Pattern lTimestampPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}(\\.\\d{1,})?");

			for (Entry<String, JsonElement> data : obj.entrySet()) {
				if (lDatePattern.matcher(obj.get(data.getKey()).getAsString()).matches()) {
					obj.addProperty(data.getKey(), df.parse(data.getValue().getAsString()).getTime() + plus7Hours);
				} else if (lTimestampPattern.matcher(obj.get(data.getKey()).getAsString()).matches()) {
					obj.addProperty(data.getKey(),
							dfWithMs.parse(data.getValue().getAsString()).getTime() + plus7Hours);
				} else if (obj.get(data.getKey()).getAsString().isEmpty()) {
					obj.add(data.getKey(), null);
				} else {
//					System.out.println(data.getKey() + " : " + data.getValue());
//					System.out.println("GA MATCH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}

			}
			if (ops.equals("d")) {
				payload.add("before", obj);
				payload.add("after", null);
			} else {
				payload.add("before", null);
				payload.add("after", obj);
			}
			source.addProperty("table", tableName.toLowerCase());
			payload.add("source", source);
			payload.addProperty("op", ops);
			payload.addProperty("ts_ms", dfWithMs.parse(timestamps).getTime() + plus7Hours);
			finalJsonObject.add("payload", payload);
			return finalJsonObject.toString();

		}
	}
	
	public void listDeleteKeyJson() {
		listDeleteDoc.add("ENTITY_TYPE");
		listDeleteDoc.add("SRC_TABLE_NAME");
		listDeleteDoc.add("DEBEZIUM_TIMESTAMP");
		listDeleteDoc.add("SRC_SYSTEM");
		listDeleteDoc.add("SRC_CDC_OPERATION");
		listDeleteDoc.add("SRC_TXN_TIMESTAMP");
		listDeleteDoc.add("SRC_CHANGE_NUM");
	}

}
