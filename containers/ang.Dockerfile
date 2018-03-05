FROM node:latest
MAINTAINER Satender Rawal

RUN apt-get -y update && \
    apt-get -y install sudo

RUN sudo apt-get -y update

RUN  node -v
RUN  npm -v

RUN  sudo npm -y install --unsafe-perm -g @angular/cli

RUN  ng help
RUN  ng -v

RUN mkdir FrontEndAng
RUN mkdir AngularOut


EXPOSE 4200

WORKDIR /FrontEndAng

ENTRYPOINT ["ng","serve","-H","0.0.0.0","--port","4200","--disable-host-check","--verbose"]
