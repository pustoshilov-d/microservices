FROM openjdk:11
VOLUME /tmp
COPY build/libs/*.jar cart_app.jar
EXPOSE 9002
ENTRYPOINT ["java", "-jar", "/cart_app.jar"]