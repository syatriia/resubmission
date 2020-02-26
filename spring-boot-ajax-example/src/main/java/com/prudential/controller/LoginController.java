package com.prudential.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prudential.model.Auth;
import com.prudential.services.CouchbaseController;
import com.prudential.utils.Utils;

@Controller
public class LoginController {

	private Gson gson = new GsonBuilder().serializeNulls().create();

	@GetMapping("/")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getRecommendation(@RequestBody String body) {
		String res = null;
		Auth auth = gson.fromJson(body, Auth.class);
//		if (auth.getUsername().equals("admin")) {
		if (Utils.encrypt(auth.getUsername(), auth.getPassword())
				.equals(new CouchbaseController().getAuthDocFromBucketError(auth.getUsername()))) {
			res = gson.toJson(auth).toString();
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}