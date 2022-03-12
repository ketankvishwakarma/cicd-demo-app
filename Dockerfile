FROM openjdk:16-jdk-alpine
VOLUME /tmp
COPY target/*.jar cicd-demo-aap.jar
ENTRYPOINT ["java","-jar","/cicd-demo-aap.jar"]