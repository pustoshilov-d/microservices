FROM openjdk:11
VOLUME /tmp
COPY build/libs/*.jar goods_app.jar
EXPOSE 9003
ENTRYPOINT ["java", "-jar", "/goods_app.jar"]