FROM openjdk:15
LABEL maintainer="wenting <wenting.zhang1@outlook.com>"
ADD api-2.0.1.jar /api-2.0.1.jar
RUN bash -c 'touch /api-2.0.1.jar'
EXPOSE  9000
ENTRYPOINT    ["java","-jar","/api-2.0.1.jar"]
