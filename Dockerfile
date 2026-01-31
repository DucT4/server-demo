# Stage 1: Build (Giữ nguyên vì đã thành công)
FROM maven:3-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# SỬA TÊN FILE TẠI ĐÂY:
# Thay DrComputer...war thành uniclub-0.0.1-SNAPSHOT.jar cho đúng với thực tế build
COPY --from=build /app/target/uniclub-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Chạy file jar
ENTRYPOINT ["java","-jar","app.jar"]