#FROM nginx:latest
FROM nginx:alpine
MAINTAINER Satender Rawal

#RUN DEBIAN_FRONTEND=noninteractive
#RUN echo 'debconf debconf/frontend select Noninteractive' | debconf-set-selections


#RUN  apt-get update  && \
#     apt-get install -y --no-install-recommends apt-utils  && \
#     apt-get upgrade -y && \
#     apt-get clean


RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY ./shakleedev.com/wildcard.shakleedev.com.crt  /etc/nginx/ssl/wildcard.shakleedev.com.crt
COPY ./shakleedev.com/wildcard.shakleedev.com.key  /etc/nginx/ssl/wildcard.shakleedev.com.key

EXPOSE 80
EXPOSE 443
CMD ["nginx", "-g", "daemon off;"]