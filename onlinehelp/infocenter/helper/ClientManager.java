package infocenter.helper;

import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.RFramework.message.RFMessage;

import infocenter.event.FinishMessage;
import infocenter.event.MessageQueue;
import infocenter.event.WarnMessage;

public class ClientManager {

  
  private static final Logger logger = LoggerFactory.getLogger(ClientManager.class);
  
  private static final ClientManager cm = new ClientManager();
  
  private Map<String, PrintWriter> container = new ConcurrentHashMap<String, PrintWriter>();
  private Map<String, Thread> threadMonitor = new ConcurrentHashMap<String, Thread>();
  
  public static ClientManager get() {
    return cm;
  }
  public void add(String clientId, PrintWriter out) {
    if(container.containsKey(clientId)) {
      this.remove(clientId);
    } 
    if(!threadMonitor.containsKey(clientId)) {
      logger.info("create a new thread to run the output");
      ManageThread t = new ManageThread(clientId);
      threadMonitor.put(clientId, t);
      t.start();
    }
    container.put(clientId, out);
  }
  
  public void remove(String clientId){
    if(container.containsKey(clientId)) {
      container.remove(clientId);
    }
  }
  
  public void clear(String clientId) {
    if(container.containsKey(clientId)) {
      container.remove(clientId);
    }
    if(threadMonitor.containsKey(clientId)) {
      threadMonitor.remove(clientId);
    }
  }
  
  public void print(String clientId, RFMessage msg){
    logger.info("try to print message:{}",msg.toString());
    int count = 0;
    while(count < 50) {
      PrintWriter out = container.get(clientId);
      if(null != out) {
        try {
          synchronized (out) {
            out.print(msg.toString());
            out.flush();
            out.close();
            this.remove(clientId);
            out.notify();
          }
          break;
        } catch (Exception ex) {
          ex.printStackTrace();
          logger.info("Can be ignore:" + ex.getMessage());
        }
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        this.remove(clientId);
        logger.error(e.getMessage());
      }
      count ++;
      logger.info("Writer is out of date, waiting for update.count:{}", count);
    }
  }
  
}

class ManageThread extends Thread{

  private static final Logger logger = LoggerFactory.getLogger(ManageThread.class);
  private static final int WARNING_WAITINGTIME = 8000;
  
  private String clientId;
  public ManageThread(String clientId) {
    this.clientId = clientId;
  }
  public void run(){
    boolean terminate = false;
    while (!terminate) {
      Object[] msgs;
      try {
        msgs = MessageQueue.get(clientId).take();
        if(null != msgs && msgs.length == 2) {
          ClientManager.get().print((String)msgs[0], (RFMessage)msgs[1]);
          if(msgs[1] instanceof FinishMessage){
            logger.info("Remove client{} from queue",clientId);
            ClientManager.get().clear(clientId);
            MessageQueue.remove(clientId);
            terminate = true;
          } else if(msgs[1] instanceof WarnMessage) {
            Thread.sleep(WARNING_WAITINGTIME);
          }
        }
      } catch (InterruptedException e) {
        logger.warn(e.getMessage());
        logger.warn("Exception! Remove client{} from queue",clientId);
        ClientManager.get().clear(clientId);
        MessageQueue.remove(clientId);
        terminate = true;
      }
    }
  }
}