FROM openjdk:8-jdk
MAINTAINER torres

ENV LOG_TAG="[Dockerfile]:"

RUN echo "$LOG_TAG Prepare the application" \
    mkdir /usr/local/app

COPY ./build/libs/template-SpringBoot-*.jar /usr/local/app/template-SpringBoot.jar
COPY ./public.tar.gz /usr/local/app/
WORKDIR /usr/local/app
RUN ls -al
RUN tar -xvzf ./public.tar.gz

EXPOSE 8080
ENV CLASSPATH .;
CMD ["java","-jar","/usr/local/app/template-SpringBoot.jar"]
