<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../common/common.jsp" %>
<r:base/>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8 "/>
<title>Online Help Check Tool</title>

<link href="<r:dojoPath/>/dijit/themes/claro/claro.css" rel="stylesheet" type="text/css" />
<link href="<r:dojoPath/>/dojox/form/resources/FileUploader.css" rel="stylesheet" type="text/css" />
<link href="<r:dojoPath/>/dojox/form/resources/UploaderFileList.css" rel="stylesheet" type="text/css" />

<style type="text/css">
fieldset {
  padding: 10px;
  margin: 15px;
}
</style>

<r:dojoPath dojo="true" />

<script type="text/javascript">
  dojo.require("dojo.parser");
  dojo.require("dojox.form.Uploader");
  //if(dojo.isIE) dojo.require("dojox.form.uploader.Flash");
  //else dojo.require("dojox.form.uploader.HTML5");
  //dojo.require("dojox.form.uploader.plugins.IFrame");
  var flash = false;
  dojo.require("dojox.form.uploader.plugins.IFrame");
  
  dojo.require("dojox.form.uploader.FileList");
  dojo.require("dijit.form.CheckBox");
  dojo.require("dijit.form.Button");
  dojo.require("dijit.form.SimpleTextarea");
  dojo.require("dijit.ProgressBar");
  dojo.require("dojo.request.xhr");

  var hasWarnning = false;
  
  dojo.ready(function(){
    var workspace = "<c:out value='${param.workspace}'/>";
    var clientId = "-1";
    
    //not support IE any more
    if(dojo.isIE){
        //dojo.require("dojox.form.uploader.plugins.Flash");
        //flash = true;
        showWarn("IE is not supported any more.\nPlease use Firefox instead of IE for this tool.");
      }else{
        dojo.require("dojox.form.uploader.plugins.IFrame");
        flash = false;
      }
    dojo.request.xhr("query.q?action=getClientId")
    .then(
        function(data){
          clientId = data;
          var uploader = dijit.byId("uploader");
          uploader.url = uploader.url + "&clientId=" + clientId ;
        }, 
        function(err) {
          
        });
    
    dojo.request.xhr("query.q?action=getLink")
      .then(
          function(urlStr){
            dojo.byId("checkLink").href = urlStr;
          }, 
          function(err) {
            showMsg(err);
          });
    
    var uploader = dijit.byId("uploader");
    uploader.url = uploader.url + "?workspace="+workspace + "&flash=" + flash ;
    
    dojo.connect(dijit.byId("uploader"), "onComplete", function(data){
      //alert(data.files);
      if(data.files != undefined) {
        showMsg('The files:['+data.files+'] upload successfully!');
      }
      else{
        showMsg('The files upload successfully!');
      }
      dojo.request.xhr("query.q?action=startProcess&clientId="+clientId+"&workspace="+workspace)
      .then(
          function(data){
        	  hasWarnning = false;
            this.connect();
          }, 
          function(err) {
            showMsg(err);
          });
    });
    
    dojo.connect(dijit.byId("uploader"), "onError", function(evtObject){
      showMsg('The files upload failed!');
      //var div = dojo.create('div', {className:'thumb'});
          //dojo.create('span', {className:'thumbbk', innerHTML: 'The files upload failed!'}, div);
          //dojo.byId("result").appendChild(div);
    });
    
    this.connect = function() {
      dojo.request.xhr("action.a?clientId="+clientId+"&workspace="+workspace+"&timestamp="+new Date().getTime(), 
          {
            handleAs: "json"
          }
      ).then(
          function(data){
            if(data.type){
              if(data.type != 'finish'){
                connect();
              }
              handleData(data);
            }
          },
          function(err){
            showMsg(err);
          });
    };
    function handleData(data) {
      if(data.type == 'finish') {
        showMsg(data.content);
        if(!hasWarnning){
          dojo.byId("TipBox").style.display = "none";
        }
      } else if(data.type == 'info') {
        showMsg(data.content);
      } else if(data.type == 'warn') {
        showMsg(data.content);
        showWarn(data.content);
        //refresh data in warn
      } else if(data.type == 'error') {
        showMsg(data.content);
        showError(data.content);
        //refresh data in error
      } else if(data.type == 'comm') {
        showMsg(data.content);
        showComm(data.content);
      }
    }
  });
  
  
  function showMsg(msg) {
    var console = dijit.byId("console");
    console.set("value",console.get("value")+"\n\n"+msg);
    dojo.byId("console").scrollTop = dojo.byId("console").scrollHeight;
  }
  
  function showError(msg) {
    var content = msg;
    hasWarnning = true;
    if(content.length > 100){
      content = content.substr(0,100) + "<...more>";
    }

    dojo.byId("TipBox").innerHTML = content;
    new dijit.form.Button({
      label: "Check!",
      onClick: function(){
        dojo.byId("TipBox").style.display = "none";
        this.destroy();
      }
    }, "checkButtonError").placeAt("TipBox");

    dojo.byId("TipBox").style.backgroundColor = "red";
    dojo.byId("TipBox").style.display = "";
  }
  
  function showWarn(msg) {
    var content = msg;
    //hasWarnning = true;
    if(content.length > 100){
      content = content.substr(0,100) + "<...more>";
    }
    dojo.byId("TipBox").innerHTML = content;
    /*
    new dijit.form.Button({
      label: "Check!",
      onClick: function(){
        dojo.byId("TipBox").style.display = "none";
        dijit.byId("checkButtonWarn").destroy();
      }
    }, "checkButtonWarn").placeAt("TipBox");
    */
    dojo.byId("TipBox").style.backgroundColor = "darkorange";
    dojo.byId("TipBox").style.display = "";
  }
  
  function showComm(msg) {
    var content = msg;
    if(content.length > 50){
      content = content.substr(0,50) + "<...more>";
    }
    dojo.byId("TipBox").style.backgroundColor = "gray";
    dojo.byId("TipBox").innerHTML = content;
    dojo.byId("TipBox").style.display = "";
  }

</script>
</head>
<body class="claro">
  <div style="padding: 8px;">
  <h1>Online Help Check Tool ( Version 0.02 ) __ <c:out value='${param.workspace}'/></h1>
  <hr />
  <fieldset> 
    <legend>Upload files</legend>
    <form method="post" action="service.u" id="myForm" enctype="multipart/form-data" >
        <input name="uploadedfile" multiple="true" type="file" data-dojo-type="dojox.form.Uploader" label="Select Some Files" id="uploader" />
        <input type="submit" label="Submit" data-dojo-type="dijit.form.Button"/>
        <div id="files" data-dojo-type="dojox.form.uploader.FileList" uploaderId="uploader"></div>
    </form>
    <div id="result"></div>
  </fieldset>
  
  <fieldset>
    <div>
    <label>Console:</label>
    </div>
    <textarea id="console" name="console" data-dojo-type="dijit.form.SimpleTextarea" rows="20" cols="50" style="width:90%;">Console is ready!</textarea>
  </fieldset>
  
  <fieldset>
    <div>
      <a id="checkLink" href="" target="_blank">Click me for checking!</a>
    </div>
  </fieldset>
  </div>
  
  <div id="TipBox" style="display: none; position: fixed; top:0px; left:0px; filter:alpha(Opacity=80); -moz-opacity:0.6;opacity: 0.6; 
        width: 100%; height: 100%; background-color: gray; z-index: 999; text-align: center;
        font-size: 68px; color: #fee100; padding-top: 15%;">
    
  </div>
  
  
</body>
</html>