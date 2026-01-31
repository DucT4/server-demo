# Stage 1: Build
# Nâng cấp lên Maven với JDK 21
FROM maven:3-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy mã nguồn và build file .war
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
# Nâng cấp lên JRE/JDK 21
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy file .war (Hãy đảm bảo tên file DrComputer-0.0.1-SNAPSHOT.war là chính xác)
COPY --from=build /app/target/DrComputer-0.0.1-SNAPSHOT.war drcomputer.war

EXPOSE 8080

ENTRYPOINT ["java","-jar","drcomputer.war"]