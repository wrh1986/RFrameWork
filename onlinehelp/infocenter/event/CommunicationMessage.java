package infocenter.event;

import com.RFramework.message.RFMessage;

public class CommunicationMessage extends RFMessage{

  private String content;
  
  public CommunicationMessage(String content) {
    this.setContent(content);
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  private String type = "comm";
  
  public String getType() {
    return type;
  }
}