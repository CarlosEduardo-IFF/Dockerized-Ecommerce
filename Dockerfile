FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR para o contêiner
COPY target/ecommerce-0.0.1-SNAPSHOT.jar /app-1.0.0.jar

# Cria o diretório de imagens dentro do contêiner
RUN mkdir -p /app/uploads

COPY src/main/resources/static/img/ /app/uploads/  

# Define o ponto de entrada
ENTRYPOINT ["java", "-jar", "/app-1.0.0.jar"]
