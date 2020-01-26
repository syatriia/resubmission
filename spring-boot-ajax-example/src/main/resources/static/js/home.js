$(document).ready(
		function() {

			var bodyData = $("#body_data");
			var addBody, td;

			if (bodyData.children().length > 1) {
				bodyData.empty();
			}
			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "/query",
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					$.each(data, function(k, v) {
						console.log(k);
						console.log(v);
						td = '<tr><td class="text-right">' + v.meta_id + '</td>'
							+ '<td class="text-right">' + v.table_name+ '</td>'
							+ '<td class="text-right">' + v.error_messagge+ '</td>'
							+ '<td class="text-right">' + v.doc+ '</td>'
							+ '<td class="text-right">' + v.TIMESTAMPS+ '</td></tr>';
						bodyData.append(td);
					});
				
				}

			});

			/* var table = $('#data').DataTable(); */

			$('#example tbody').on('click', 'tr', function() {
				var data = table.row(this).data();
				alert('You clicked on ' + data[0] + '\'s row');
			});
		});