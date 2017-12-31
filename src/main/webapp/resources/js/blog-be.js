var currentPage = 1;
var totalPage = 2;

$( document ).ready(function() {
});

function gotoPreviousPage(){
	currentPage--;
	$.when(loadAllStory(), loadTotalPage(), loadChangePageButton()).done(function(a1, a2, a3, a4){
		$('body,html').scrollTop($('.story-container').offset().top - 150);
		console.log("b")
	});
}

function gotoNextPage(){
	currentPage++;
	$.when(loadAllStory(), loadTotalPage(), loadChangePageButton()).done(function(a1, a2, a3, a4){
		$('body,html').scrollTop($('.story-container').offset().top - 150);
		console.log("b")
	});
}

function loadChangePageButton(){
	if(currentPage == 1){
		$(".previous-page").css("display","none")
	} else {
		$(".previous-page").css("display","inline-block")
	}
	if(currentPage == totalPage){
		$(".next-page").css("display","none")
	} else {
		$(".next-page").css("display","inline-block")
	}
}

function goToTop(){
	$('html, body').animate({
        scrollTop: $("header").offset().top
    }, 1000);
}

$(document).ready(function(){
	loadAllStory();
	loadChangePageButton();
	loadTotalPage();
});

$(document).ajaxStop(function () {
	$(".cssload-loader").fadeOut("slow");
	$(".loading-container").css('display','none');
});

function loadTotalPage(){
	$.ajax({
		type : 'GET',
		url : 'getTotalPageBe',
		dataType: "json"
	}).success(function(data) {
		$(".current-page").html("");
		totalPage =  Math.floor(parseInt(data.INT_TOTAL_PAGE)/5) + 1;
		$(".current-page").append("Page " + currentPage + " of " + totalPage)
    });
}

function loadAllStory(){
	$.ajax({
		type : 'GET',
		url : 'getAllStoryForBlogBePage',
		data : {
			currentPage : currentPage
		},
		dataType: "json"
	}).success(function(data) {
		console.log(data);
		$(".story-container").html("");
		var contentAppend = "";
		for(var i=0;i<data.ALL_STORY.length;i++){
			contentAppend +=
			"<div class='each-story'>"
				+ "<div class='storyid' style='display:none'>" + data.ALL_STORY[i].storyID + "</div>"
				+ "<div class='title text-center title-font-japan '><a href='story?s=" + data.ALL_STORY[i].storyID +  "'>" + data.ALL_STORY[i].title+ "</a></div>"
				if(data.ALL_STORY[i].type == 1){
					contentAppend += "<div class='story-content content-font-japan' style='font-size:24px;'>" + data.ALL_STORY[i].content.substring(0,70) + "...</div>";
				} else {
					contentAppend += "<div class='story-content content-font-english'>" + data.ALL_STORY[i].content.split(/\s+/).slice(0,35).join(" ") + "...</div>";
				}
				if(data.ALL_STORY[i].writeID == 1){
					contentAppend += "<div class='author-date text-center'>" 
						+ "<img class='avatar' src='webapp/img/ava.jpeg'><span class='author'>Duy Kieu</span>"
						+ "<span class='time'>" + data.ALL_STORY[i].createDate + "</span>"
						+ "</div></div>"
				} else {
					contentAppend += "<div class='author-date text-center'>" 
						+ "<img class='avatar' src='webapp/img/ava2.jpg'><span class='author'>My Girl</span>"
						+ "<span class='time'>" + data.ALL_STORY[i].createDate + "</span>"
						+ "</div></div>"
				}
				
		}
		$(".story-container").append(contentAppend);
		console.log("a")
    });
}

function showDetailStory(selector){
}