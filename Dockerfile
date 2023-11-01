FROM openjdk:11
EXPOSE 8089
ADD target/achat-1.0.jar achat-devops-1.0.jar
ENTRYPOINT ["java","-jar","/achat-devops-1.0.jar"]