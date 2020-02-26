package com.prudential.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {

	@SerializedName("kafkahost")
	@Expose
	private String kafkaHost;
	@SerializedName("kafkaport")
	@Expose
	private Integer kafkaPort;
	@SerializedName("topic")
	@Expose
	private String topic;
	@SerializedName("bucket")
	@Expose
	private String bucket;
	@SerializedName("cbuser")
	@Expose
	private String cbuser;
	@SerializedName("cbpass")
	@Expose
	private String cbpass;
	@SerializedName("cbnode")
	@Expose
	private String cbnode;


	public String getkafkaHost() {
		return kafkaHost;
	}

	public void setkafkaHost(String kafkaHost) {
		this.kafkaHost = kafkaHost;
	}

	public Integer getkafkaPort() {
		return kafkaPort;
	}

	public void setkafkaPort(Integer kafkaPort) {
		this.kafkaPort = kafkaPort;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getCbuser() {
		return cbuser;
	}

	public void setCbuser(String cbuser) {
		this.cbuser = cbuser;
	}

	public String getCbpass() {
		return cbpass;
	}

	public void setCbpass(String cbpass) {
		this.cbpass = cbpass;
	}

	public String getCbnode() {
		return cbnode;
	}

	public void setCbnode(String cbnode) {
		this.cbnode = cbnode;
	}


}
