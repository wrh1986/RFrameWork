<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>subscriberTracingSetting.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()+
            request.getServletContext().getInitParameter("dojoPath") %>/dojo/dojo.js" djconfig="parseOnLoad:true"></script>
<style type="text/css">
td{
  border-bottom:1px solid black ;
  text-align: center;
} 
.td_exception {
  text-align: left;
}
</style>
<script type="text/javascript">

var curActiveRow;

function addSubscriber() {
  var tr1 = document.createElement("tr");
  dojo.connect(tr1,'click',function(){
    dojo.byId("type").value = tr1.childNodes[0].innerHTML;
    dojo.byId("value").value = tr1.childNodes[1].innerHTML;
    dojo.byId("enableSubscriber").value = tr1.childNodes[2].innerHTML;
    
    if(curActiveRow) {
      curActiveRow.style.backgroundColor = '';
    }
    curActiveRow = tr1;
    curActiveRow.style.backgroundColor = 'yellow';
    //tr.style.backgroundColor='#0066cc';
  });
  var td1 = "<td>"+dojo.byId("type").value+"</td>";
  var td2 = "<td class=\"td_exception\">"+dojo.byId("value").value+"</td>";
  var td3 = "<td><input type='checkbox'></td>";
  var td4 = "<td><img src=\"../img/delete3.png\"></td>";
  tr1.innerHTML = td1 + td2 + td3 +td4;
  dojo.byId("subscriberTable").appendChild(tr1);
}
</script>
</head>
<body>

<div>
  <span>Enable Tracing</span>
  <input type="checkbox">
  <!-- html:checkbox property="enableTracing"/> -->
</div>
<br>

<div>
  <table id="subscriberTable" cellpadding="0" cellspacing="0">
    <colgroup span="1" width="225"></colgroup>
    <colgroup span="1" width="225"></colgroup>
    <colgroup span="1" width="225"></colgroup>
    <colgroup span="1" width="225"></colgroup>
    <tr>
      <th>Identifier Type</th>
      <th>Identifier Value</th>
      <th>Enable</th>
      <th>Action</th>
    </tr>
  </table>
  
</div>
<br>
<br>
<hr>
<div>
  <span>Identifier Type</span>
  <select id="type">
    <option value="IMSI" >IMSI</option>
    <option value="E.164">E.164</option>
    <option value="NAI" >NAI</option>
  </select>
  <span style="margin-left: 60px">Identifier Value</span>
  <input type="text" id="value"/>
  <span style="margin-left: 60px">Enable/Disable</span>
  <input type="checkbox" id="enableSubscriber"/>
  <!-- html:checkbox property="enableSubscriber"/> -->
  
  <input type="button" value="Add/Modify" onclick="addSubscriber()">
</div>
<hr>
<div>
  <input type="button" value="Save" />
  <input type="button" value="Cancel" />
</div>
</body>
</html>