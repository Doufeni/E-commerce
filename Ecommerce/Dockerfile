#
# Build stage
#
FROM maven:3.9.9-ibm-semeru-21-jammy AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/target/Ecommerce-0.0.1-SNAPSHOT.jar"]