package test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import net.schmizz.sshj.SSHClient;

import org.xml.sax.SAXException;


public class TestTaskLoader {

  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    SSHClient ssh = new SSHClient();
    ssh.loadKnownHosts();
    ssh.connect("10.60.25.81");
    ssh.authPassword("root", "NextGen");
    ssh.startSession().exec("service qp_procmgr restart");
  }
}
