FROM openjdk:11
VOLUME /tmp
COPY target/*.jar cicd-demo-aap.jar
ENTRYPOINT ["java","-jar","/cicd-demo-aap.jar"]