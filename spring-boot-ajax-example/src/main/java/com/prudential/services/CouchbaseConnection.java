package com.prudential.services;



import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.google.gson.Gson;

import com.prudential.utils.Config;
//import com.prudential.resubmission.utils.AppConfig;
import com.prudential.utils.Utils;

public class CouchbaseConnection {

	protected Cluster cluster;
	protected Bucket bucket;
	
	public Config config;
	public CouchbaseConnection() {
//		AppConfig appConfig = new AppConfig();
		config = new Gson().fromJson(Utils.readFileContent(Utils.PATH), Config.class);
		cluster = CouchbaseCluster.create(config.getCbnode());
		cluster.authenticate(config.getCbuser(), config.getCbpass());
	}

	public void getConnection() {
		//wtf ga bisa open bucket
//		System.out.println("cbhost : "+appConfig.getCbHost());
//		System.out.println("cbuser : "+appConfig.getCbUser());
//		System.out.println("cbpass : "+appConfig.getCbPass());
//		System.out.println("cbbucket : "+appConfig.getCbbucket());

//		cluster = CouchbaseCluster.create(appConfig.getCbHost());
//		cluster.authenticate(appConfig.getCbUser(), appConfig.getCbPass());
//		this.bucket = cluster.openBucket(appConfig.getCbbucket());

		this.bucket = cluster.openBucket(config.getBucket());

		
//		return cluster.openBucket("Error");
//		return bucket;
	}

	public Bucket getBucket(String bucketName) {
		bucket = cluster.openBucket(bucketName);
		return bucket;
	}

//	public void getConnection() {
//		getConnection();
//		bucket = cluster.openBucket(appConfig.getCbbucket());
//		return bucket;
//	}

	public void closeConnection(Bucket bucket) {
		bucket.close();
//		cluster.disconnect();
	}

}
