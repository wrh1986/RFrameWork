#!/usr/bin/python
# Filename : build.py
import os
import sys
import time
antpath = "/var/camiant/tools/apache-ant-1.6.0/bin/ant"
print "Ant path: " + antpath
os.chdir(workspace)
os.chdir("./src/infocenter/java")
print "Start clearing history files!"
command = antpath + " clean"
print command
result = os.popen(command)
print result.read()
if not os.path.exists(workspace + "/build/jars"):
        os.makedirs(workspace + "/build/jars")
        print "build directory:" + workspace + "/build/jars"
print "Start building war file!"
command = antpath + " war"
result = os.popen(command)
print result.read()
#end

