#!/usr/bin/python
# Filename : deploy.py
import os
import sys
import time
from net.schmizz.sshj import SSHClient
from net.schmizz.sshj.connection.channel.direct import Session

if ip is None or ip=="localhost" or ip=="127.0.0.1":
        os.popen("service qp_procmgr restart")
else:
        ssh = SSHClient()
        ssh.loadKnownHosts()
        ssh.connect(ip)
        ssh.authPassword(username, password)
        ssh.startSession().exec("service qp_procmgr restart")
print "Restart qp_procmgr finished"
#end
