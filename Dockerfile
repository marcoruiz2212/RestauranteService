FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8083
ARG APP_NAME="RestauranteService"
ARG APP_VERSION="0.0.1"
ARG APP_SNAPSHOT="SNAPSHOT"
ARG JAR_FILE="/build/libs/${APP_NAME}-${APP_VERSION}-${APP_SNAPSHOT}.jar"

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "app.jar"]