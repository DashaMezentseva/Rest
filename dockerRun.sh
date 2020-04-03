#!/usr/bin/env bash

filepath=$PWD
cd $filepath
mkdir deploy
cd deploy
# Download the war from gihub
curl -LOk https://github.com/DashaMezentseva/Rest/raw/master/target/SpringInAction-1.0-SNAPSHOT.war
# run the docker
winpty docker run -it -p 80:8888 -v /$(pwd):/usr/local/tomcat/webapps java-restfull-api:1.0
# you can reach this service through docker machine ip, also add the demorest folder to the path
# to find docker-machine ip execute the following command
# docker-machine ls
# example URL http://192.168.99.100/demorest/
