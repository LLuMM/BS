#!/bin/sh

solr/tomcat7/bin/startup.sh
python bsCraw/NewsServer.py 
	
