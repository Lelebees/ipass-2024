# Stage 1: Build the application with Maven
FROM maven:3.9.5-eclipse-temurin-17-alpine AS builder
COPY . /usr/src/
RUN mvn -f /usr/src/pom.xml clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM tomcat:9.0.62-jdk17-temurin
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY --from=builder /usr/src/target/*.war $CATALINA_HOME/webapps/ROOT.war
#COPY --from=builder /usr/src/target/boekmanager-backend-1.0-SNAPSHOT/* /$CATALINA_HOME/webapps/ROOT/
RUN cat $CATALINA_HOME/conf/server.xml


EXPOSE 8080

ENTRYPOINT ["catalina.sh", "run"]
