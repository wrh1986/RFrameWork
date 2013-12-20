package infocenter.servlet;

import infocenter.helper.ApplicationContext;
import infocenter.helper.ClientManager;
import infocenter.helper.ConfigurationException;
import infocenter.helper.ScriptConfiguration;
import infocenter.helper.ScriptRunTaskHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ActionServlet  extends HttpServlet{

  private Logger logger = LoggerFactory.getLogger(ActionServlet.class);
  public static final String CONFIG_PATH = "/var/camiant/tools/Configuration.xml";
  
  public void init() {
    ServletContext context = getServletContext();
    InputStream in = null;
    try {
      try {
        in = new FileInputStream(CONFIG_PATH);
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        logger.error(e.toString());
      }//context.getResourceAsStream("/configs/Configuration.xml");
      ApplicationContext.getInstance().setScriptConfig(new ScriptConfiguration(in));
    } catch (ConfigurationException e) {
      e.printStackTrace();
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

  }
  public void service (HttpServletRequest request, HttpServletResponse response){
    ScriptConfiguration config = ApplicationContext.getInstance().getScriptConfig();
    if(null == config) {
      this.init();
    }
    
    String clientId = request.getParameter("clientId");
    //String workspace = request.getParameter("workspace");
    if(!StringUtils.isEmpty(clientId)) {
      PrintWriter writer;
      try {
        writer = response.getWriter();
        synchronized (writer) {
          logger.debug("synchronized writer");
          ClientManager.get().add(clientId, writer);
          //ScriptRunTaskHelper.createProcess(clientId, config.getWorkspace(workspace));
          logger.debug("writer ready to wait");
          writer.wait();
        }
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return;
      }
    }
  } 
  
}
