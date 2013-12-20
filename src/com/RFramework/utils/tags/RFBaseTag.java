package com.RFramework.utils.tags;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class RFBaseTag extends TagSupport {
  /**
   * 
   */
  private static final long serialVersionUID = 8515897433696857574L;

  @Override
  public int doStartTag() throws JspException {
    try {
      ServletRequest request = this.pageContext.getRequest();
      String path = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getServletContext().getContextPath() + "/";
      JspWriter out = this.pageContext.getOut();
      out.println("<base href=\""+path+"\">");
    } catch(Exception e) {
      throw new JspException(e.getMessage());
    }
    return SKIP_BODY;
  }
 
  @Override
  public int doEndTag() throws JspException {
      return EVAL_PAGE;
  }



  @Override
  public void release() {
      super.release();
  }

}
