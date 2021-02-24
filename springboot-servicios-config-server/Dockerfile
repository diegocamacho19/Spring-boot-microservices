FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8889
ADD ./target/springboot-servicios-config-server-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT ["java","-jar", "/config-server.jar"]