FROM openjdk:11
EXPOSE 8089
ADD target/timesheet-devops-1.0.jar timesheet-devops-1.0.jar
ENTRYPOINT ["java", "-jar", "/timesheet-devops-1.0.jar"]
