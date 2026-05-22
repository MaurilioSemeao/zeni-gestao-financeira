# Estágio 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Fazer o build ignorando testes para ser mais rápido
RUN mvn clean package -DskipTests

# Estágio 2: Run (Imagem muito mais leve, apenas com o JRE)
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copia o .jar compilado do estágio anterior
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
