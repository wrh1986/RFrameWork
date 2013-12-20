<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
  window.onload = function() {
	  var worker = new Worker("worker.js");
	  worker.postMessage("Hello, worker!");
	  worker.onmessage = function(event) {
		  var msg = event.data;
		  document.getElementById("contentDiv").innerHTML = msg;
	  };
	  
  };
  
</script>
</head>
<body>
<div id = "contentDiv">

</div>
<input type="button" value="Show OK" onclick="javascript:alert('OK');">
</body>
</html>