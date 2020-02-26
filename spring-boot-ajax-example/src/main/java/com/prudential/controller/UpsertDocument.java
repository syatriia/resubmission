package com.prudential.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonParser;
import com.prudential.services.CouchbaseController;

@Controller
public class UpsertDocument {
	
	@RequestMapping(value = "/upsert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> upsertDocument(@RequestBody String body) {
		boolean ret = false;
		System.out.println(body);
		ret = new CouchbaseController().upsertDocToBucketError(new JsonParser().parse(body).getAsJsonObject());
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

}
