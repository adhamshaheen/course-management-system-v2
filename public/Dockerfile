# Use Java 21 image
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/public-0.0.1-SNAPSHOT.jar"]