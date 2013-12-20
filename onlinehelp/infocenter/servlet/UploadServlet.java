package infocenter.servlet;

import infocenter.event.ErrorMessage;
import infocenter.event.FinishMessage;
import infocenter.event.MessageQueue;
import infocenter.event.WarnMessage;
import infocenter.helper.ApplicationContext;
import infocenter.helper.ScriptConfiguration;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.RFramework.utils.file.FileUtil;

public class UploadServlet extends HttpServlet{

	private static int SizeThreshold = 2000000;
	private static String TemPath = "//var//camiant//temp";
	
  private Logger logger = LoggerFactory.getLogger(UploadServlet.class);
	
	public void init() {
	  File tempDir = new File(TemPath);
	  if(!tempDir.isDirectory()) {
	    tempDir.mkdirs();
	  }
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
	  String flash = request.getParameter("flash");
	  String workspace = request.getParameter("workspace");
    String clientId = request.getParameter("clientId");
	  logger.info("Upload tar files to workspace:{}", workspace);
		try { 
			if(FileUpload.isMultipartContent(request)) {
				DiskFileUpload upload = new DiskFileUpload();
				upload.setSizeMax(Long.MAX_VALUE);
				upload.setSizeThreshold(SizeThreshold);
				upload.setRepositoryPath(TemPath);
				List<FileItem> items = upload.parseRequest(request);
				StringBuilder fileNames = new StringBuilder();
				if(items.size() > 0) {
				  checkFileSize(clientId, workspace, items);
				}
				for(FileItem item: items) {
			    if(!item.isFormField() && null != item.getName() && !"".equals(item.getName())) {
  				  if(fileNames.length() > 0) {
  				    fileNames.append(",");
  				  }
  				  fileNames.append(item.getName());
  				  try{
  				    this.saveFileItem(workspace, item);
  				  }catch (Exception e){
  				    MessageQueue.get(clientId).put(clientId, new ErrorMessage(e.toString()));
  				    MessageQueue.get(clientId).put(clientId, new FinishMessage("Success"));
  				  }
			    }
				  item.delete();
				}
				//response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				if("false".equals(flash)) {
          out.println("{\"files\":\""+fileNames.toString()+"\"}");
          //out.println("{\"files\":\""+fileNames.toString()+"\"}");
				} else {
          out.println("files=\""+fileNames.toString()+"\"");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void checkFileSize(String clientId, String workspace, List<FileItem> items) {
	  StringBuilder result = new StringBuilder();
    ScriptConfiguration config = ApplicationContext.getInstance().getScriptConfig();
    String destPath = config.getWorkspace(workspace).getPath() + "/src/infocenter/online-help-data/";
    File folder = new File(destPath);
    File[] files = folder.listFiles();
    for(FileItem item: items) {
      for(File f: files) {
        if(item.getName().equals(f.getName())) {
          if(item.getSize() == f.length()){
            logger.warn("The uploaded file{} has the same size:{} as previous.", item.getName(), f.getName());
            result.append("The uploaded file:" + item.getName() + " has the same size:" + item.getSize() + " as previous.\n");
          }
          break;
        }
      }
    }
    if(result.length() > 0) {
      MessageQueue.get(clientId).put(clientId, new WarnMessage(result.toString()));
    }
  }

  private void saveFileItem(String workspaceId, FileItem item) throws Exception {
	  if(null == workspaceId) return;
	  ScriptConfiguration config = ApplicationContext.getInstance().getScriptConfig();
    String destPath = config.getWorkspace(workspaceId).getPath() + "/src/infocenter/online-help-data/";
    String tempPath = config.getWorkspace(workspaceId).getPath() + "/src/infocenter/online-help-data/temp/";
    File folder = new File(tempPath);
    folder.mkdirs();
    File file = new File(destPath+parseFileName(item.getName()));
    File tempFile = new File(tempPath+parseFileName(item.getName()));
    item.write(file);
    FileUtil.copyFile(file, tempFile);
    logger.info("Upload file:{}, size:{}",file.getName(), file.length());
	}
	
	private String parseFileName(String fileName) {
	  int start1 = fileName.lastIndexOf('/');
	  int start2 = fileName.lastIndexOf('\\');
	  int start = 0;
	  if(start1 != -1 && start1 > start2) {
	    start = start1;
	  }
	  else if( start2 != -1 && start2 > start1 ) {
	    start = start2;
	  }
	  return fileName.substring(start).replace("/", "").replace("\\", "");
	}
	
	class UploadFileResult {
	  private String name;
	  private int size;
	  private String result;
    public String getName() {
      return name;
    }
    public int getSize() {
      return size;
    }
    public String getResult() {
      return result;
    }
    public void setName(String name) {
      this.name = name;
    }
    public void setSize(int size) {
      this.size = size;
    }
    public void setResult(String result) {
      this.result = result;
    }
    public String toString() {
      return "{\"name\":\""+name+"\",\"size\":" + size + ",\"result\":\""+result +"\"}";
    }
	}
	
	/*
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServletInputStream in = request.getInputStream();
			PrintWriter out = response.getWriter();
			System.out.println(request.getContentType());
			OutputStream fileOut = new FileOutputStream("//tek//tmp.txt");
			byte[] pool = new byte[2000];
			int size = in.read(pool);
			while( size != -1) {
				writeFile(fileOut, pool, size);
				size = in.read(pool);
			}
			out.print(true);
			fileOut.flush();
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeFile(OutputStream out, byte[] pool, int size) throws IOException {
		System.out.write(pool, 0, size);
	}
	*/
}
