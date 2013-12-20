package com.RFramework.utils.tags;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class RFDojoPathTag extends TagSupport {

  /**
   * 
   */
  private static final long serialVersionUID = -6407593121814556575L;

  private static final String DOJOPATH = "/js/dojo-release-1.8.0";

  private String dojo;
  
  @Override
  public int doStartTag() throws JspException {
    try {
      ServletRequest request = this.pageContext.getRequest();
      String path = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getServletContext().getContextPath() + DOJOPATH;
      if("true".equals(dojo)) {
        path = "<script type=\"text/javascript\" src=\"" + path 
            + "/dojo/dojo.js\" ></script>";
      }
      JspWriter out = this.pageContext.getOut();
      out.print(path);
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

  public String getDojo() {
    return dojo;
  }

  public void setDojo(String dojo) {
    this.dojo = dojo;
  }

}
