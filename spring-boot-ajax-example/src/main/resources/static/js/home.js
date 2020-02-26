var dataDocError;

$(document).ready(function() {
		  if (typeof(Storage) !== "undefined") {
			  console.log(sessionStorage.getItem("username"));
			  if(sessionStorage.getItem("username") == null){
				  window.location.replace('/');
			  }
		  }

			$('#edit').hide()
			var table = $("#data").DataTable();
			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "/query",
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					$.each(data, function(k, v) {
						if(v.status != "Submit"){
							table.row.add([
								v.meta_id,
								v.src_table_name,
								v.error_message,
								v.type,
								v.status,
								v.state,
								v.TIMESTAMPS,
								'<button type="button" class="btn btn-success" >Submit to Kafka</button>' ])
						.draw();
						}else{
							table.row.add([
								v.meta_id,
								v.src_table_name,
								v.error_message,
								v.type,
								v.status,
								v.state,
								v.TIMESTAMPS,
								'' ])
						.draw();
						}
						
					});
				}
			});
			
			
			 table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {       
			        var cell = table.cell({ row: rowIdx, column: 0 }).node();
			        $(cell).addClass('background-color: #fc0303 !important;');        
			    });
			
			//button action
			 $('#data tbody').on( 'click', 'button', function () {
			        var data = table.row( $(this).parents('tr') ).data();
			        sendToKafka(data[0]);
			    });

			$(document).on("click", "#data td:nth-child(1)", function() {
				/* alert($(this).text()); */
				var dataRow = table.row($(this).parents('tr')).data();
				editForm(dataRow);
			});
		});

function editForm(dataRow) {
	/* $('#form_doc_id').val(data[0]); */
	var url = "/query/id/"+dataRow[0];
	console.log(url);
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : url,
		dataType : 'json',
		cache: true,
		timeout : 600000,
		success : function(data) {
			console.log(data)
			dataDocError = data;
			$('#form_doc_id').val(data.meta_id);
			$('#form_table_name').val(data.src_table_name);
			$('#form_error_message').val(data.error_message);
			$('#form_error_details').val(data.error);
			$('#form_doc').val(data.doc);
		}
	});
	$('#main').hide();
	$('#edit').show();
}

function upsertDoc(){

	dataDocError.doc = $('#form_doc').val();
	$.ajax({
	 type: "POST",
	    url: "/upsert",
	    data: JSON.stringify(dataDocError),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(data){
	    	$('#edit').hide();
	    	$('#main').show();
	    	var startDate = $('#startDate').val();
	    	var endDate = $('#endDate').val();
	    	
	    	if( startDate != null || endDate != null){
	    		searchByDate();
	    	}else{
	    		laodData();
	    	}
	    	
	    	},
	    failure: function(errMsg) {
	        alert(errMsg);
	    }});
//	window.location.replace('/home');
}

function refresh(){
	
}

function searchByDate(){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var param = {};
	param.startDate = startDate;
	param.endDate = endDate;
	if(startDate>endDate){
		alert("start date is higher than end date");
	}else if(startDate == '' ||endDate == ''){
		alert("start date and end date must not empty");
	}else{
		$.ajax({
			 type: "POST",
			    url: "/query/date",
			    data: JSON.stringify(param),
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(data){
			    	var table = $("#data").DataTable();
			    	table.clear().draw();
			    	$.each(data, function(k, v) {
			    		if(v.status != "Submit"){
							table.row.add([
								v.meta_id,
								v.src_table_name,
								v.error_message,
								v.type,
								v.status,
								v.state,
								v.TIMESTAMPS,
								'<button type="button" class="btn btn-success" >Submit to Kafka</button>' ])
						.draw();
						}else{
							table.row.add([
								v.meta_id,
								v.src_table_name,
								v.error_message,
								v.type,
								v.status,
								v.state,
								v.TIMESTAMPS,
								'' ])
						.draw();
						}
					});
			    	},
			    failure: function(errMsg) {
			        alert(errMsg);
			    }});
	}
	
}

function sendToKafka(id){
	console.log(id);
	var param = {};
	param.id = id;
	param.startDate = $('#startDate').val(); 
	param.endDate = $('#endDate').val(); 
	$.ajax({
		 type: "POST",
		    url: "/push/kafka",
		    data: JSON.stringify(param),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(data){
		    	var table = $("#data").DataTable();
		    	table.clear().draw();
		    	$.each(data, function(k, v) {
		    		if(v.status != "Submit"){
						table.row.add([
							v.meta_id,
							v.src_table_name,
							v.error_message,
							v.type,
							v.status,
							v.state,
							v.TIMESTAMPS,
							'<button type="button" class="btn btn-success" >Submit to Kafka</button>' ])
					.draw();
					}else{
						table.row.add([
							v.meta_id,
							v.src_table_name,
							v.error_message,
							v.type,
							v.status,
							v.state,
							v.TIMESTAMPS,
							'' ])
					.draw();
					}
				});
		    	},
		    failure: function(errMsg) {
		        alert(errMsg);
		    }});
	
}

function laodData(){
	var table = $("#data").DataTable();
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/query",
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			$.each(data, function(k, v) {
				if(v.status != "Submit"){
					table.row.add([
						v.meta_id,
						v.src_table_name,
						v.error_message,
						v.type,
						v.status,
						v.state,
						v.TIMESTAMPS,
						'<button type="button" class="btn btn-success" >Submit to Kafka</button>' ])
				.draw();
				}else{
					table.row.add([
						v.meta_id,
						v.src_table_name,
						v.error_message,
						v.type,
						v.status,
						v.state,
						v.TIMESTAMPS,
						'' ])
				.draw();
				}
				
			});
		}
	});
}
	