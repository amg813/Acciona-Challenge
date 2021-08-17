FROM openjdk:11
ENV MAVEN_VERSION 3.8.1
RUN mkdir /usr/src/accionadocker
COPY /target/accionaChallenge-0.0.1-SNAPSHOT.jar /usr/src/accionadocker
WORKDIR /usr/src/accionadocker
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.data.mongodb.host=mongo", "/usr/src/accionadocker/accionaChallenge-0.0.1-SNAPSHOT.jar"]