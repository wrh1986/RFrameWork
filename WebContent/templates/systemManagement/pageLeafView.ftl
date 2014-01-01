<div class="toolBar">
  <div class="left">
    <div data-dojo-type="dijit.form.Button">编辑
      <script type="dojo/on" data-dojo-event="click">
        require(['dojo/request/xhr'], function(xhr){
          xhr("systemManagement/pageLeafModify.action").then(
            function(data){
              replacePageContext(data);
            },
            function(err){
              replacePageContext(data);
            });
        })
      </script>
    </div>
    <div data-dojo-type="dijit.form.Button">删除
      <script type="dojo/on" data-dojo-event="click">
        require(['dojo/request/xhr', 'dijit/registry'], function(xhr, registry){
          
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
    <td>
      Name
    </td>
    <td>
      ${pageItem.name}
    </td>
  </tr>
  <tr>
    <td>
      Url
    </td>
    <td>
      
    </td>
  </tr>
  <tr>
    <td>
      description
    </td>
    <td>
      
    </td>
  </tr>
</table>