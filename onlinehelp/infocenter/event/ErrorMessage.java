package infocenter.event;

import com.RFramework.message.RFMessage;

public class ErrorMessage extends RFMessage{

  private String content;
  
  public ErrorMessage(String content) {
    this.setContent("ERROR: "+content);
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  private String type = "error";
  
  public String getType() {
    return type;
  }

}
