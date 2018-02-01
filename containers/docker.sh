#!/usr/bin/env bash

# ./mvnw install dockerfile:build


echo "PWD is: $PWD"

cd ./../FrontEndAng

rm -rf ./../src/main/resources/static/hp/*

ng build -prod --output-path=./../src/main/resources/static/hp/

cd ..

mvn package

cd containers

docker ps -a
docker ps

docker stop $(docker ps -aq)

docker rm nginxhp
docker rm springhp
docker rm angularhp

docker build --rm=true -f ng.Dockerfile -t nginx:hp .
docker build --rm=true -f sb.Dockerfile -t spring:hp .
docker build --rm=true -f ang.Dockerfile -t angular:hp .

#docker run --name angularhp  -d -p 4200:4200 -v "$(PWD)/../FrontEndAng/":/FrontEndAng/:ro  -v "$(PWD)/../src/main/resources/static/hp/":/AngularOut/:ro  -e -d angular:hp
#docker run --name nginxhp  -d -p 80:80 -p 443:443 -v "$(PWD)/../src/main/resources/static":/usr/share/nginx/html -d nginx:hp
#docker run --name springhp -d -p 8080:8080 -p 5005:5005 -v "/DataDisc/TempDir/IDE/SpringBootSAML2/target":/opt/spring-cloud/lib/:ro -e -d spring:hp

docker-compose up -d

docker ps


#docker images -f dangling=true
#docker images purge