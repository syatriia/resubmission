package com.prudential.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.prudential.model.Param;
import com.prudential.services.CouchbaseController;
import com.prudential.services.Kafka;
import com.prudential.utils.AppConfig;
import com.prudential.utils.ConvertDoc;
import com.prudential.utils.ErrorHandling;
import com.prudential.utils.LogApp;
import com.prudential.utils.Utils;

@Controller
public class HomeController {

	Gson gson = new GsonBuilder().serializeNulls().create();

//	String pathConfig = appConfig.getPathConfig();

	LogApp LogApp = new LogApp(HomeController.class, "resubmission");

	@GetMapping("/home")
	public String home() {

		return "home";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getErrorDoc() {
		return new ResponseEntity<Object>(new CouchbaseController().getErrorDocFromBucketError(), HttpStatus.OK);

	}

	@RequestMapping(value = "/query/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getErrorDocByDocID(@PathVariable String id) {
		try {
			return new ResponseEntity<Object>(
					new CouchbaseController().getErrorDocFromBucketErrorByDocId(id).toString(), HttpStatus.OK);
		} catch (Exception e) {
			LogApp.writeLogApp(e.getMessage());
			ErrorHandling errorResponse = new ErrorHandling();
			errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			errorResponse.setMessage("Failed Get doc by Id");
			errorResponse.setDebugMessage(e.getMessage());
			return new ResponseEntity<Object>(gson.toJson(errorResponse), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/query/date", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getErrorDocByMultipleParam(@RequestBody String body) {
		try {
			return new ResponseEntity<Object>(new CouchbaseController().getErrorDocFromBucketErrorByDate(body),
					HttpStatus.OK);
		} catch (Exception e) {
			LogApp.writeLogApp(e.getMessage());
			ErrorHandling errorResponse = new ErrorHandling();
			errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			errorResponse.setMessage("Failed Get doc by Id");
			errorResponse.setDebugMessage(e.getMessage());
			return new ResponseEntity<Object>(gson.toJson(errorResponse), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/push/kafka", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> sendDocumentToKafka(@RequestBody String body) {
		try {
			String ret = "";
			Kafka kafka = new Kafka();
			ConvertDoc convertDoc = new ConvertDoc();
			CouchbaseController cbCon = new CouchbaseController();
			System.out.println("body: "+body);
			String id = new JsonParser().parse(body).getAsJsonObject().get("id").getAsString();
			String startDate = new JsonParser().parse(body).getAsJsonObject().get("startDate").getAsString();
			String endDate = new JsonParser().parse(body).getAsJsonObject().get("endDate").getAsString();
			System.out.println("id: "+id);
			JsonObject data = new CouchbaseController().getErrorDocFromBucketErrorByDocId(id);
			String topic = null, message = null;
//			if (data.get("state").getAsString().equals("StreamSource")) {
			if (data.get("type").getAsString().equals("SME")) {
				topic = "CustomerSME";
			} else if (data.get("type").getAsString().equals("PruCare")) {
				topic = "Customer";
			}
//			}
//			else {
//				topic = data.get("type").getAsString().toLowerCase() + "_CDC" + data.get("state").getAsString()
//						+ "_datalake";
//			}
//			topic = "test";
			if (data.get("type").getAsString().equals("SME")) {
				message = convertDoc.convertDocErrorToDebeziumFormat(data.get("doc").getAsString());
			} else {

			}
			data.addProperty("status", "Submit");
			cbCon.upsertDocToBucketError(data);
			System.out.println("message to kafka: " + message);
			System.out.println("topics: " + topic);
//			kafka.sendMessage(message, topic);
			if(startDate.isEmpty() || endDate.isEmpty()) {
				ret = new CouchbaseController().getErrorDocFromBucketError();
			}else {
				ret = new CouchbaseController().getErrorDocFromBucketErrorByDate(body);
			}
			return new ResponseEntity<Object>(ret, HttpStatus.OK);
		} catch (Exception e) {
			LogApp.writeLogApp(e.getMessage());
			e.printStackTrace();
			ErrorHandling errorResponse = new ErrorHandling();
			errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			errorResponse.setMessage("Failed Get doc by Id");
			errorResponse.setDebugMessage(e.getMessage());
			return new ResponseEntity<Object>(gson.toJson(errorResponse), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
