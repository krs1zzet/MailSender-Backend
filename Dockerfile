FROM bellsoft/liberica-openjdk-alpine:17.0.3

WORKDIR /usr/share/app

COPY target/*.jar app.jar

CMD ["java", "-jar", "/usr/share/app/app.jar"]
