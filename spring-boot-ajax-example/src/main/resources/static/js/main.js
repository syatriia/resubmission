$(document).ready(function() {

	/*
	 * $("#search-form").submit(function(event) { // stop submit the form, we
	 * will post it manually. event.preventDefault();
	 * 
	 * fire_ajax_submit();
	 * 
	 * });
	 */

	/*
	 * $('.form-signin').on('submit', function () { auth(); });
	 */

});

function auth() {
	var auth = {}
	auth["username"] = $("#username").val();
	auth["password"] = $("#password").val();
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/auth",
		data : JSON.stringify(auth),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			if (data.username != null) {
				window.location.replace('/home');
			}
		}

	});
}
