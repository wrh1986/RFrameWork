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
require(['dojo/_base/array', 'dojo/_base/lang', 'dojo/on', 'dojox/grid/DataGrid', 
         'dojo/data/ItemFileWriteStore', 'dijit/tree/ForestStoreModel', 'dijit/Tree',
         'dojo/dom', 'dijit/form/Button', 'dijit/registry', 'customlib/CheckBoxTree',
         'dojo/domReady!'],
  function(array, lang, on, DataGrid, ItemFileWriteStore, 
		  ForestStoreModel, Tree, dom, Button, registry, CheckBoxTree){

    var data = new ItemFileWriteStore({  
      url: "systemManagement/getAllUsers.action"
    });
    
    var store = new ItemFileWriteStore(data);
    
    store.onNew = function() {console.log("new");};  
    store.onDelete = function() {console.log("delete");};
    
    /*set up layout*/
    var layout = [
      {'name': '角色名称', 'field': 'name', 'width': '100px'},
      {'name': '角色描述', 'field': 'userName', 'width': '200px'},
    ];

    /*create a new grid*/
    var grid = new DataGrid({
        id: 'authManagement_roleGrid',
        store: store,
        selectionMode: "single",
        structure: layout,
        rowSelector: '20px'}, "authManagement_roleGridDiv");
    /*Call startup() to render the grid*/
    grid.startup();
    
    var datastore = new ItemFileWriteStore({
      url: "systemManagement/getAllPages.action"
    });
    
    var treeModel = new ForestStoreModel({
        store: datastore,
        query: {type: "F"},
        rootId: "root",
        rootLabel: "root",
        childrenAttrs: ["children"]
    });

    // Create the Tree.   Note that all widget creation should be inside a dojo.ready().
    var tree = new CheckBoxTree({
        model: treeModel,
        onClick: function(item, node, evt){
          
        }
    }, "authManagement_pageGridDiv");
    tree.startup();
    
});
</script>
</head>
<body>
  <fieldset class="rolefieldset">
    <legend>角色列表</legend>
    <div id="authManagement_roleGridDiv" style="height:100%;"></div>
  </fieldset>
  <fieldset class="rolefieldset">
    <legend>权限分配</legend>
    <div id="authManagement_pageGridDiv" style="height:100%;">
      
    </div>
  </fieldset>
  
</body>
</html>