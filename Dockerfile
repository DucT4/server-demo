# Stage 1: Build
FROM maven:3-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy mã nguồn và build file .war
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy file từ stage build sang stage run
# Lưu ý: Tên file .war phải khớp với kết quả build của bạn
COPY --from=build /app/target/DrComputer-0.0.1-SNAPSHOT.war drcomputer.war

EXPOSE 8080

ENTRYPOINT ["java","-jar","drcomputer.war"]