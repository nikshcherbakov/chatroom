FROM openjdk:11
EXPOSE 8080
ADD target/chatroom.jar chatroom.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "chatroom.jar"]