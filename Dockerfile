 FROM openjdk:15-jdk-alpine
   VOLUME /tmp
   COPY target/*.jar app.jar
   ENTRYPOINT ["java","-Dspring.profiles.active=mysql","-jar","/app.jar"]