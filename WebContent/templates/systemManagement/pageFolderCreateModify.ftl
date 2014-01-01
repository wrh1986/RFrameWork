<form id="systemManagement_pageFolderCreateModifyForm">
  <#if pageItem??>
    <input type="hidden" name="uid" value="${pageItem.uid}" />
    <input type="hidden" name="typeString" value="${pageItem.typeString}" />
  <#else>
    <input type="hidden" name="uid" value="" />
    <input type="hidden" name="typeString" value="F" />
  </#if>
  <table>
    <colgroup>
      <col width="200px"/>
      <col width="200px"/>
      <col width="*"/>
    </colgroup>
    <tr>
      <td>
        Name
      </td>
      <td>
        <#if pageItem??>
          <input name="name" type="text" value="${pageItem.name}">
        <#else>
          <input name="name" type="text" value="">
        </#if>
      </td>
    </tr>
    <tr>
      <td>
        description
      </td>
      <td>
        <#if pageItem??>
          <input name="description" type="text" value="${pageItem.description}">
        <#else>
          <input name="description" type="text" value="">
        </#if>
      </td>
    </tr>
  </table>
</form>
<div data-dojo-type="dijit.form.Button">保存
  <script type="dojo/on" data-dojo-event="click">
    require(['dojo/request/xhr', 'dijit/registry', "dojo/dom-form"], function(xhr, registry, domForm){
      var updateItem = domForm.toObject("systemManagement_pageFolderCreateModifyForm");
      xhr("systemManagement/pageFolderModifySubmit.action",{
        data: updateItem,
        method: "POST",
        handleAs: "json"
      }).then(
        function(data){
          if(data.result && data.result == 'success') {
            <#if pageItem??>
              registry.byId("pageManagement_tree").selectLeaf(${pageItem.uid});
              registry.byId("pageManagement_tree").updateNode(${pageItem.uid}, updateItem);
            <#else>
              if(data.uid) {
                updateItem.uid = uid;
              }
              registry.byId("pageManagement_tree").selectLeaf(${parentId});
              registry.byId("pageManagement_tree").addNode(updateItem, ${parentId});
            </#if>
          }
        },
        function(err){
          console.log(err);
          replaceData("pageManagement_main", err);
        });
    })
  </script>
</div>
<div data-dojo-type="dijit.form.Button">取消
  <script type="dojo/on" data-dojo-event="click">
    require(['dijit/registry'], function(registry){
      <#if pageItem??>
        registry.byId("pageManagement_tree").selectLeaf(${pageItem.uid});
      <#else>
        registry.byId("pageManagement_tree").selectLeaf(${parentId});
      </#if>
    })
  </script>
</div>