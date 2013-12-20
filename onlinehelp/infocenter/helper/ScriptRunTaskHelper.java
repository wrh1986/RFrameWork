package infocenter.helper;

import infocenter.event.CommunicationMessage;
import infocenter.event.ErrorMessage;
import infocenter.event.FinishMessage;
import infocenter.event.MessageQueue;
import infocenter.event.PrintMessage;
import infocenter.event.WarnMessage;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.RFramework.utils.script.ScriptRunner;

import com.RFramework.utils.script.handler.DefaultResultHandler;
import com.RFramework.utils.script.loader.FileScriptLoader;
import com.RFramework.utils.script.loader.ScriptLoader;

public class ScriptRunTaskHelper {

  private static Logger logger = LoggerFactory.getLogger(ScriptRunTaskHelper.class);
  private static void run(ConfigurationWorkspace wk, ConfigurationTask task, StringWriter out, StringWriter errOut) throws Exception{
    logger.info("Start task:{}, workspace:{}",task.getName(), wk.getName());
    try {
      ScriptRunner runner = new ScriptRunner();
      //get file's full name
      String fileName = ApplicationContext.getInstance().getWebAppPath() + task.getFileName();
      ScriptLoader scriptLoader = new FileScriptLoader(fileName, runner.convertType(task.getScriptType()));
      //create result handler
      DefaultResultHandler handler = new DefaultResultHandler();
      handler.setWriter(out);
      handler.setErrorWriter(errOut);
      //get parameter map
      Map paraMap = task.getParameterMap();
      //add workspace
      paraMap.put("workspace", wk.getPath());
      
      runner.setScriptLoader(scriptLoader);
      //execute
      runner.execute(paraMap, handler);
    } catch (Exception e) {
      out.write("Task:[" + task.getName() + "] failed!");
      logger.error("Task " + task.getName() + "failed!");
      //e.fillInStackTrace(out);
      throw e;
    }
  }
  
  public static void createProcess(final String clientId, final ConfigurationWorkspace wk) {
    logger.info("Try to create process");
    if(!wk.isWorking()) {
      synchronized(wk) {
        if(!wk.isWorking()) {
          wk.setWorking(true);
          new Thread(){
            public void run() {
              logger.info("Pocess start");
              ScriptConfiguration cfg = ApplicationContext.getInstance().getScriptConfig();
              List<String> process = cfg.getProcess();
              for(String taskName: process) {
                ConfigurationTask task = cfg.getTask(taskName);
                MessageQueue.get(clientId).put(clientId, new CommunicationMessage("Task:"+task.getName()+" is in process..."));
                StringWriter writer = new StringWriter();
                StringWriter errWriter = new StringWriter();
                try {
                  ScriptRunTaskHelper.run(wk, task, writer, errWriter);
                  if(writer.getBuffer().length() > 0){
                    String result = writer.toString();
                    if(result.indexOf("BUILD FAILED") != -1) {
                      MessageQueue.get(clientId).put(clientId, new ErrorMessage(result));
                      logger.error(result.toString());
                      break;
                    } else {
                      MessageQueue.get(clientId).put(clientId, new PrintMessage(result));
                      logger.info(result.toString());
                    }
                  }
                  if(errWriter.getBuffer().length() > 0){
                    MessageQueue.get(clientId).put(clientId, new ErrorMessage(errWriter.toString()));
                    logger.error(errWriter.toString());
                    break;
                  }
                } catch (Exception e) {
                  MessageQueue.get(clientId).put(clientId, new ErrorMessage(e.toString()));
                  logger.error(e.toString());
                  break;
                }
              }
              logger.info("Pocess finish");
              MessageQueue.get(clientId).put(clientId, new FinishMessage("Finished"));
              wk.setWorking(false);
            }
          }.start();
        } else {
          MessageQueue.get(clientId).put(clientId, new WarnMessage("Somebody else is running build in this workspace right now, please wait a few minutes and try again."));
          MessageQueue.get(clientId).put(clientId, new FinishMessage("Finished"));
          logger.warn("Somebody else is running build in this workspace right now, please wait a few minutes and try again.");
        }
      }
    } else {
      MessageQueue.get(clientId).put(clientId, new WarnMessage("Somebody else is running build in this workspace right now, please wait a few minutes and try again."));
      MessageQueue.get(clientId).put(clientId, new FinishMessage("Finished"));
      logger.warn("Somebody else is running build in this workspace right now, please wait a few minutes and try again.");
    }
    
  }
  
}
