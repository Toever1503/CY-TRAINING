FROM openjdk:11-jdk
WORKDIR /java-app
COPY ./target/TestModule-0.0.1-SNAPSHOT.jar /java-app/TestModule-0.0.1-SNAPSHOT.jar
EXPOSE 9997
CMD ["java", "-jar","TestModule-0.0.1-SNAPSHOT.jar"]