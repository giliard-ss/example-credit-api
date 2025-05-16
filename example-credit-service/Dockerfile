FROM openjdk:17-jdk-alpine
RUN mkdir /service
WORKDIR /service
COPY . /service/
RUN ./mvnw clean package
CMD ["java", "-jar", "/service/target/example-credit-service-0.0.1-SNAPSHOT.jar"]