FROM eclipse-temurin:17
VOLUME /tmp
EXPOSE 8080
COPY target/films-0.0.1-SNAPSHOT.jar gr-films.jar
ENTRYPOINT ["java","-jar","/gr-films.jar"]
