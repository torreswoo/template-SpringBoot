FROM openjdk:8-jdk
MAINTAINER torres

ENV LOG_TAG="[Dockerfile]:"

RUN echo "$LOG_TAG Prepare the application" \
    mkdir /usr/local/app

COPY ./build/libs/template-SpringBoot-*.jar /usr/local/app/template-SpringBoot.jar

WORKDIR /usr/local/app
RUN ls -al
EXPOSE 8080

CMD ["java","-jar","/usr/local/app/template-SpringBoot.jar"]
