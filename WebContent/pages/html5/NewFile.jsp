<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%@ include file="Commons.jsp" %>

<title>Insert title here</title>

<style type="text/css">
div {
	border: solid 2px red;
	width: 50%;
}

input:focus {
	background-color: blue;
	background: yellow;
	position: 50, 50;
	border: 1 dotted;
}

input {
	display: block;
	padding: 0, 0, 0, 0;
	margin: 0, 0, 0, 0;
	border-top-width: 0;
	border-top-color: black;
	border-bottom-width: 0;
	line-height: 0;
}

ul {
	background: silver;
}

ul li {
	background: yellow;
	list-style-type: decimal-leading-zero;
}
</style>
<script type="text/javascript">

	function readyToMove(evt, thisdiv)
	{
		alert(evt||window.event);
		thisdiv.style.cursor="move";
	}
	
	function finishMove(thisdiv)
	{
		thisdiv.style.cursor="auto";
	}
	
	function move()
	{
	}
	
</script>
</head>
<body>
	<div onmousedown="readyToMove(event, this)" onmouseup="finishMove(this)">
		<h1>
			<a>AAAA</a>
		</h1>
		<hr>
		<h2>AAAA</h2>

		<input type="text"> <input type="text"> <input
			type="text"> <input type="text"> <input type="text">
		<input type="text"> <input type="text">
		<table>
			<tr>
				<td>asda</td>
				<td>adsad</td>
				<td>zczc</td>
			</tr>
		</table>
	</div>

	<div>
		<ul>
			<li>A</li>
			<li>B</li>
			<li>C</li>
			<li>A</li>
			<li>B</li>
			<li>C</li>
			<li>A</li>
			<li>B</li>
			<li>C</li>
			<li>A
				<ul>
					<li>A</li>
					<li>A</li>
					<li>A</li>
					<li>A</li>
				</ul>
			</li>

			<li>C</li>
		</ul>
	</div>
</body>
</html>