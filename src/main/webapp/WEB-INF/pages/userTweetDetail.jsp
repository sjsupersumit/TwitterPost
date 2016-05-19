<%--
  Created by IntelliJ IDEA.
  User: sumit.jha
  Date: 5/18/16
  Time: 11:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Tweet Details</title>

	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">

</head>

<body style="background-color: #b0c658">
<div class="content">

	<h1 class="top_header" align="center">TwitterPost</h1>

	<div class="container">
		<div class="row">
			<div class="span8 offset2">
				<h2>Best time for user to post tweet is beetween :</h2>

				<h2 align="center">${bestTime}</h2>
			</div>
		</div>
	</div>
</div>
</body>
</html>
