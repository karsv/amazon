FROM openjdk:11
COPY target/amazon-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "amazon-0.0.1-SNAPSHOT.war"]
