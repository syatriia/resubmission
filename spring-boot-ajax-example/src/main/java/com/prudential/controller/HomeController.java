package com.prudential.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getErrorDoc() {
//		try {
//			CouchbaseController cb = new CouchbaseController(appConfig);
//			CouchbaseController cb = new CouchbaseController();
			String example = "[\r\n" + 
					"	{\r\n" + 
					"		\"TIMESTAMPS\": \"20-01-21 08:06:46.391\",\r\n" + 
					"		\"doc\": \"{\\\"id\\\":\\\"9999\\\",\\\"group_type_code\\\":\\\"GROUP\\\",\\\"group_status_code\\\":\\\"INFORCE\\\",\\\"code\\\":\\\"001005-A19-HO\\\",\\\"name\\\":\\\"PT (4)A PA\\\",\\\"common_renewal_date_flag\\\":\\\"NNN\\\",\\\"eligible_accredited_flag\\\":\\\"N\\\",\\\"acc_status_code\\\":\\\"\\\",\\\"sales_region_id\\\":\\\"1\\\",\\\"creuser\\\":\\\"2\\\",\\\"credate\\\":1551102203732,\\\"upduser\\\":\\\"1214\\\",\\\"upddate\\\":1553065096888,\\\"administrator_id\\\":\\\"10\\\",\\\"membership_num_seq\\\":\\\"\\\",\\\"start_date\\\":1546300800000,\\\"end_date\\\":\\\"\\\",\\\"premium_change_on_agebanded\\\":\\\"\\\",\\\"employee_only\\\":\\\"N\\\",\\\"membership_fee\\\":\\\"N\\\",\\\"renewal_type_code\\\":\\\"GROUP\\\",\\\"surcharge_fee\\\":\\\"\\\",\\\"aso_fee\\\":\\\"\\\",\\\"discount\\\":\\\"\\\",\\\"endorsement_fee\\\":\\\"15000.00\\\",\\\"duty_stamp_fee\\\":\\\"6000.00\\\",\\\"delete_flag\\\":\\\"\\\",\\\"policy_holder\\\":\\\"PT (4)A PA\\\",\\\"business_type\\\":\\\"NEW_BUSINESS\\\",\\\"tax_code\\\":\\\"GST\\\",\\\"tax_number\\\":\\\"\\\",\\\"membership_fee_amount\\\":\\\"\\\",\\\"tax_payment_type_code\\\":\\\"PAYMENT_MODE\\\",\\\"occ_number\\\":\\\"\\\",\\\"reinstate_date\\\":\\\"\\\",\\\"frequency_code\\\":\\\"ANNUAL\\\",\\\"underwriter_id\\\":\\\"1033\\\",\\\"policy_type_id\\\":\\\"70\\\",\\\"full_refund_flag\\\":\\\"N\\\",\\\"refunded_flag\\\":\\\"N\\\",\\\"termination_type_code\\\":\\\"\\\",\\\"reinstatement_type_code\\\":\\\"\\\",\\\"short_term_policy_flag\\\":\\\"N\\\",\\\"deactivation_date\\\":\\\"\\\",\\\"cwt\\\":\\\"\\\",\\\"waive_surcharge\\\":\\\"N\\\",\\\"override_surcharge\\\":\\\"\\\",\\\"group_code_activated\\\":\\\"Y\\\",\\\"travel_type_code\\\":\\\"\\\",\\\"renewal_count\\\":\\\"\\\",\\\"lapsed_effective_date\\\":\\\"\\\",\\\"expired_effective_date\\\":\\\"\\\",\\\"cancellation_date\\\":\\\"\\\",\\\"refund_method_code\\\":\\\"\\\",\\\"customer_account_id\\\":\\\"\\\",\\\"surrender_rate\\\":\\\"\\\",\\\"member_aggregation_limit\\\":\\\"\\\",\\\"invoicing_level\\\":\\\"GROUP\\\",\\\"invoicing_format\\\":\\\"\\\",\\\"renewal_category_code\\\":\\\"AUTO_RENEWAL\\\",\\\"membership_card_type_code\\\":\\\"\\\",\\\"tpa_id\\\":\\\"\\\",\\\"quotation_ref_number\\\":\\\"4-4\\\",\\\"auto_billing\\\":\\\"Y\\\",\\\"cancel_remarks\\\":\\\"\\\",\\\"source_of_business_code\\\":\\\"SOURCE_OF_BUSINESS_AGENCY\\\",\\\"bank_referral_code\\\":\\\"\\\",\\\"bank_branch\\\":\\\"\\\",\\\"bank_agent_code\\\":\\\"\\\",\\\"insurance_specialist_code\\\":\\\"\\\",\\\"for_cancel_date\\\":\\\"\\\",\\\"admin_fee\\\":\\\"\\\",\\\"group_policy_number\\\":\\\"2000076\\\",\\\"claim_payee_type\\\":\\\"\\\",\\\"group_company_type_code\\\":\\\"\\\",\\\"ENTITY_TYPE\\\":\\\"SME\\\",\\\"SRC_TABLE_NAME\\\":\\\"XXX\\\",\\\"DEBEZIUM_TIMESTAMP\\\":\\\"2020-01-10 09:33:26.161\\\",\\\"SRC_SYSTEM\\\":\\\"OMNI\\\",\\\"SRC_CDC_OPERATION\\\":\\\"UPDATE\\\",\\\"SRC_TXN_TIMESTAMP\\\":\\\"2020-01-21 07:19:36.662\\\",\\\"SRC_CHANGE_NUM\\\":\\\"2020-01-21 07:19:36.662\\\"}\",\r\n" + 
					"		\"error\": \"java.lang.NullPointerException\\n\\tat com.prudential.sme.platform.data.stream.StatefulTransformationFunction.processElement(StatefulTransformationFunction.java:162)\\n\\tat com.prudential.sme.platform.data.stream.StatefulTransformationFunction.processElement(StatefulTransformationFunction.java:1)\\n\\tat org.apache.flink.streaming.api.operators.KeyedProcessOperator.processElement(KeyedProcessOperator.java:85)\\n\\tat org.apache.flink.streaming.runtime.io.StreamOneInputProcessor.processElement(StreamOneInputProcessor.java:164)\\n\\tat org.apache.flink.streaming.runtime.io.StreamOneInputProcessor.processInput(StreamOneInputProcessor.java:143)\\n\\tat org.apache.flink.streaming.runtime.tasks.StreamTask.processInput(StreamTask.java:279)\\n\\tat org.apache.flink.streaming.runtime.tasks.StreamTask.run(StreamTask.java:301)\\n\\tat org.apache.flink.streaming.runtime.tasks.StreamTask.invoke(StreamTask.java:406)\\n\\tat org.apache.flink.runtime.taskmanager.Task.doRun(Task.java:705)\\n\\tat org.apache.flink.runtime.taskmanager.Task.run(Task.java:530)\\n\\tat java.lang.Thread.run(Thread.java:748)\\n\",\r\n" + 
					"		\"table_name\": \"XXX\",\r\n" + 
					"		\"error_messagge\": null,\r\n" + 
					"		\"meta_id\": \"XXX::1579594006391\"\r\n" + 
					"	},\r\n" + 
					"	{\r\n" + 
					"		\r\n" + 
					"		\"TIMESTAMPS\": \"20-01-21 08:06:46.391\",\r\n" + 
					"		\"doc\": \"{\\\"id\\\":\\\"9999\\\",\\\"group_type_code\\\":\\\"GROUP\\\",\\\"group_status_code\\\":\\\"INFORCE\\\",\\\"code\\\":\\\"001005-A19-HO\\\",\\\"name\\\":\\\"PT (4)A PA\\\",\\\"common_renewal_date_flag\\\":\\\"NNN\\\",\\\"eligible_accredited_flag\\\":\\\"N\\\",\\\"acc_status_code\\\":\\\"\\\",\\\"sales_region_id\\\":\\\"1\\\",\\\"creuser\\\":\\\"2\\\",\\\"credate\\\":1551102203732,\\\"upduser\\\":\\\"1214\\\",\\\"upddate\\\":1553065096888,\\\"administrator_id\\\":\\\"10\\\",\\\"membership_num_seq\\\":\\\"\\\",\\\"start_date\\\":1546300800000,\\\"end_date\\\":\\\"\\\",\\\"premium_change_on_agebanded\\\":\\\"\\\",\\\"employee_only\\\":\\\"N\\\",\\\"membership_fee\\\":\\\"N\\\",\\\"renewal_type_code\\\":\\\"GROUP\\\",\\\"surcharge_fee\\\":\\\"\\\",\\\"aso_fee\\\":\\\"\\\",\\\"discount\\\":\\\"\\\",\\\"endorsement_fee\\\":\\\"15000.00\\\",\\\"duty_stamp_fee\\\":\\\"6000.00\\\",\\\"delete_flag\\\":\\\"\\\",\\\"policy_holder\\\":\\\"PT (4)A PA\\\",\\\"business_type\\\":\\\"NEW_BUSINESS\\\",\\\"tax_code\\\":\\\"GST\\\",\\\"tax_number\\\":\\\"\\\",\\\"membership_fee_amount\\\":\\\"\\\",\\\"tax_payment_type_code\\\":\\\"PAYMENT_MODE\\\",\\\"occ_number\\\":\\\"\\\",\\\"reinstate_date\\\":\\\"\\\",\\\"frequency_code\\\":\\\"ANNUAL\\\",\\\"underwriter_id\\\":\\\"1033\\\",\\\"policy_type_id\\\":\\\"70\\\",\\\"full_refund_flag\\\":\\\"N\\\",\\\"refunded_flag\\\":\\\"N\\\",\\\"termination_type_code\\\":\\\"\\\",\\\"reinstatement_type_code\\\":\\\"\\\",\\\"short_term_policy_flag\\\":\\\"N\\\",\\\"deactivation_date\\\":\\\"\\\",\\\"cwt\\\":\\\"\\\",\\\"waive_surcharge\\\":\\\"N\\\",\\\"override_surcharge\\\":\\\"\\\",\\\"group_code_activated\\\":\\\"Y\\\",\\\"travel_type_code\\\":\\\"\\\",\\\"renewal_count\\\":\\\"\\\",\\\"lapsed_effective_date\\\":\\\"\\\",\\\"expired_effective_date\\\":\\\"\\\",\\\"cancellation_date\\\":\\\"\\\",\\\"refund_method_code\\\":\\\"\\\",\\\"customer_account_id\\\":\\\"\\\",\\\"surrender_rate\\\":\\\"\\\",\\\"member_aggregation_limit\\\":\\\"\\\",\\\"invoicing_level\\\":\\\"GROUP\\\",\\\"invoicing_format\\\":\\\"\\\",\\\"renewal_category_code\\\":\\\"AUTO_RENEWAL\\\",\\\"membership_card_type_code\\\":\\\"\\\",\\\"tpa_id\\\":\\\"\\\",\\\"quotation_ref_number\\\":\\\"4-4\\\",\\\"auto_billing\\\":\\\"Y\\\",\\\"cancel_remarks\\\":\\\"\\\",\\\"source_of_business_code\\\":\\\"SOURCE_OF_BUSINESS_AGENCY\\\",\\\"bank_referral_code\\\":\\\"\\\",\\\"bank_branch\\\":\\\"\\\",\\\"bank_agent_code\\\":\\\"\\\",\\\"insurance_specialist_code\\\":\\\"\\\",\\\"for_cancel_date\\\":\\\"\\\",\\\"admin_fee\\\":\\\"\\\",\\\"group_policy_number\\\":\\\"2000076\\\",\\\"claim_payee_type\\\":\\\"\\\",\\\"group_company_type_code\\\":\\\"\\\",\\\"ENTITY_TYPE\\\":\\\"SME\\\",\\\"SRC_TABLE_NAME\\\":\\\"XXX\\\",\\\"DEBEZIUM_TIMESTAMP\\\":\\\"2020-01-10 09:33:26.161\\\",\\\"SRC_SYSTEM\\\":\\\"OMNI\\\",\\\"SRC_CDC_OPERATION\\\":\\\"UPDATE\\\",\\\"SRC_TXN_TIMESTAMP\\\":\\\"2020-01-21 07:19:36.662\\\",\\\"SRC_CHANGE_NUM\\\":\\\"2020-01-21 07:19:36.662\\\"}\",\r\n" + 
					"		\"error\": \"java.lang.NullPointerException\\n\\tat com.prudential.sme.platform.data.stream.StatefulTransformationFunction.processElement(StatefulTransformationFunction.java:162)\\n\\tat com.prudential.sme.platform.data.stream.StatefulTransformationFunction.processElement(StatefulTransformationFunction.java:1)\\n\\tat org.apache.flink.streaming.api.operators.KeyedProcessOperator.processElement(KeyedProcessOperator.java:85)\\n\\tat org.apache.flink.streaming.runtime.io.StreamOneInputProcessor.processElement(StreamOneInputProcessor.java:164)\\n\\tat org.apache.flink.streaming.runtime.io.StreamOneInputProcessor.processInput(StreamOneInputProcessor.java:143)\\n\\tat org.apache.flink.streaming.runtime.tasks.StreamTask.processInput(StreamTask.java:279)\\n\\tat org.apache.flink.streaming.runtime.tasks.StreamTask.run(StreamTask.java:301)\\n\\tat org.apache.flink.streaming.runtime.tasks.StreamTask.invoke(StreamTask.java:406)\\n\\tat org.apache.flink.runtime.taskmanager.Task.doRun(Task.java:705)\\n\\tat org.apache.flink.runtime.taskmanager.Task.run(Task.java:530)\\n\\tat java.lang.Thread.run(Thread.java:748)\\n\",\r\n" + 
					"		\"table_name\": \"XXX\",\r\n" + 
					"		\"error_messagge\": \"xxx\",\r\n" + 
					"		\"meta_id\": \"XXX::test1\"\r\n" + 
					"	}\r\n" + 
					"]";
			return new ResponseEntity<Object>(example, HttpStatus.OK);
//			return new ResponseEntity<Object>(cb.getErrorDocFromBucketError().toString(), HttpStatus.OK);

//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//			errorResponse.setMessage("");
//			errorResponse.setDebugMessage(e.toString());
//			return new ResponseEntity<Object>(gson.toJson(errorResponse), HttpStatus.INTERNAL_SERVER_ERROR);
//		}

	}

}
