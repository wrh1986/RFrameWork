<form id="systemManagement_pageFolderCreateModifyForm">
  <#if pageItem??>
    <input type="hidden" name="uid" value="${pageItem.uid}" />
    <input type="hidden" name="type" value="${pageItem.type}" />
  <#else>
    <input type="hidden" name="uid" value="" />
    <input type="hidden" name="type" value="F" />
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
    require(["dojo/request/iframe"], function(iframe){
      iframe("systemManagement/pageFolderModifySubmit.action",{
        form: "systemManagement_pageFolderCreateModifyForm",
        method: "POST",
        handleAs: "text"
      }).then(
        function(data){
          replaceData("pageManagement_main", data);
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
      registry.byId("pageManagement_tree").selectLeaf(${pageItem.uid});
    })
  </script>
</div>