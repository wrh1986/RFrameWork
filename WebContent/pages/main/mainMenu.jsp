<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../common/common.jsp" %>
<r:base/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mainMenu.jsp</title>
<style type="text/css">
  .MenuTotal {
    width: 100%;
    padding-top: 5px;
  }
  
  .MenuTotal .MenuTitle {
    width: 100%;
    height: 25px;
    font-size: 16px;
    cursor: pointer;
    background-image: url(pages/img/menu_unselected.gif);
    background-repeat: repeat-x;
  }
  
  .MenuTotal .MenuTitle span {
    margin-left: 15px;
  }
  
  .MenuTotal .MenuLevel1 {
    width: 100%;
    font-size:12px;
  }
  
  .MenuTotal .MenuLevel1 ul {
    width: 100%;
    list-style: none;
    margin: 0;
    padding: 0;
  }
  
  .MenuTotal .MenuLevel1 ul li {
    cursor: pointer;
    background-repeat: repeat-x;
    line-height: 20px;
  }
  
  .MenuTotal .MenuLevel1 ul li:hover{
    border: solid 1px;
    background-color: #E9E9E9;
  }
  
  .MenuTotal .MenuLevel1 ul li span {
    margin-left: 25px;
  }
</style>
<script type="text/javascript">
  require(['dojo/data/ItemFileReadStore', 'dojo/request/xhr', 'dojo/dom-construct', 'dojo/domReady!'], 
    function(ItemFileReadStore, xhr, domConst){
      xhr("loadMenuPage.action").then(
        function(data){
          domConst.place(data, "menuDiv");
        },
        function(err){
          domConst.place(data, "menuDiv");
        });
    }
  );
  
  function handleMenuTotal(id){
    var menuBlock = dojo.byId("menuTotal"+id);
    if(menuBlock.style.display == 'none'){
       dojo.fx.wipeIn({ 
         node: menuBlock, 
         duration: 500 
       }).play();
    } else {
      dojo.fx.wipeOut({ 
        node: menuBlock, 
        duration: 500 
      }).play();
    }
  }

</script>
</head>
<body>
  <div id="menuDiv"></div>
</body>
</html>