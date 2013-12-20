#!/usr/bin/python
# Filename : check.py
import os
import sys
import time
import shutil
print 'Start checking environment...'

#check workspace
if not os.path.exists(workspace):
	error = workspace + " does not exist."
	sys.exit(error)

#check bakup folder
if not os.path.exists("/var/camiant/war_backup"):
	os.makedirs("/var/camiant/war_backup")

folder = os.listdir("/var/camiant/war_backup")
for f in folder:
	tempName = f.split("_")[-1]
	diffs = time.time() - time.mktime(time.strptime(tempName,'%Y-%m-%d-%H-%M-%S'))
	if diffs/60/60/24 > 10:
		shutil.rmtree("/var/camiant/war_backup/"+f)
		print "Remove backup folder:" + f
print 'End checking, validation passed!'
#end
