package infocenter.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class ScriptConfiguration {
  private Configuration configuration = new Configuration();
  private Map<String, ConfigurationWorkspace> workspaces = new HashMap<String, ConfigurationWorkspace>();
  private Map<String, ConfigurationTask> tasks = new HashMap<String, ConfigurationTask>();
  
  public ScriptConfiguration(String filePath) throws ConfigurationException, IOException {
    FileInputStream in = new FileInputStream(filePath);
    try {
      this.init(in);
    } catch (Exception e) {
      throw e;
    } finally {
      in.close();
    }
  }

  public ScriptConfiguration(InputStream in) throws ConfigurationException {
    this.init(in);
  }
  
  public void init(InputStream in) throws ConfigurationException {
    try {
      JAXBContext context = JAXBContext.newInstance(Configuration.class); 
      Unmarshaller unmarshal = context.createUnmarshaller();
      configuration = (Configuration)unmarshal.unmarshal(in);
    } catch (Exception e){
      e.printStackTrace();
      configuration = null;
    }
    
    if(null == configuration) throw new ConfigurationException("Configuration is null. Parse configuration error!");
    
    List<ConfigurationWorkspace> wks = configuration.getWorkspaces();
    List<ConfigurationTask> tks = configuration.getTasks();
    
    if(null != wks) {
      for(ConfigurationWorkspace wk : wks) {
        if(null != wk.getName() && null == workspaces.get(wk.getName())) {
          workspaces.put(wk.getName(), wk);
        } else {
          throw new ConfigurationException("Parse workspace configuration error!");
        }
      }
    }
    
    if(null != tks) {
      for(ConfigurationTask tk : tks) {
        if(null != tk.getId() && null == tasks.get(tk.getId())) {
          tasks.put(tk.getId(), tk);
        } else {
          throw new ConfigurationException("Parse task configuration error!");
        }
      }
    }
  }
  
  public ConfigurationWorkspace getWorkspace(String key) {
    ConfigurationWorkspace result;
    if(null == (result = workspaces.get(key))) {
      result = ConfigurationWorkspace.NONE;
    }
    return result;
  }
  
  public List<ConfigurationWorkspace> getWorkspaces() {
    return configuration.getWorkspaces();
  }
  
  public ConfigurationTask getTask(String id) {
    ConfigurationTask result;
    if(null == (result = tasks.get(id))) {
      result = ConfigurationTask.NONE;
    }
    return result;
  }
  
  public List<ConfigurationTask> getTasks() {
    return configuration.getTasks();
  }

  public List<String> getProcess() {
    return configuration.getProcess();
  }
  
  public String getCheckLink() {
    return configuration.getCheckLink();
  }
}
