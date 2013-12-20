package infocenter.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import infocenter.event.MessageQueue;
import infocenter.event.WarnMessage;
import infocenter.helper.ApplicationContext;
import infocenter.helper.ConfigurationException;
import infocenter.helper.ConfigurationWorkspace;
import infocenter.helper.ScriptConfiguration;
import infocenter.helper.ScriptRunTaskHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryServlet extends HttpServlet{

  private Logger logger = LoggerFactory.getLogger(QueryServlet.class);
  
  public void service (HttpServletRequest request, HttpServletResponse response){
    String action = request.getParameter("action");
    String clientId = request.getParameter("clientId");
    String workspace = request.getParameter("workspace");
    logger.info("Action is:{}, clientId:{}, workspace:{}", new String[]{action, clientId, workspace});
    try {
      response.getWriter().print(getResult(action, clientId, workspace));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

  private String getResult(String action, String clientId, String workspace) {
    String result = "";
    if("getWorkspace".equals(action)) {
      List<ConfigurationWorkspace> wks = ApplicationContext.getInstance().getScriptConfig().getWorkspaces();
      for(ConfigurationWorkspace wk : wks) {
        if(result.length() > 0) result += ",";
        result += wk.getName();
      }
    } else if("getClientId".equals(action)) {
      long time = Calendar.getInstance().getTimeInMillis();
      result = "client"+String.valueOf(time);
    } else if("getLink".equals(action)){
      result = ApplicationContext.getInstance().getScriptConfig().getCheckLink();
    } else if("reloadConfiguration".equals(action)){
      InputStream in = null;
      try {
        in = new FileInputStream(ActionServlet.CONFIG_PATH);
        ApplicationContext.getInstance().setScriptConfig(new ScriptConfiguration(in));
      } catch (ConfigurationException | FileNotFoundException e) {
        e.printStackTrace();
        logger.error(e.getMessage());
      } finally {
        if(null != in) {
          try {
            in.close();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    } else if("startProcess".equals(action)) {
      if(!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(workspace)) {
        ScriptConfiguration config = ApplicationContext.getInstance().getScriptConfig();
        ScriptRunTaskHelper.createProcess(clientId, config.getWorkspace(workspace));
      } else {
        MessageQueue.get(clientId).put(clientId, new WarnMessage("Warning, clientId or workspace is null!"));
        logger.warn("Warning, clientId or workspace is null!");
      }
    }
    return result;
  }
}
