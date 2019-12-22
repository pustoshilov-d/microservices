FROM openjdk:11
VOLUME /tmp
COPY build/libs/*.jar identify_app.jar
EXPOSE 9004
ENTRYPOINT ["java", "-jar", "/identify_app.jar"]