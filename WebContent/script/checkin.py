#!/usr/bin/python
#Filename : build.py
import os
import sys
path = workspace + "/src/infocenter/online-help-data"
os.chdir(path)
print "trying to login with username:" + username + " and password:" + password
os.system("accurev login " + username+" " + password)
os.system("accurev pop -R -O .")
os.system("accurev update")
binaryKeepCommand = "accurev keep -c 'update data files' -E binary "
textKeepCommand = "accurev keep -c 'update data files' -E text "
if os.path.exists(path + "/temp"):
	result = os.popen("cp -rf " + path + "/temp/*" + " ./")
	os.system("accurev merge -oO")
	folder = os.listdir(path + "/temp")
	for f in folder:
		if f == "infocenterMapping" or f == "helpData.xml":
			result = os.popen(textKeepCommand + f)
			print result.read()
		if f == "policy_gs_doc.tar" or f == "policy_kpi_doc.tar" or f == "policy_troubleshooting_doc.tar" or f == "policy_wireless_doc.tar":
			result = os.popen(binaryKeepCommand + f)
			print result.read()
		result = os.popen("rm -f " + path + "/temp/" + f + " ./")
		print "remove " + path + "/temp/" + f
promoteCommand = "accurev promote -c 'update data files' -d"
result = os.popen(promoteCommand)
print result.read()
print("Finish accurev check in.")
#end
