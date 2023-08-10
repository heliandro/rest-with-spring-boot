FROM openjdk:18-alpine

WORKDIR /app

ARG PROFILE
ARG VERSION

COPY target/*${VERSION}-${PROFILE}.jar app.jar

CMD ["java", "-jar", "app.jar"]