<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../common/common.jsp" %>
<r:base/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8 "/>
<title>Online Help Check Tool</title>

<r:dojoPath dojo="true" />

<style type="text/css">

ol {
  list-style: none inside none;
}

li {
  list-style: none inside none;
}

#grid ol li {
  border: 1px solid #D8D8D8;
  float: left;
  position: relative;
  height: 100px;
  margin: 20px;
  margin-left: 100px;
  width: 320px;
  FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#A8D3FF,endColorStr=#FFFFFF);/*IE6*/
  background:-moz-linear-gradient(top,#A8D3FF,#FFFFFF);
  background:-webkit-gradient(linear, 0% 0%, 0% 100%, from(#A8D3FF), to(#FFFFFF)); 
  border-radius:10;
  font-size: 32px;
  line-height:45px;
  padding-left: 25px;
  padding-top: 25px;
  color: darkViolet;
  cursor: pointer;
}

</style>

<script type="text/javascript" src="<%=request.getContextPath()+
            request.getServletContext().getInitParameter("dojoPath") %>/dojo/dojo.js" djconfig="parseOnLoad:true"></script>
            
<script type="text/javascript">

  dojo.ready(function(){
	  dojo.xhrGet({ 
      url:"query.q?action=getWorkspace",
      handleAs:"text",
      load:function(response,ioArgs){
        var names = response.split(","); 
        for(var index in names) { 
        	addWorkspace(names[index]);
        }
      },
      error:function(response,ioArgs){
        console.error("HTTP status code: ", ioArgs.xhr.status); 
          console.log(response);
      }
    });
	  
	  dojo.connect(dojo.byId("reloadButton"), "onclick", function(evt) {
		  dojo.xhrGet({ 
	      url:"query.q?action=reloadConfiguration",
	      handleAs:"text",
	      load:function(response,ioArgs){
	        window.location.reload();
	      },
	      error:function(response,ioArgs){
	    	  alert("Load failed!");
	        console.log(response);
	      }
	    });
	  });
	  
	  function addWorkspace(name) {
	    var newWorkspace = dojo.doc.createElement("li")
	    newWorkspace.innerHTML = name;
	    dojo.connect(newWorkspace, "onclick", function(evt){
	    	selectWorkspace(name);
	    });
	    dojo.byId("workspaceSelection").appendChild(newWorkspace);
	  }
	  
	  function selectWorkspace(name) {
      window.location = "pages/infocenter/infocenterActionPage.jsp?workspace="+name;
	    //console.log(111);
	  }
	  
  });

</script>
</head>
<body class="claro">
  <div style="padding: 8px;">
	  <h1>Online Help Check Tool</h1>
	  <hr />
	  <div style="font-size: 32px;">
	    <button id="reloadButton">Reload Configuration</button>
	  </div>
	  <div id="grid">
	    <ol id="workspaceSelection">
	    </ol>
	  </div>
	</div>
</body>
</html>