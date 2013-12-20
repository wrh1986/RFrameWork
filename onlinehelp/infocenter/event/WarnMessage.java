package infocenter.event;

import com.RFramework.message.RFMessage;

public class WarnMessage extends RFMessage{

  private String content;
  
  public WarnMessage(String content) {
    this.setContent("WARNING: "+content);
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  private String type = "warn";
  
  public String getType() {
    return type;
  }

}
