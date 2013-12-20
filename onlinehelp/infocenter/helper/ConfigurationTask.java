package infocenter.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name="task")
@XmlRootElement
public class ConfigurationTask {
  
  public static final ConfigurationTask NONE = new ConfigurationTask();
  
  private String id;
  
  private String name;
  
  private String scriptType;
  
  private String fileName;
  
  private List<ConfigurationParam> parameters;
  
  
  @XmlAttribute
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  @XmlAttribute
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  @XmlAttribute
  public String getScriptType() {
    return scriptType;
  }
  
  public void setScriptType(String scriptType) {
    this.scriptType = scriptType;
  }

  @XmlAttribute
  public String getFileName() {
    return fileName;
  }
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  @XmlElementWrapper(name = "params")
  @XmlElement(name = "param")
  public List<ConfigurationParam> getParameters() {
    return parameters;
  }
  
  public void setParameters(List<ConfigurationParam> parameters) {
    this.parameters = parameters;
  }
  
  public Map<String, Object> getParameterMap() {
    Map<String, Object> result = new HashMap<String, Object>();
    for(ConfigurationParam param : parameters) {
      result.put(param.getKey(), param.getValue());
    }
    return result;
  }
}
