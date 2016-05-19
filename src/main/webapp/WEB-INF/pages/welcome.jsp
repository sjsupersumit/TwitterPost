<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: sumit.jha
  Date: 5/18/16
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
	<meta charset="utf-8">
	<title>TwitterPost</title>

	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">

	<style>

		.form-horizontal .form-control {
			/* text-align:right; */
			text-align: left;
			background-color: #ffa;
		}
	</style>

</head>

<body style="background-color:#b0c658; ">

<div class="content">
	<h1 class="top_header" align="center">Welcome to TwitterPost</h1>

	<div class="container">
		<div class="row">
			<div class="span8 offset2">
				<h2>Find out the best time to tweet</h2>

				<form:form method="post" action="time" commandName="twitterUser" class="form-horizontal">
				<div class="form-group">
					<form:label cssClass="control-label " path="userName">User Name:</form:label>
					<div class="controls">
						<form:input path="userName" cssClass="control-label form-control"/>
					</div>
				</div>

				<div class="form-group">
					<h3 align="center">OR</h3>
				</div>
				<div class="form-group">
					<form:label cssClass="control-label  " path="userId">User Id:</form:label>
					<div class="controls">
						<form:input path="userId" cssClass="control-label form-control"/>
					</div>
				</div>
				<div class="form-group">
					<div class="controls">
						<input type="submit" value="Get Details" class="btn"/>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>
