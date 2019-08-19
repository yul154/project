<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"  uri= "http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attend </title>
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
    <a href="?language=en">
      English
    </a>
    <a href="?language=cn">
      Chinese
    </a>
	<form:form modelAttribute="attendee">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<label for="textinput1"><spring:message code="attendee.name" /></label>
		<form:input path="name" cssClass="errorblock"/>
		<form:errors path="name" cssClass="errorblock" />
		<br>
		<label for="textinput2"><spring:message code="attendee.email" /></label>
		<form:input path="email" cssClass="errorblock"/>
		<form:errors path="email" cssClass="errorblock" />
		<br>
		<label for="textinput3"><spring:message code="attendee.phone" /></label>
		<form:input path="phone" cssClass="errorblock"/>
		<form:errors path="phone" cssClass="errorblock" />
		<br>
		<input type="submit" class="btn" value="Enter Attendee"/>
	</form:form>
</body>
</html>