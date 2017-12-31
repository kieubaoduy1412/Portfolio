<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Admin</title>
	<meta name="viewport" content="width=device-width">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="webapp/css/font-awesome.min.css">
	<link rel="shortcut icon" type="image/x-icon" href="webapp/img/favicon.ico"/>
	<link rel="stylesheet" href="webapp/css/bootstrap.css">
	<link rel="stylesheet" href="webapp/css/admin.css">
</head>
<body>
	<ul class="topnav" id="myTopnav">
	  <li class="first-li"><a href="#" onclick="loadBlogContent()">Blog</a></li>
	  <li class=""><a href="#" onclick="loadUserInfo()">User</a></li>
	  <li class=""><a href="#" onclick="loadSlogan()">Slogan</a></li>
	  <li class="icon">
	    <a href="javascript:void(0);" style="font-size:15px;" onclick="responsiveAnimation()">☰</a>
	  </li>
	</ul>

	<div id="content-container" class="container">

		<!-- Click Blog content -->
		<div class="add-story text-right">
			<button class="login-button" onclick="addStory()">Add story</button>
		</div>
		<div class="story-container">
		</div>
		<div class="change-page container text-center">
			<a href="#" class="previous-page" onclick="gotoPreviousPage();return false">Previous page</a>
			<span class="current-page">Page 1 of 30</span>
			<a href="#" class="next-page"  onclick="gotoNextPage();return false">Next page</a>
		</div>



		<!-- Add story -->
		<!-- <div class="add-story text-right">
			<button class="login-button" onclick="saveStory()">Save story</button>
		</div>
		<div class="story-container">
			<div class="title-text"><input type="text" class="title-text form-control" placeholder="Title"></div>
			<div class="content-text">
				<textarea rows="4" id="add-story-content" placeholder="content">
					At w3schools.com you will learn how to make a website. We offer free tutorials in all web development technologies.
				</textarea>
			</div>
		</div> -->

	</div>
</body>
<script src="webapp/js/jquery-2.2.3.min.js"></script>
<script charset="ISO-8859-1" src="webapp/js/admin.js"></script>
</html>