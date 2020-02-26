package com.prudential.services;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.gson.Gson;
import com.prudential.utils.Config;
import com.prudential.utils.Exceptions;
import com.prudential.utils.Utils;

public class Kafka {

	private Properties props = new Properties();
	public Config config;

	public Kafka() {
		config = new Gson().fromJson(Utils.readFileContent(Utils.PATH), Config.class);
		initProp();
	}

	public Boolean sendMessage(String doc, String topic) {
		boolean ret = false;
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		try {
			producer.send(new ProducerRecord<String, String>(topic, doc)).get();
			System.out.println("Success Send Doc to Kafka topics "+topic);
			ret  = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new Exceptions(e.getMessage());
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw new Exceptions(e.getMessage());
		}
		return ret;
	}

	private void initProp() {
		props.put("bootstrap.servers", config.getkafkaHost() + ":" + config.getkafkaPort());
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	}

}
