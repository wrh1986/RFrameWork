package infocenter.helper;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="Configuration", propOrder={"workspaces","tasks","process","checkLink"})
@XmlRootElement
public class Configuration {
  
  private List<ConfigurationWorkspace>  workspaces;
  
  private List<ConfigurationTask> tasks;

  private List<String> process;
  
  private String checkLink;
  @XmlElementWrapper(name = "workspaces")
  @XmlElement(name = "workspace")
  public List<ConfigurationWorkspace> getWorkspaces() {
    return workspaces;
  }
  public void setWorkspaces(List<ConfigurationWorkspace> workspaces) {
    this.workspaces = workspaces;
  }
  
  @XmlElementWrapper(name = "tasks")
  @XmlElement(name = "task")
  public List<ConfigurationTask> getTasks() {
    return tasks;
  }
  public void setTasks(List<ConfigurationTask> tasks) {
    this.tasks = tasks;
  }
  
  @XmlElementWrapper(name = "process")
  @XmlElement(name = "task-ref")
  public List<String> getProcess() {
    return process;
  }

  public void setProcess(List<String> process) {
    this.process = process;
  }
  
  @XmlElement(name = "checkLink")
  public String getCheckLink() {
    return checkLink;
  }
  
  public void setCheckLink(String checkLink) {
    this.checkLink = checkLink;
  }
}
