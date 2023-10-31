FROM openjdk:11
EXPOSE 8089
RUN mkdir /app
WORKDIR /app
RUN curl -O http://192.168.1.22:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar
ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]
