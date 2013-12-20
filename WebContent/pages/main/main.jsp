<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../common/common.jsp" %>
<r:base/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title><s:text name="page.title.main"/></title>

<link href="<r:dojoPath/>/dijit/themes/tundra/tundra.css" rel="stylesheet" type="text/css" />
<link href="<r:dojoPath/>/dojo/resources/dojo.css" rel="stylesheet" type="text/css" />

<style type="text/css">
 html, body, #main{ 
  width: 100%; /* make the body expand to fill the visible window */
  height: 100%;
  overflow: hidden; /* erase window level scrollbars */
  padding: 0 0 0 0;
  margin: 0 0 0 0;
  font: 10pt Arial,Myriad,Tahoma,Verdana,sans-serif;
} 
</style>
<script type="text/javascript">  
  var dojoConfig = {  
    parseOnLoad: true,
    //isDebug: true,
    async: true,
    packages: [
                {
                  name: "customlib",
                  location: "../../custom"
                }
              ]
  };  
</script> 
<r:dojoPath dojo="true" />

<script type="text/javascript">
  var loadPage;
  var loadIFramePage;
  var loadUrlData;
  //replace div with data
  var replaceData;
  require(['dijit/layout/BorderContainer', 'dojox/layout/ContentPane', 'dijit/layout/AccordionContainer',
           'dijit/layout/TabContainer', 'dijit/form/Slider', 'dojo/fx', 'dojo/request/xhr', 'dojo/dom-construct',
           'dijit/registry'],
    function(BorderContainer, ContentPane, AccordionContainer, TabContainer, Slider, fx, xhr, domConst, registry){
	    loadPage = function(name, url) {
   	    var content = {
   	        id:name,
   	        title:name , 
   	        href:url ,
   	        executeScripts:true
   	    };
   	    var container = registry.byId("mainContainer");
   	    var newPage = registry.byId(name);
   	    if(newPage){
   	      newPage.refresh();
   	    } else {
   	      newPage = new ContentPane(content);
   	      newPage.set("closable", true);
   	      container.addChild(newPage);
   	    }
   	    container.selectChild(newPage);
   	  };
   	  
   	  loadIFramePage = function (name, url) {
   	    var content = {
   	         id:name,
   	         title:name , 
   	         executeScripts:true,
   	         href:"pages/main/mainFrameInner.jsp?url=" + url
   	    };
   	    var container = registry.byId("mainContainer");
   	    var newPage = registry.byId(name);
   	    if(newPage){
   	      newPage.refresh();
   	    } else {
   	      newPage = new ContentPane(content);
   	      newPage.set("closable", true);
   	      container.addChild(newPage);
   	    }
   	    container.selectChild(newPage);
   	  };
   	   
   	  loadUrlData = function(destDivString, url) {
   		  xhr(url).then(
   	      function(data){
   	    	  domConst.empty(destDivString);
   	        //domConst.place(data, "pageManagement_main");
   	        var content = {
   	          content: data,
   	          executeScripts:true
   	        };
   	        newPage = new ContentPane(content);
   	        newPage.placeAt(destDivString);
   	      },
          function(err){
            console.log(err);
          }
   	    );
      };
      
      replaceData = function(destDivString, data) {
    	  domConst.empty(destDivString);
        //domConst.place(data, "pageManagement_main");
        var content = {
          content: data,
          executeScripts:true
        };
        newPage = ContentPane(content);
        newPage.placeAt(destDivString);
      }
    }
  );
  
</script>
</head>
<body class="tundra">
<div dojoType="dijit.layout.BorderContainer" design="headline" id="main">
  <div dojoType="dijit.layout.ContentPane" region="top"
    style="background-color: #FFFFFF; height: 100px;" href="pages/main/mainTitle.jsp">
  </div>
  <div dojoType="dijit.layout.ContentPane" splitter="true"
          region="leading" style="width: 200px; height: 100%;">
    <div dojoType="dojox.layout.ContentPane" parseOnLoad="true" href="pages/main/mainMenu.jsp"></div>
  </div>
  <div id="mainContainer" dojoType="dijit.layout.TabContainer" region="center">
  </div>
  
  <div dojoType="dijit.layout.ContentPane" region="bottom"
    style="background-color: #FFFFFF; height: 25px;" splitter="true">
    <a href="http://api.dojotoolkit.org/" target="_blank">dojotoolkit</a>
    <a href="http://localhost:8080/MyWeb/js/dojo-release-1.8.0/dijit/themes/themeTester.html" target="_blank">ThemeTester</a>
  </div>
</div>
</body>
</html>