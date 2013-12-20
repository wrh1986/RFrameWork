<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript" src="<%=request.getContextPath()+
            request.getServletContext().getInitParameter("dojoPath") %>/dojo/dojo.js" djconfig="parseOnLoad:true"></script>
            
<title>Insert title here</title>
<style type="text/css">
  @import "<%=request.getContextPath()+
            request.getServletContext().getInitParameter("dojoPath") %>/dijit/themes/tundra/tundra.css";
  @import "<%=request.getContextPath()+
            request.getServletContext().getInitParameter("dojoPath") %>/dijit/themes/tundra/Dialog.css";
</style>
<style type="text/css">
#topDiv {
  width: 100%; 
  height: 100px; 
  margin-top: -90px;
  background-color: #DADADA;
  z-index: 1000;
  cursor: pointer;
}

.menuPane {
  height: 100%;
  position: absolute;
  background-color: #777777;
  z-index: 1000;
  overflow: hidden;
  padding: 0px;
}

.menuPane ol {
  padding: 0;
  margin: 0;
  height: 100%;
  list-style: none inside none;
  font-size: 14px;
}
.menuPane ol li {
  border-top: 1px dotted blue;
  width: 100%;
  height: 40px;
  padding: 0;
  margin: 0;
  background-color: gray;
  cursor: pointer;
}

.menuPane ol li a {
  display:block;
  padding: 10px 10px 10px 25px;
}

.menuPane ol li a:hover{
  background:#6dc7ec;
  color:#fff;
}

.menuPane ol li a:active{
  background-color: red;
}

ol {
  list-style: none inside none;
}

li {
  list-style: none inside none;
}

#grid ol li {
  border: 1px solid #D8D8D8;
  float: left;
  height: 166px;
  margin: 25px;
  position: relative;
  width: -moz-calc((100% - 80px) / 5);
  FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#A8D3FF,endColorStr=#FFFFFF);/*IE6*/
  background:-moz-linear-gradient(top,#A8D3FF,#FFFFFF);/*·ÇIE6µÄÆäËü*/
  background:-webkit-gradient(linear, 0% 0%, 0% 100%, from(#A8D3FF), to(#FFFFFF)); 
  border-radius:10;
}

#grid ol li .check {
  left: 0px;
  top: 0px;
  position: absolute;
}

#grid ol li .delete {
  background-image: url("../img/delete3.png");
  background-color:transparent;
  right:0px;
  top: 0px;
  position: absolute;
  border: 0 none;
  cursor: pointer;
}

#grid ol li .name {
  width: 100%;
  height: 25px;
  line-height: 25px;
  text-indent: 5px;
  bottom: 0px;
  background: none repeat scroll 0 0 #FAFAFA;
  overflow: hidden;
  position: absolute;
}

#grid ol li a .blockA{
  width: 30%; 
  height: 50%; 
  position: absolute; 
  left: 15%; top: 25%; 
  font-size: 48px; 
  color: gray;
  line-height: 80px;
}

#grid ol li a .blockB{
  width: 30%; 
  height: 50%; 
  position: absolute; 
  left: 55%; top: 25%; 
  font-size: 48px; 
  color: yellow;
  line-height: 80px;
}
#grid ol li a .blockC{
 
}

html, body, #main{ 
  width: 100%; /* make the body expand to fill the visible window */
  height: 100%;
  overflow: hidden; /* erase window level scrollbars */
  padding: 0 0 0 0;
  margin: 0 0 0 0;
  font: 10pt Arial,Myriad,Tahoma,Verdana,sans-serif;
} 

.titleName{
  font-family: Microsoft YaHei,Simsum,serif;
  font-size: small;
}

.dijitDialogUnderlay{
  display: none;
}
</style>
<script type="text/javascript">
dojo.require("dojo.parser");
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("dojox.layout.ContentPane");
dojo.require("dijit.layout.AccordionContainer");
dojo.require("dijit.layout.TabContainer");
dojo.require("dijit.form.Slider"); 
dojo.require("dojo.fx");
dojo.require("dijit.Dialog");

var mx = -90;
var mx1 = -90;
var mx2 = -90;
var display = false;
function load(){
  if(display) {  
    if(mx > -90) {
      mx = mx - 10;
      dojo.byId("topDiv").style.marginTop = mx + "px";
      setTimeout(load,20);
    } else {
      display = false;
    }
  } else {
    if(mx < 0) {
      mx = mx + 10;
      dojo.byId("topDiv").style.marginTop = mx + "px";
      setTimeout(load,20);
    } else {
      display = true;
    }
  }
}

function loadActionDiv(displayActionDiv){
  if(displayActionDiv) {
    if(mx1 > -90) {
      mx1 = mx1 - 10;
      dojo.byId("actionDiv").style.marginRight = mx1 + "px";
      setTimeout(function(){
    	  loadActionDiv(displayActionDiv);}, 20);
      
    } 
  } else {
    if(mx1 < 0) {
      mx1 = mx1 + 10;
      dojo.byId("actionDiv").style.marginRight = mx1 + "px";
      setTimeout(function(){
        loadActionDiv(displayActionDiv);}, 20);
    } 
  }
}

function loadControlDiv(displayDiv){
  if(displayDiv) {
    if(mx2 > -190) {
      mx2 = -190;//mx2 - 10;
      dojo.byId("controlDiv").style.marginLeft = mx2 + "px";
      //setTimeout(function(){
    	//  loadControlDiv(displayDiv);}, 20);
      
    } 
  } else {
    if(mx2 < 0) {
      mx2 = 0;//mx2 + 10;
      dojo.byId("controlDiv").style.marginLeft = mx2 + "px";
      //setTimeout(function(){
    	//  loadControlDiv(displayDiv);}, 20);
    } 
  }
}

function handleSelect() {
	var checks = document.getElementsByName("itemSelect");
	for(var index=0; index < checks.length ; index ++) {
		if(checks.item(index).checked) {
			loadActionDiv(false);
			return;
		}
	}
	loadActionDiv(true);
}

function showLogDetail(name) {
	myDialog = new dijit.Dialog({
		      title: name,
          content: "Log test content1.<br>Log test content2.",
          style: "height: 600px; width: 800px;"
        });
	myDialog.show();
}

function addNewMonitor(){
	myDialog = new dijit.Dialog({
        title: "New Monitor",
        content: "New Monitor",
        style: "height: 400px; width: 300px;"
      });
	myDialog.show();
}

var consoleDiv = null;
function hideConsole() {
	if(consoleDiv == null){
		consoleDiv = dijit.byId("consoleDiv");
	  dijit.byId("main").removeChild(consoleDiv);
	} else {
		dijit.byId("main").addChild(consoleDiv);
		consoleDiv = null;
	}
}

dojo.ready(function(){
	dojo.style(dojo.byId("controlDiv"),{
									width: "200px",
							    left: "0px",
							    marginLeft: "-190px"
								});
	dojo.style(dojo.byId("actionDiv"),{
        width: "100px",
        right: "0px",
        marginRight: "-90px"
      });
});
</script>
</head>
<body class="tundra">

<div dojoType="dijit.layout.BorderContainer" design="headline" id="main">
  <div id="actionDiv" class="menuPane">
    <ol>
      <li>
        <a>MODIFY</a>
      </li>
      <li>
        <a>DELETE</a>
      </li>
    </ol>
  </div>
  <div id="controlDiv" class="menuPane" onmouseover="loadControlDiv(false)" onmouseout="loadControlDiv(true)">
    <ol>
      <li>
        <a onclick="load()">FILTER</a>
      </li>
      <li>
        <a onclick="hideConsole()" >CONSOLE</a>
      </li>
      <li>
        <a onclick="" >HISTORY LOG QUERY</a>
      </li>
    </ol>
  </div>
  <div id="centerDiv" dojoType="dijit.layout.ContentPane" region="center"
    style="padding: 0px;">
    
    <div id="mainDiv">
		  <div id="topDiv" onclick="load()">
		    <table width="100%">
		      <col width="10%">
		      <col width="10%">
		      <col width="60%">
		      <tr>
		        <td></td>
		        <td>
		          Filter By Name:
		        </td>
		        <td>
		          <input type="text" size="50"/>
		        </td>
		      </tr>
		      <tr>
		        <td></td>
		        <td>
		          Sort By:
		        </td>
		        <td>
		          <input type="radio">Name
		          <input type="radio">Update Counts
		          <input type="radio">Update Time
		          <input type="radio">Total Counts
		        </td>
		      </tr>
		    </table>
		  </div>
		  <div id="grid">
		    <ol>
		      <li>
		        <input name="itemSelect" type="checkbox" onclick="handleSelect()" class="check">
		        <input type="button" class="delete">
		        <a href="#" onclick="showLogDetail('ALL')"> 
		          <div class="blockA">189</div>
		          <div class="blockB">11</div>
		          <span class="name">ALL</span>
		        </a>
		      </li>
		      <li>
		        <input name="itemSelect" type="checkbox" onclick="handleSelect()" class="check">
		        <input type="button" class="delete">
		        <a href="#" onclick="showLogDetail('IMSI|0000000000000')"> 
		          <div class="blockA">50</div>
		          <div class="blockB">2</div>
		          <span class="name">IMSI|0000000000000</span>
		        </a>
		      </li>
		      
		      <li>
		        <input name="itemSelect" type="checkbox" onclick="handleSelect()" class="check">
		        <input type="button" class="delete">
		        <a href="#" onclick="showLogDetail('IMSI|0000000000001')"> 
		          <div class="blockA">30</div>
		          <div class="blockB">8</div>
		          <span class="name">IMSI|0000000000001</span>
		        </a>
		      </li>
		      
		      <li>
		        <input name="itemSelect" type="checkbox" onclick="handleSelect()" class="check">
		        <input type="button" class="delete">
		        <a href="#" onclick="showLogDetail('IMSI|0000000000002')"> 
		          <div class="blockA">20</div>
		          <div class="blockB">1</div>
		          <span class="name">IMSI|0000000000002</span>
		        </a>
		      </li>
		      
		      <li>
		        <input name="itemSelect" type="checkbox" onclick="handleSelect()" class="check">
		        <input type="button" class="delete">
		        <a href="#" onclick="showLogDetail('IMSI|0000000000003')"> 
		          <div class="blockA">19</div>
		          <div class="blockB">3</div>
		          <span class="name">IMSI|0000000000003</span>
		        </a>
		      </li>
		      
		      <li>
		        <input name="itemSelect" type="checkbox" onclick="handleSelect()" class="check">
		        <input type="button" class="delete">
		        <a href="#" onclick="showLogDetail('IMSI|0000000000004')"> 
		          <div class="blockA">45</div>
		          <div class="blockB">11</div>
		          <span class="name">IMSI|0000000000004</span>
		        </a>
		      </li>
		      
          <li>
            <span href="#" style="top: 35%; left: 45%; position: absolute; font-size: 48px; cursor: pointer;" onclick="addNewMonitor()"> 
              +
            </span>
          </li>
		    </ol>
		  </div>
		</div>
  </div>
  <div id="consoleDiv" class="consolePnae" dojoType="dijit.layout.ContentPane" region="bottom"
    style="background-color:#DADADA; height: 100px; margin: 0px 20px;" splitter="true">
    console output............................................
  </div>
  
</div>

</body>
</html>