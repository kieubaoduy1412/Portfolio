var currentPage = 1;
var totalPage = 2;

$(document).ready(function(){
	loadResponsivePageBasedOnWidth();
	loadAllStory();
	loadChangePageButton();
	loadTotalPage();
});

function loadUserInfo(){
	$("#content-container").html("");
}

function loadSlogan(){
	$("#content-container").html("");
}

//function ni sau ni bo
function loadBlogContent(){
	$("#content-container").html("");
	//call ajax load blog content
	// Fake data
		var appendContent = 
		"<div class='add-story text-right'>"
			+ "<button class='login-button' onclick='addStory()'>Add story</button>"
		+ "</div>"
		+ "<div class='story-container'>"
	for(var i=0;i<result.length;i++){
		appendContent += 
		"<div class='each-story' onclick=showDetailStory(this)>"
			+ "<div class='storyid'>" + result[i].id + "</div>"
			+ "<div class='title text-center'>" + result[i].title + "</div>"
			+ "<div class='content'>" + result[i].content + "</div>"
		+ "</div>"
	}
	appendContent += 
	"<div class='change-page container text-center'>"
		+ "<a href='#'' class='previous-page' onclick='gotoPreviousPage();return false'>Previous page</a>"
		+ "<span class='current-page'>Page " + currentPage + " of 30</span>"
		+ "<a href='#' class='next-page' onclick='gotoNextPage();return false'>Next page</a>"
	+ "</div>"
	$("#content-container").append(appendContent)
}

function gotoPreviousPage(){
	currentPage--;
	loadAllStory(currentPage);
	loadTotalPage();
	loadChangePageButton();
	$('body').scrollTop($('.story-container').offset().top);
}

function loadTotalPage(){
	$.ajax({
		type : 'GET',
		url : 'getTotalPage',
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
		url : 'getAllStoryForBlogPage',
		data : {
			currentPage : currentPage
		},
		dataType: "json"
	}).success(function(data) {
		$(".story-container").html("")
		var appendContent = ""
		for(var i=0;i<data.ALL_STORY.length;i++){
			appendContent += 
			"<div class='each-story' onclick=showDetailStory(this)>"
				+ "<div class='storyid'>" + data.ALL_STORY[i].storyID + "</div>"
				+ "<div class='title text-center'>" + data.ALL_STORY[i].title + "</div>"
				if(data.ALL_STORY[i].type == 1){
					appendContent += "<div class='content'>" + data.ALL_STORY[i].content.substring(0,70) + "...</div>";
				} else {
					appendContent += "<div class='content'>" + data.ALL_STORY[i].content.split(/\s+/).slice(0,35).join(" ") + "...</div>";
				}
			appendContent += "</div>"
		}
		$(".story-container").append(appendContent)
    });
}

function gotoNextPage(){
	currentPage++;
	loadAllStory(currentPage);
	loadTotalPage();
	loadChangePageButton();
	$('body').scrollTop($('.story-container').offset().top);
}


function showDetailStory(selector){
	$("#content-container").html("");
	loadDetailStory($(selector).find('.storyid').html());
}

function loadDetailStory(storyID){
	//Fake data
	$.ajax({
		type : 'GET',
		url : 'getDetailStoryAdmin',
		data : {
			storyID : storyID
		},
		dataType: "json"
	}).success(function(data) {
		$("#content-container").html("");
		var appendContent = 
		"<div class='add-story text-right'>"
			+ "<button class='update-button' style='margin-right: 20px;' onclick='deleteStory()''>Delete story</button>"
			+ "<button class='update-button' onclick='updateStory()''>Update story</button>"
		+ "</div>"
		+ "<input type='hidden' value=" + data.DETAIL_STORY.id + " class='storyID'/>"
		+ "<div class='story-container'>"
			+ "<div class='title-text'><input type='text' id='update-story-title' class='title-text form-control' placeholder='Title' value='" + data.DETAIL_STORY.title + "'></div>"
			+ "<div class='content-text'>"
				+ "<textarea rows='4' id='update-story-content' placeholder='content'>" + data.DETAIL_STORY.content + "</textarea>"
			+ "</div>"
		+ "</div>"
		$("#content-container").append(appendContent);
		$('textarea').each(function () {
			 h(this);
		})
    });
	
}

function updateStory(){
	//Call ajax to update
	$.ajax({
		type : 'POST',
		url : 'updateStoryAdmin',
		data : {
			storyID : $(".storyID").val(),
			title:$("#update-story-title").val(),
			content:$("#update-story-content").val().replace(/\n/g,"<br>")
		},
		dataType: "json"
	}).success(function(data) {
		window.location.href="admin"
    });
}

function deleteStory(){
	$.ajax({
		type : 'POST',
		url : 'deleteStoryAdmin',
		data : {
			storyID : $(".storyID").val(),
		},
		dataType: "json"
	}).success(function(data) {
		window.location.href="admin"
    });
}

/*Click button add story*/
function addStory(){
	$("#content-container").html("");
	var appendContent = 
	"<div class='add-story text-right'>"
		+ "<button class='login-button' onclick='saveStory()''>Save story</button>"
	+ "</div>"
	+ "<div class='story-container'>"
		+ "<div class='title-text'><input type='text' id='add-story-title' class='title-text form-control' placeholder='Title'></div>"
		+ "<select id='language' class='form-control' style='margin-top:20px;'>"
			+ "<option value='2'>Vietnam</option>"
			+ "<option value='1'>Japan</option>"
		+ "</select>"
		+ "<div class='content-text'>"
			+ "<textarea rows='4' id='add-story-content' placeholder='content'></textarea>"
		+ "</div>"
	+ "</div>"
	$("#content-container").append(appendContent);
}

function saveStory(){
	//Call ajax statement to save with params is title and content
	$.ajax({
		type : 'POST',
		url : 'addStoryAdmin',
		data : {
			title:$("#add-story-title").val(),
			content:$("#add-story-content").val().replace(/\n/g,"<br>"),
			type:parseInt($("#language").val()),
			createDate:getCurrentDate()
		},
		dataType: "json"
	}).success(function(data) {
		window.location.href="admin"
    });
}




/*MẤY HÀM ĐÉO QUAN TÂM*/
/*resize text area*/
function h(e) {
  $(e).css({'height':'auto','overflow-y':'hidden'}).height(e.scrollHeight);
}
$('textarea').each(function () {
  h(this);
})
$(document).on('input', 'textarea', function() { h(this)});

/*Responsive in mobile*/
function responsiveAnimation() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}

function loadResponsivePageBasedOnWidth(){
	if ($(window).width() > 680) {
	    $( "#myTopnav li" ).first().addClass("active");
		$('#myTopnav li').click(function() {
	        $(this).siblings('li').removeClass('active');
	        $(this).addClass('active');
	    });
	}
    else{
    	$('#myTopnav li:not(:last)').click(function() {
	        /*$(this).siblings('li').not(":last-child").css('display','none');
	        $(this).css('display','block');*/
	        $("#myTopnav").removeClass('responsive');
	        $(this).prependTo("#myTopnav");
	        /*$("#myTopnav li").eq($(this).index()).remove();*/
	        /*$("#myTopnav").prepend("<li>" + $(this).html());*/
	        $("#myTopnav li").siblings('li').not(":last-child").removeClass('first-li');	        
			$("#myTopnav li").first().addClass('first-li')
	    });
    }
}

function loadChangePageButton(){
	if(currentPage == 1){
		$(".previous-page").css("display","none")
	} else {
		$(".previous-page").css("display","block")
	}
	if(currentPage == totalPage){
		$(".next-page").css("display","none")
	} else {
		$(".next-page").css("display","inline-block")
	}
}

function getCurrentDate(){
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	var output = 
	    (day<10 ? '0' : '') + day + '-' +
	    (month<10 ? '0' : '') + month + '-' +
	    d.getFullYear()
	return output
}
