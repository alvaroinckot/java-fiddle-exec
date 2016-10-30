FROM java:8 

RUN apt-get update
RUN apt-get install -y maven

WORKDIR /code

ADD java-fiddle-exec.web/pom.xml /code/java-fiddle-exec.web/pom.xml

WORKDIR /code/java-fiddle-exec.web

RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]
ADD java-fiddle-exec.web/src /code/java-fiddle-exec.web/src
RUN ["mvn", "package"]

WORKDIR /code
EXPOSE 4568
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "java-fiddle-exec.web/target/java_fiddle_exec-jar-with-dependencies.jar"]