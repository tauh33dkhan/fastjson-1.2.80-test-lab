FROM tomcat:8.5.73-jdk8

COPY target/poc-0.0.1-SNAPSHOT.jar /usr/src/

RUN java -Dserver.address=0.0.0.0 -Dfastjson.parser.autoTypeSupport=true -Dserver.port=8080 -jar /usr/src/poc-0.0.1-SNAPSHOT.jar

EXPOSE 8080
