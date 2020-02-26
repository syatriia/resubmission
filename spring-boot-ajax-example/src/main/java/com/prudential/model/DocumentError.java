package com.prudential.model;

import com.google.gson.JsonObject;

public class DocumentError {

	private String meta_id;
	private String src_table_name;
	private String error_message;
	private String error;
	private String state;
	private JsonObject doc;
	private String type;
	private String status;
	private String TIMESTAMPS;

	public String getMeta_id() {
		return meta_id;
	}

	public void setMeta_id(String meta_id) {
		this.meta_id = meta_id;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getTIMESTAMPS() {
		return TIMESTAMPS;
	}

	public void setTIMESTAMPS(String tIMESTAMPS) {
		TIMESTAMPS = tIMESTAMPS;
	}

	public String getSrc_table_name() {
		return src_table_name;
	}

	public void setSrc_table_name(String src_table_name) {
		this.src_table_name = src_table_name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JsonObject getDoc() {
		return doc;
	}

	public void setDoc(JsonObject doc) {
		this.doc = doc;
	}

}
