FROM openjdk:11
VOLUME /tmp
COPY build/libs/*.jar buying_app.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "/buying_app.jar"]