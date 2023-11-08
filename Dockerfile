FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/achat-1.0.jar.original achat-devops-1.0.jar
ENTRYPOINT ["java","-jar","/achat-devops-1.0.jar"]