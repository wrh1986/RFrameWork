#!/usr/bin/python
# Filename : deploy.py
import os
import sys
import time
from net.schmizz.sshj import SSHClient

warTime = time.strftime('%Y-%m-%d-%H-%M-%S',time.localtime(time.time()))
if ip is None or ip=="localhost" or ip=="127.0.0.1":
        print "Destination server is :localhost"
        if not os.path.exists(path):
                os.makedirs(path)
                print("Build directory:"+path)
        os.system ("rm -f "+path+"/infocenter.war")
        print "Remove "+path+"/infocenter.war"
        os.system ("cp "+workspace+"/build/jars/infocenter.war "+path)
        print "Copy " + workspace + "/build/jars/infocenter.war to " + path
	strlist = workspace.split("/")
	if len(strlist) < 1:
		strlist += ["Temp"]
        backFolder = backupFolder + "/" + strlist[-1] + "_" + warTime
        os.makedirs (backFolder);
        os.system ("cp " + path + "/infocenter.war " + backFolder)
        os.system ("cp -r " + workspace + "/src/infocenter/online-help-data " + backFolder)
	print "Backup files finished"
        if os.path.isfile (path+"/infocenter.war"):
                print "Deploy war file successfully!"
        else:
                print "Deploy war file failed!"
else:
        print "Destination server is :"+ip
        ssh = SSHClient()
        ssh.loadKnownHosts()
        ssh.connect(ip)
        ssh.authPassword(username, password)
        ssh.newSCPFileTransfer().upload(workspace+"/build/jars/infocenter.war", path)
#end
