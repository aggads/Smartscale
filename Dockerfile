FROM java:8
FROM maven:alpine

COPY ./target /app

COPY . /app

WORKDIR /app

RUN mvn clean install -DskipTests

ENTRYPOINT [ "java","-jar","/app/smart-scale-0.0.1-SNAPSHOT.jar" ]