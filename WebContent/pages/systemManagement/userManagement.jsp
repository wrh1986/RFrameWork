<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../common/common.jsp" %>
<r:base/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<r:dojoPath/>/dijit/themes/tundra/tundra.css" rel="stylesheet" type="text/css" />
<link href="<r:dojoPath/>/dojo/resources/dojo.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<r:dojoPath/>/dojox/grid/resources/tundraGrid.css"> 

<style type="text/css">
.queryForm {
  margin-bottom: 15px;
}
</style>

<title>User Configuration</title>

<script type="text/javascript">
  require(['dojo/_base/array', 'dojo/_base/lang', 'dojo/on', 'dojox/grid/DataGrid', 'dojo/data/ItemFileWriteStore', 
           'dojo/dom', 'dijit/form/Button', 'dijit/registry', 'dojo/ready', 'dojo/domReady!'],
    function(array, lang, on, DataGrid, ItemFileWriteStore, dom, Button, registry, ready){

		  var data = new ItemFileWriteStore({  
			  url: "systemManagement/getAllUsers.action"
			});
      var store = new ItemFileWriteStore(data);
      
      store.onNew = function() {console.log("new");};  
      store.onDelete = function() {console.log("delete");};
      
      /*set up layout*/
      var layout = [[
        {'name': '用户名称', 'field': 'name', 'width': '100px'},
        {'name': '登陆名', 'field': 'userName', 'width': '200px'},
        {'name': '最近登录时间', 'field': 'formatedLastLoginTime', 'width': '150px'},
        {'name': '创建时间', 'field': 'formatedCreateTime', 'width': '150px'}
      ]];

      /*create a new grid*/
      var grid = new DataGrid({
          id: 'userManagement_userGrid',
          store: store,
          selectionMode: "single",
          structure: layout,
          rowSelector: '20px'});

      /*append the new grid to the div*/
      grid.placeAt("userManagement_gridDiv");

      /*Call startup() to render the grid*/
      grid.startup();
      
      var addBtn = new Button({
    	  label: "新增",
        onClick: function(){
          userManagement_name.set("value", "");
          userManagement_userName.set("value", "");
          userManagement_addUserDialog.set("title", "新增用户");
          userManagement_addUserDialog.set("modify", false);
          userManagement_addUserDialog.show();
        }
      }).placeAt("userManagement_toolBar_left");
      
      var modifyBtn = new Button({
        label: "修改",
        onClick: function(){
        	var items = grid.selection.getSelected();
          if(items.length && items.length > 0){
        	  var selectedItem = items[0];
        	  userManagement_name.set("value", selectedItem.name);
            userManagement_userName.set("value", selectedItem.userName);
            userManagement_addUserDialog.set("title", "修改用户");
            userManagement_addUserDialog.set("modify", true);
            userManagement_addUserDialog.show();
          } else {
        	  alert("请选择一条记录！");
          }
        }
      }).placeAt("userManagement_toolBar_left");
      

      var deleteBtn = new Button({
        label: "删除",
        onClick: function(){
 	        /* Get all selected items from the Grid: */
 	        var items = grid.selection.getSelected();
 	        if(items.length){
 	            /* Iterate through the list of selected items.
 	               The current item is available in the variable
 	               "selectedItem" within the following function: */
 	            array.forEach(items, function(selectedItem){
 	                if(selectedItem !== null){
 	                    /* Delete the item from the data store: */
 	                    store.deleteItem(selectedItem);
 	                } /* end if */
 	            }); /* end forEach */
 	        } /* end if */
 	        //event.stop(e);
        }
      }).placeAt("userManagement_toolBar_left");

      var resetPwdBtn = new Button({
        label: "重置密码",
        onClick: function(){
          userManagement_addUserDialog.show();
        }
      }).placeAt("userManagement_toolBar_left");
      
      
      on(dom.byId('userManagement_addUserConfirm'), 'click', function(e){
        if(userManagement_addUserDialog.get("modify")){
        	var items = grid.selection.getSelected();
          if(items.length && items.length > 0){
            var selectedItem = items[0];
            store.setValue(selectedItem, 'name', userManagement_name.get("value"));
            store.setValue(selectedItem, 'userName', userManagement_userName.get("value"));
          }
        } else {
        	var newItem = {
              'name': userManagement_name.get("value"),
              'userName': userManagement_userName.get("value"),
              'formatedLastLoginTime':'',
              'formatedCreateTime':''
          };
          store.newItem(newItem);
        }
        userManagement_addUserDialog.hide();
      });
      
      on(dom.byId("userManagement_userQuery"), 'click', function(e){
    	  var name = userManagement_queryName.get("value");
    	  var userName = userManagement_queryUserName.get("value");
    	  grid.filter({
    		  "name":"*"+name+"*",
    		  "userName":"*"+userName+"*"
    	  });
      });
  });

</script>
</head>
<body class="tundra">
  <div class="queryForm">
  <table width="100%">
    <colgroup>
      <col width="100px"/>
      <col width="200px"/>
      <col width="100px"/>
      <col width="200px"/>
      <col width="*"/>
    </colgroup>
    <tr>
      <td>登录名</td>
      <td><input data-dojo-type="dijit/form/TextBox" name="userManagement_queryName" data-dojo-id="userManagement_queryName"/></td>
      <td>用户名称</td>
      <td><input data-dojo-type="dijit/form/TextBox" name="userManagement_queryUserName" data-dojo-id="userManagement_queryUserName"/></td>
      <td><button id="userManagement_userQuery">查询</button></td>
    </tr>
  </table>
  </div>
  <div class="toolBar">
    <div id="userManagement_toolBar_left" class="left">
    </div>
  </div>
  <div id="userManagement_gridDiv" style="height:80%;"></div>
  
  <div data-dojo-type="dijit/Dialog" data-dojo-id="userManagement_addUserDialog" title="新增用户">
    <table class="dijitDialogPaneContentArea">
      <tr>
        <td><label for="name">用户名称:</label></td>
        <td><input data-dojo-type="dijit/form/TextBox" name="name" data-dojo-id="userManagement_name"></td>
      </tr>
      <tr>
        <td><label for="userName">登录名:</label></td>
        <td><input data-dojo-type="dijit/form/TextBox" name="userName" data-dojo-id="userManagement_userName"></td>
      </tr>
    </table>

    <div class="dijitDialogPaneActionBar">
        <button id="userManagement_addUserConfirm">确认</button>
        <button onClick="userManagement_addUserDialog.onCancel();">取消</button>
    </div>
  </div>
</body>
</html>