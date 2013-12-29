<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../common/common.jsp" %>
<r:base/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="<r:dojoPath/>/dijit/themes/tundra/tundra.css" rel="stylesheet" type="text/css" />
<link href="<r:dojoPath/>/dojo/resources/dojo.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<r:dojoPath/>/dojox/grid/resources/tundraGrid.css"> 

<style type="text/css">
.rolefieldset {
  padding: 5px;
  margin: 10px;
  border: 1px solid gray;
  height: 90%;
  width: 45%;
  float: left;
}
.userfieldset {
  padding: 5px;
  margin: 10px;
  border: 1px solid gray;
  height: 35%;
}

.actionicon{
  height: 90%;
  cursor: pointer;
}

.actionicon:hover{
  height: 100%;
  border: 1px solid gray;
}
</style>

<script>
require(['dojo/_base/array', 'dojo/_base/lang', 'dojo/on', 'dojox/grid/DataGrid', 'dojo/data/ItemFileWriteStore', 
         'dojo/dom', 'dijit/form/Button', 'dijit/registry', 'dojo/ready', 'dojo/domReady!'],
  function(array, lang, on, DataGrid, ItemFileWriteStore, dom, Button, registry, ready){

    var data = new ItemFileWriteStore({  
      url: "systemManagement/getAllUsers.action"
    });
    var data1 = new ItemFileWriteStore({  
      url: "systemManagement/getAllUsers.action"
    });
    var data2 = new ItemFileWriteStore({  
      url: "systemManagement/getAllUsers.action"
    });
    var store = new ItemFileWriteStore(data);
    var store1 = new ItemFileWriteStore(data1);
    var store2 = new ItemFileWriteStore(data2);
    
    store.onNew = function() {console.log("new");};  
    store.onDelete = function() {console.log("delete");};
    
    /*set up layout*/
    var layout = [
      {'name': '角色名称', 'field': 'name', 'width': '100px'},
      {'name': '角色描述', 'field': 'userName', 'width': '200px'},
    ];
    var userLayout = [
      {'name': '用户名称', 'field': 'name', 'width': '100px'},
      {'name': '登录名', 'field': 'name', 'width': '100px'},
    ];

    /*create a new grid*/
    var grid = new DataGrid({
        id: 'roleManagement_roleGrid',
        store: store,
        selectionMode: "single",
        structure: layout,
        rowSelector: '20px'}, "roleManagement_roleGridDiv");
    /*Call startup() to render the grid*/
    grid.startup();
    
    /*create a new grid*/
    var unallocatedGrid = new DataGrid({
        id: 'roleManagement_unallocatedUserGrid',
        store: store1,
        structure: userLayout,
        rowSelector: '20px'}, "roleManagement_unallocatedUserGridDiv");
    /*Call startup() to render the grid*/
    unallocatedGrid.startup();
    
    /*create a new grid*/
    var allocatedGrid = new DataGrid({
        id: 'roleManagement_allocatedUserGrid',
        store: store2,
        structure: userLayout,
        rowSelector: '20px'}, "roleManagement_allocatedUserGridDiv");
    /*Call startup() to render the grid*/
    allocatedGrid.startup();
});
</script>
</head>
<body>
  <fieldset class="rolefieldset">
    <legend>角色列表</legend>
    <div id="roleManagement_roleGridDiv" style="height:100%;"></div>
  </fieldset>
  <fieldset class="rolefieldset">
    <legend>用户分配</legend>
    <div style="height:100%;">
      <fieldset class="userfieldset">
        <legend>已分配列表</legend>
        <div id="roleManagement_allocatedUserGridDiv" style="height:100%"></div>
      </fieldset>
      <div style="height:25px; padding-left:25%;">
        <div style="height:100%; float:left; width:40px;">
          <img class="actionicon" src="pages/img/uppArrow.png"/>
        </div>
        <div style="height:100%; float:left; width:40px;">
          <img class="actionicon" src="pages/img/downArrow.png"/>
        </div>
        <div style="height:100%; float:left; width:40px;">
          <img class="actionicon" src="pages/img/delete.png"/>
        </div>
      </div>
      <fieldset class="userfieldset">
        <legend>未分配列表</legend>
        <div id="roleManagement_unallocatedUserGridDiv" style="height:100%;"></div>
      </fieldset>
    </div>
  </fieldset>
  
</body>
</html>