package infocenter.helper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="workspace")
@XmlRootElement
public class ConfigurationWorkspace {

  public static final ConfigurationWorkspace NONE = new ConfigurationWorkspace();
  
  private String name;
  
  private String path;

  private Boolean status = false;
  
  @XmlElement
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
  
  public boolean isWorking() {
    return status;
  }
  
  public synchronized void setWorking(boolean status) {
    this.status = status;
  }
}
