FROM openjdk:18-alpine

WORKDIR /app

ARG PROFILE=dev
ARG VERSION=latest

COPY /target/*${VERSION}-${PROFILE}.jar app.jar

CMD ["java", "-jar", "app.jar"]
