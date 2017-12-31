function sendRequest(){
	$.ajax({
		type : 'GET',
		url : 'abcd',
		dataType: "json"
	}).success(function(data) {
		console.log(data);
		$(".textabc").append(data.LIST_SIZE[1].password);
    });
}

$(document).ready(function(){
	window.location.href = 'portfolioEN';
});