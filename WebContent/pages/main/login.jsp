<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../common/common.jsp" %>
<r:base/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title><s:text name="page.title.login"/></title>

</head>
<body>
  <form action="authCheck.action">
    User Name
    <input type="text" name="username"/>
    <br>
    Password
    <input type="password" name="password"/>
    <br>
    <input type="submit" value="OK"/>
  </form>
</body>
</html>