FROM java:latest
LABEL maintainer="wenting <wenting.zhang1@outlook.com>"
ADD service/target/service-2.0.1.jar /service-2.0.1.jar
RUN bash -c 'touch/service-2.0.1.jar'
EXPOSE  9000
ENTRYPOINT    ["java","-jar","/service-2.0.1.jar"]