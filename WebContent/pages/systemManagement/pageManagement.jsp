<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../common/common.jsp" %>
<r:base/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<r:dojoPath/>/dojox/grid/resources/tundraGrid.css"> 

<title>Page Configuration</title>
<script type="text/javascript">
  require(['dojo/_base/window', 'dojo/store/Memory',
         'dijit/tree/ObjectStoreModel', 'dijit/Tree', 'dojo/store/Observable',
         'dojo/data/ItemFileWriteStore', 'dojo/store/DataStore', 'dijit/tree/ForestStoreModel', 
         'dojo/on', 'dojo/request/xhr', "dojo/dom-construct", 'dojo/domReady!'
         ], function(win, Memory, ObjectStoreModel, Tree, Observable, 
             ItemFileWriteStore, DataStore, ForestStoreModel, on, xhr, domConst){
	     
         // Create test store, adding the getChildren() method required by ObjectStoreModel
         var datastore = new ItemFileWriteStore({
           url: "systemManagement/getAllPages.action",
           clearOnClose: true
         });
         
         var treeModel = new ForestStoreModel({
             store: datastore,
             query: {type: "F"},
             rootId: "root",
             rootLabel: "根目录",
             childrenAttrs: ["children"]
         });

         // Create the Tree.   Note that all widget creation should be inside a dojo.ready().
         var tree = new Tree({
           model: treeModel,
           onClick: function(item, node, evt){
             if(item.root) {
               console.log(this.model.root);
             }
             else if(item.type == 'F') {
               var url = "systemManagement/pageFolderView.action?pageAction=view&id="+item.uid;
               loadUrlData("pageManagement_main", url);
             } else if(item.type == 'L') {
               var url = "systemManagement/pageLeafView.action?pageAction=view&id="+item.uid;
               loadUrlData("pageManagement_main", url);
             }
             
           },
           onLoad: function() {
             // dis-select all selected nodes
        	   this.set('selectedNodes',[]);
           },
           
           selectLeaf: function(/**item id**/id){
        	   treeModel.fetchItemByIdentity({
        		   identity: id,
        		   onItem: function(item) {
        			   console.log(item);
        			   var nodes = tree.getNodesByItem(item);
   	             tree.set('selectedNodes', nodes);
   	             if(item.root) {
   	               console.log(tree.model.root);
   	             }
   	             else if(item.type == 'F') {
   	               var url = "systemManagement/pageFolderView.action?pageAction=view&id="+item.uid;
   	               loadUrlData("pageManagement_main", url);
   	             } else if(item.type == 'L') {
   	               var url = "systemManagement/pageLeafView.action?pageAction=view&id="+item.uid;
   	               loadUrlData("pageManagement_main", url);
   	             }
        		   }
        	   });
           }
         }, "pageManagement_tree");
         tree.startup();
  });
</script>
</head>
<body class="tundra">
  <div dojoType="dijit.layout.BorderContainer"  design="headline">
    <div dojoType="dijit.layout.ContentPane" splitter="true"
            region="leading" style="width: 200px; height: 100%;">
      <div id="pageManagement_tree">
      
      </div>
    </div>
    <div id="pageManagement_main" dojoType="dijit.layout.ContentPane" region="center">
    </div>
  </div>
</body>
</html>