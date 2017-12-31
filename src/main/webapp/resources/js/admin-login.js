function login(){
	$.ajax({
		type : 'POST',
		url : 'loginToAdmin',
		data : {
			username : $(".username").val(),
			password : $(".password").val()
		},
		dataType: "json"
	}).success(function(data) {
		if(data.IS_ALLOWED){
			window.location.href="admin"
		} else {
			alert("Wrong username or password")
		}
		
    });
}