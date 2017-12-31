setInterval(function() {
	//fade In - fade Out
	$('.animation-text').fadeIn().delay(1500).fadeOut();
}, 1000);

$(document).ready(function(){
	loadContentOfStory();
});

function loadContentOfStory(){
	$.ajax({
		type : 'GET',
		url : 'getDetailStory',
		data : {
			storyID : $("#storyID").val()
		},
		dataType: "json"
	}).success(function(data) {
		console.log(data);
		$(".title").append(data.ALL_STORY.title);
		$(".content").append(data.ALL_STORY.content);
		if(data.ALL_STORY.type == 1){
			$(".content").addClass('content-font-japan');
		} else {
			$(".content").addClass('content-font-english');
		}
		if(data.ALL_STORY.previousStoryID == 0){
			$(".change-page").append(
					"<a href='story?s=" + data.ALL_STORY.nextStoryID + "' class='previous-page'>" + data.ALL_STORY.nextStoryTitle + "</a>"
			)
		} else if(data.ALL_STORY.nextStoryID == 0){
			$(".change-page").append(
					"<a href='story?s=" + data.ALL_STORY.previousStoryID + "' class='next-page'>"+ data.ALL_STORY.previousStoryTitle +"</a>"
			)
		} else {
			$(".change-page").append(
					"<a href='story?s=" + data.ALL_STORY.nextStoryID + "' class='previous-page'>"+ data.ALL_STORY.nextStoryTitle +"</a>"
					+ "<a href='story?s=" + data.ALL_STORY.previousStoryID +"' class='next-page'>"+ data.ALL_STORY.previousStoryTitle +"</a>"
			)
		}
    });
}