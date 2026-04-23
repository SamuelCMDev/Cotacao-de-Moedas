# 1. Usar uma imagem do Maven para compilar o código
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# 2. Copiar os ficheiros do projeto para dentro do container
COPY . .

# 3. Compilar o projeto e gerar o .jar (ignora os testes para ser mais rápido)
RUN mvn clean package -DskipTests

# 4. Usar uma imagem leve do Java para correr o projeto
FROM openjdk:17-jdk-slim
WORKDIR /app

# 5. Copiar o .jar que foi gerado no passo 3
COPY --from=build /app/target/*.jar app.jar

# 6. Expor a porta 8080
EXPOSE 8080

# 7. Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]