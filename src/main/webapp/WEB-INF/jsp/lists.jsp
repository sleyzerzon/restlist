<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lists</title>
</head>
<body>
	<h2>Lists</h2>
	<ul>
	<c:forEach items="${itemListList}" var="itemList">
		<li><a href="<c:url value="/lists/${itemList.name}"/>">${itemList.title}</a></li>
	</c:forEach>
	</ul>
	<form name="create-list" method="post">
		<label for="title">Title</label> <input type="text" id="title" name="title"/>
		<label for="items">Items</label> <input type="text" id="items" name="items"/>
		<input type="submit" value="Create"/>
	</form>
</body>
</html>