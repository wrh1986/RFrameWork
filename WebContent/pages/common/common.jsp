<%@ taglib prefix="r" uri="/RFtaglib" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
button{
  /* enabled state - inner */
  border: 1px solid #c0c0c0;
  border-bottom: 1px solid #9b9b9b;
  padding: 0.1em 0.2em 0.2em 0.2em;
  background: url("<r:dojoPath/>/dijit/themes/tundra/images/buttonEnabled.png") repeat-x scroll left bottom #FFFFFF;
  text-align: center;
  margin: 0.2em;
  padding: 0.1em 0.2em;
  vertical-align: middle;
  cursor: pointer;
}

button:hover {
  border-color: #a5beda;
  border-bottom-color:#5c7590;
  color:#243C5F;
  background:#fcfdff url("<r:dojoPath/>/dijit/themes/tundra/images/buttonHover.png") repeat-x bottom;
}

.toolBar {
  background-color: #e0e0e0;
}

.toolBar .left {
  text-align: left;
}

.toolBar .right {
  text-align: right;
}

.dijitTreeCheckBox {
  vertical-align: middle;
}
</style>