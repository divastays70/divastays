<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="saveBed">
<input type="text" name="bed">
<input type="text" name="rid" value="${rId}">
<input type="submit" value="submit">
</form>

<c:forEach var="i" begin="1" end="${numberOfBed}">
    Input ${i}: <input type="text" name="txtDynamic_${i}" id="txtDynamic_${i}" />
    <br />
</c:forEach>

<form action="assignBed">
<input type="text" name="uId">
<input type="text" name="bId">
<input type="submit" value="submit">
</form>
</body>
</html>