# 1. Usar uma imagem do Maven para compilar o código
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# 2. Copiar os ficheiros do projeto
COPY . .

# 3. Compilar o projeto
RUN mvn clean package -DskipTests

# 4. Usar uma imagem atualizada e estável do Java (Eclipse Temurin)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 5. Copiar o .jar gerado
COPY --from=build /app/target/*.jar app.jar

# 6. Expor a porta 8080
EXPOSE 8080

# 7. Comando para iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]