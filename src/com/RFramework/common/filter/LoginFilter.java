package com.RFramework.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.RFramework.common.app.AppConstants;

public class LoginFilter implements Filter {
  protected final static String LOGIN_PAGE = "/login.action";
  private List<String> noFilterList = new ArrayList<String>();
  protected FilterConfig filterConfig;
  
  public void setFilterConfig(final FilterConfig filterConfig) {
          this.filterConfig = filterConfig;
  }

  /**
   * 销毁过滤器
   */
  public void destroy() {
          this.filterConfig = null;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // TODO Auto-generated method stub
    this.filterConfig = filterConfig;
    noFilterList.add(filterConfig.getServletContext().getContextPath() + "/login.action");
    noFilterList.add(filterConfig.getServletContext().getContextPath() + "/authCheck.action");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    // TODO Auto-generated method stub
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    if(noFilterList.indexOf(httpRequest.getRequestURI()) == -1 ) {
      HttpSession session = httpRequest.getSession();
      try {
              String user = (String) session.getAttribute(AppConstants.USERNAME);
              if (user != null) {
                      chain.doFilter(request, response);
                      return;
              } else {
                      httpResponse.sendRedirect(request.getServletContext().getContextPath() + LOGIN_PAGE);
                      return;
              }
      } catch (Exception e) {
              e.printStackTrace();
      }
    }
    chain.doFilter(request, response);
  }

}

