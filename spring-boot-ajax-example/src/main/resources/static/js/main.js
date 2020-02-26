$(document).ready(function() {
	 if (typeof(Storage) !== "undefined") {
		  console.log(sessionStorage.getItem("username"));
		  if(sessionStorage.getItem("username") != null){
			  window.location.replace('/home');
		  }
	  }

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
				sessionStorage.setItem("username", data.username);
			}
		}

	});
}
