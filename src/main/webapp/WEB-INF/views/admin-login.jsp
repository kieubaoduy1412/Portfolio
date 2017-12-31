<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<meta name="viewport" content="width=device-width">
	<link rel="stylesheet" type="text/css" href="webapp/css/font-awesome.min.css">
	<link rel="shortcut icon" type="image/png" href="webapp/img/favicon.png"/>
	<link rel="stylesheet" href="webapp/css/bootstrap.css">
	<link rel="stylesheet" href="webapp/css/admin-login.css">
</head>
<body>
	<div class="container login-form content-font">
		<div class="header-title title-font text-center">Login to admin</div>
		<input type="text" placeholder="Username" class="username">
		<input type="password" placeholder="Password" class="password">
		<button class="login-button" onclick="login()" value="Login">Login</button>
	</div>
</body>
<script src="webapp/js/jquery-2.2.3.min.js"></script>
<script src="webapp/js/admin-login.js"></script>
</html>