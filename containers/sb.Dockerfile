#FROM ubuntu:latest
#FROM openjdk:8-jdk-alpine
#FROM debian:latest
#FROM centos:latest
FROM alpine:latest
MAINTAINER Satender Rawal

RUN apk --update add curl ca-certificates tar \
    && curl -Ls https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.23-r3/glibc-2.23-r3.apk > /tmp/glibc-2.23-r3.apk \
    && apk add --allow-untrusted /tmp/glibc-2.23-r3.apk

ENV JAVA_DOWNLOAD=
RUN mkdir -p /opt && curl -jfksSLH "Cookie: oraclelicense=accept-securebackup-cookie" \
      "${JAVA_DOWNLOAD:-$(curl -s https://lv.binarybabel.org/catalog-api/java/jdk8.txt?p=downloads.tgz)}" \
      | tar -xzf - -C /opt \
    && ln -s /opt/jdk1.*.0_* /opt/jdk \
    && rm -rf /opt/jdk/*src.zip \
              /opt/jdk/lib/missioncontrol \
              /opt/jdk/lib/visualvm \
              /opt/jdk/lib/*javafx* \
              /opt/jdk/jre/lib/plugin.jar \
              /opt/jdk/jre/lib/ext/jfxrt.jar \
              /opt/jdk/jre/bin/javaws \
              /opt/jdk/jre/lib/javaws.jar \
              /opt/jdk/jre/lib/desktop \
              /opt/jdk/jre/plugin \
              /opt/jdk/jre/lib/deploy* \
              /opt/jdk/jre/lib/*javafx* \
              /opt/jdk/jre/lib/*jfx* \
              /opt/jdk/jre/lib/amd64/libdecora_sse.so \
              /opt/jdk/jre/lib/amd64/libprism_*.so \
              /opt/jdk/jre/lib/amd64/libfxplugins.so \
              /opt/jdk/jre/lib/amd64/libglass.so \
              /opt/jdk/jre/lib/amd64/libgstreamer-lite.so \
              /opt/jdk/jre/lib/amd64/libjavafx*.so \
              /opt/jdk/jre/lib/amd64/libjfx*.so

ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin

#COPY java.sh /usr/bin/java
#RUN chmod 755 /usr/bin/java

#ENV container docker


#RUN echo 'Updating Yum ...'
#RUN yum -y update

#RUN echo 'Installing wget ...'
#RUN yum -y install wget

# JDK Installation
#RUN echo 'Removing Open JDK ...'
#RUN yum remove java-1.6.0-openjdk
#RUN yum remove java-1.7.0-openjdk

#RUN echo 'Downloading JDK ...'
#RUN wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn/java/jdk/8u152-b16/aa0333dd3019491ca4f6ddbe78cdb6d0/jdk-8u152-linux-i586.rpm"

#RUN echo 'Installing JDK ...'
#RUN rpm -ivh jdk-8u60-linux-x64.rpm



#RUN  apt-get update -y  &&\
#     apt-get upgrade -y  &&\
#     apt-get install -y software-properties-common &&\
#     add-apt-repository ppa:webupd8team/java &&\
#     apt update -y
#     apt install -y oracle-java8-installer
#     add-apt-repository "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" &&\
#     apt-get install -y oracle-java8-installer


#RUN apt-get install oracle-java8-set-default

#RUN  apt-get update -y &&\
#     apt-get install -y software-properties-common &&\
#     add-apt-repository -y ppa:webupd8team/java && \
#     apt-get update && \
#     apt-get install -y oracle-java8-installer && \
#     rm -rf /var/lib/apt/lists/* && \
#     rm -rf /var/cache/oracle-jdk8-installer

#ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

RUN java -version
RUN which java

VOLUME /tmp
#ARG JAR_FILE
#ADD ${JAR_FILE} app.jar

EXPOSE 8080
EXPOSE 5005

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/spring-cloud/lib/SpringBootSAML2-1.0.RELEASE.war"]
