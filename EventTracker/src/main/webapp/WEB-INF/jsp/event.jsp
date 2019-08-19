<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event</title>
<style type="text/css">
	.erro{
		color:#ff0000;
	}
	.errorblock{
		color:#000;
		background-color:#ffEEEE;
		border:3px solid #ff0000;
		padding: 10px;
		margin: 5px;
	}

</style>
</head>
<body>

	<form:form modelAttribute="event">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<label for="textinput1">Enter Minutes : </label>
		<form:input path="name" cssClass="errorblock"/>
		<form:errors path="name" cssClass="errorblock" />
		<br>
		<input type="submit" class="btn" value="Enter Event"/>
	</form:form>
</body>
</html>