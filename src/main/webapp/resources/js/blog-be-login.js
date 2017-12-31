function login(){
	$.ajax({
		type : 'POST',
		url : 'loginToBlogBe',
		data : {
			password : $(".password").val()
		},
		dataType: "json"
	}).success(function(data) {
		if(data.IS_ALLOWED){
			window.location.href="blog"
		} else {
			alert("Wrong password")
		}
    });
}