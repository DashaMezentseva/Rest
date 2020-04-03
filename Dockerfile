# Stage 1: download the code of github
FROM alpine/git as downloadSourceCode
WORKDIR /app
RUN git clone https://github.com/DashaMezentseva/Rest

# Stage 2: build the project via maven
FROM maven:3.5-jdk-8-alpine as packageSourceCode
WORKDIR /app/backend
COPY --from=downloadSourceCode /app/Rest /app/backend
ADD pom.xml /app/backend/pom.xml
RUN mvn package

EXPOSE 8888
CMD ["mvn", "tomcat7:run"]




#
## Stage 3: spin up a tomcat container
#FROM tomcat:8.0-alpine
#WORKDIR /app
#COPY --from=packageSourceCode /app/target/SpringInAction-1.0-SNAPSHOT.war /usr/local/tomcat/webapps
## the port which the tomcat is listening inside the container
#EXPOSE 8888
## run the tomcat
#CMD ["catalina.sh", "run"]
