package infocenter.event;

import com.RFramework.message.RFMessage;

public class PrintMessage extends RFMessage{

  private String content;
  
  public PrintMessage(String content) {
    this.setContent(content);
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
  
  private String type = "info";
  
  public String getType() {
    return type;
  }

}
