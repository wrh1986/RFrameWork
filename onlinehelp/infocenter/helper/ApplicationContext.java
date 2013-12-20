package infocenter.helper;


public class ApplicationContext {

  private static ApplicationContext context = new ApplicationContext();
  
  public static ApplicationContext getInstance() {
    return context;
  }
  
  private ScriptConfiguration scriptConfig;
  private String webAppPath;

  public ScriptConfiguration getScriptConfig() {
    return scriptConfig;
  }
  public void setScriptConfig(ScriptConfiguration scriptConfig) {
    this.scriptConfig = scriptConfig;
  }
  public String getWebAppPath() {
    if(null == webAppPath) {
      String path = this.getClass().getClassLoader().getResource("").getPath();
      String[] paths = path.split("/WEB-INF");
      webAppPath = paths[0];
    }
    return webAppPath;
  }
  public void setWebAppPath(String webAppPath) {
    this.webAppPath = webAppPath;
  }
  
}
