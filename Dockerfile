FROM openjdk:11.0.5-jdk-stretch

COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./gradlew build
CMD ["./gradlew", "run"]
