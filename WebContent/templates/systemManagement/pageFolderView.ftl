<#if pageAction == "modify">
  <textarea>
</#if>
<div class="toolBar">
  <div class="left">
    <div data-dojo-type="dijit.form.Button">新增页面
      <script type="dojo/on" data-dojo-event="click">
        require(['dojo/request/xhr'], function(xhr){
          url = "systemManagement/pageFolderModify.action";
          loadUrlData("pageManagement_main", url, {
            pageAction: "create",
            parentId: ${pageItem.uid}
          });
        })
      </script>
    </div>
    <div data-dojo-type="dijit.form.Button">编辑
      <script type="dojo/on" data-dojo-event="click">
        require(['dojo/request/xhr'], function(xhr){
          url = "systemManagement/pageFolderModify.action?pageAction=modify&id=${pageItem.uid}";
          loadUrlData("pageManagement_main", url);
        })
      </script>
    </div>
    <div data-dojo-type="dijit.form.Button">删除
      <script type="dojo/on" data-dojo-event="click">
        require(['dojo/request/xhr', 'dijit/registry'], function(xhr, registry){
          url = "systemManagement/pageFolderDelete.action?pageAction=delete&id=${pageItem.uid}";
          registry.byId("pageManagement_tree").removeNode();
        })
      </script>
    </div>
  </div>
</div>
<table>
  <colgroup>
    <col width="200px"/>
    <col width="200px"/>
    <col width="*"/>
  </colgroup>
  <tr>
    <td>目录名称12</td>
    <td>
      ${pageItem.name}
    </td>
  </tr>
  <tr>
    <td>描述</td>
    <td>
      ${pageItem.description}
    </td>
  </tr>
</table>
<#if pageAction == "modify">
  </textarea>
</#if>
