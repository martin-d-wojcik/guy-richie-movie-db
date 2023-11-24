# OpenJDK is deprecated. Use clipse-temurin instead
FROM eclipse-temurin:17
WORKDIR /films
# Copy the jar from target directory
COPY ./target/films-0.0.1-SNAPSHOT.jar /films
# Port mapping
EXPOSE 8080
CMD ["java", "-jar", "films-0.0.1-SNAPSHOT.jar"]

# OpenJDK is deprecated. Use clipse-temurin instead
# FROM eclipse-temurin:17
# ARG JAR_FILE=*.jar
# Copy the .jar to application.jar
# COPY ${JAR_FILE} application.jar
# Run application.jar to start the app in the container
# ENTRYPOINT ["java", "-jar", "application.jar"]