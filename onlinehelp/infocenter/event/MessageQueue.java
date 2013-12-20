package infocenter.event;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.RFramework.message.RFMessage;

public class MessageQueue {

  private Logger logger = LoggerFactory.getLogger(MessageQueue.class);
  
  private static final Map<String, MessageQueue> c = new ConcurrentHashMap<String, MessageQueue>();
  private BlockingQueue<Content> container = new LinkedBlockingQueue<Content>();
  
  public synchronized static MessageQueue get(String clientId) {
    if(null == c.get(clientId)){
      MessageQueue queue = new MessageQueue();
      c.put(clientId, queue);
    }
    return c.get(clientId);
  }
  
  public static void remove(String clientId) {
    if(null != c.get(clientId)){
      c.remove(clientId);
    }
  }
  
  public void put(String client, RFMessage msg) {
    Content nc = new Content(client, msg);
    logger.debug("put value from queue{}***{}",nc.getMessage().toString(),nc.getTimeStamp());
    try {
      container.add(nc);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }
  }
  
  public Object[] take () throws InterruptedException {
    Content ct = container.poll(300, TimeUnit.SECONDS);
    logger.debug("get value from queue{}***{}",ct.getMessage().toString(),ct.getTimeStamp());
    return new Object[]{ct.getClientId(), ct.getMessage()};
  }
  
  private class Content {
    private String clientId;
    private RFMessage message;
    private long timeStamp = System.nanoTime();
    public Content(String clientId, RFMessage msg) {
      this.setClientId(clientId);
      this.setMessage(msg);
    }
    public RFMessage getMessage() {
      return message;
    }
    public void setMessage(RFMessage message) {
      this.message = message;
    }
    public String getClientId() {
      return clientId;
    }
    public void setClientId(String clientId) {
      this.clientId = clientId;
    }
    public long getTimeStamp() {
      return this.timeStamp;
    }
  }
}
