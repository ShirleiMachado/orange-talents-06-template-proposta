FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} proposta.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "proposta.jar"]