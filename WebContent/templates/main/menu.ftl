<div class="MenuTotal">
  <div class="MenuTitle" onClick="handleMenuTotal(1)">
    <span>系统管理</span>
  </div>
  <div class="MenuLevel1" id="menuTotal1">
    <ul>
      <li onclick="loadPage('用户管理','pages/systemManagement/userManagement.jsp')" ><span>用户管理</span></li>
      <li onclick="loadPage('菜单管理','pages/systemManagement/pageManagement.jsp')" ><span>菜单管理</span></li>
      <li onclick="loadPage('角色管理','pages/systemManagement/roleManagement.jsp')" ><span>角色管理</span></li>
      <li onclick="loadPage('权限管理','pages/systemManagement/authManagement.jsp')" ><span>权限管理</span></li>
    </ul>
  </div>
</div>