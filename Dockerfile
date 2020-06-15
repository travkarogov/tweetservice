FROM openjdk:11-jdk

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} tweet-service.jar

ENTRYPOINT ["java","-jar","/tweet-service.jar"]