package infocenter.event;

import com.RFramework.message.RFMessage;

public class FinishMessage extends RFMessage{

  private String content;
  
  public FinishMessage(String content) {
    this.setContent(content);
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  private String type = "finish";
  
  public String getType() {
    return type;
  }
}