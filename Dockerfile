FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/book-practice-0.0.1-SNAPSHOT.jar"]