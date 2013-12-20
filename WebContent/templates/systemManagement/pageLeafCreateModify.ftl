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
      <input type="text">
    </td>
  </tr>
  <tr>
    <td>
      Url
    </td>
    <td>
      <input type="text">
    </td>
  </tr>
  <tr>
    <td>
      description
    </td>
    <td>
      <input type="text">
    </td>
  </tr>
</table>
<div data-dojo-type="dijit.form.Button">保存
  <script type="dojo/on" data-dojo-event="click">
    require(['dojo/request/xhr'], function(xhr){
      xhr("systemManagement/pageLeafView.action").then(
        function(data){
          replacePageContext(data);
        },
        function(err){
          replacePageContext(data);
        });
    })
  </script>
</div>
<div data-dojo-type="dijit.form.Button">取消
  <script type="dojo/on" data-dojo-event="click">
    require(['dojo/request/xhr'], function(xhr){
      xhr("systemManagement/pageLeafView.action").then(
        function(data){
          replacePageContext(data);
        },
        function(err){
          replacePageContext(data);
        });
    })
  </script>
</div>