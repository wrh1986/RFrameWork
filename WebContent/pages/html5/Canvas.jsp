<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="Commons.jsp" %>
<style type="text/css">
canvas 
{
	vertical-align: middle;
	margin: 0, auto;
	border: solid 1px;
}
</style>

<script type="text/javascript">
function drawLine()
{
  var canvas = document.getElementById("drawtable");
  var context = canvas.getContext("2d");
  context.fillStyle = "lightblue";
  context.fillRect(10,10,20,20);
  
  context.beginPath();
  context.moveTo(50,10);
  context.lineTo(50,35);
  context.lineTo(85,10);
  context.closePath();
  context.lineWidth=0;
  context.stroke();
  context.fillStyle = "red";
  context.fill();
  
  context.beginPath();
  context.arc(100,30,25,0,2*Math.PI, true);
  context.fillStyle = "lightblue";
  context.fill();
  
  context.fillText("Wit", 100,100);
  context.strokeText("Wit", 100,200);
  
  canvas.onclick = function() {
	  var img = canvas.toDataURL("image/png");
	  //document.write(img);
	  //window.location.href = img;
	  window.open(img);
	};
}

window.onload = drawLine;


</script>
<title>Insert title here</title>
</head>
<body>

<canvas width="800" height="500" id="drawtable" >
Your browser does not support Canvas.
</canvas>
</body>
</html>