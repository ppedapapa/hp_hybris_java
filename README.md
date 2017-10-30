# GAE app engine template for web services
GAE Tutorial 4 Public

This sample shows how to run a [Spring Boot][spring-boot] application on [Google
Cloud Platform][cloud-java], combined with Swagger docs integrated via springfox. It uses the [Google App Engine flexible
environment][App Engine-flexible].

The base configuration is to have App Engine manage Jetty and the VM.

[App Engine-flexible]: https://cloud.google.com/appengine/docs/flexible/
[cloud-java]: https://cloud.google.com/java/
[spring-boot]: http://projects.spring.io/spring-boot/


## Before you begin

This sample assumes you have [Java 8][java8] installed.

[java8]: http://www.oracle.com/technetwork/java/javase/downloads/

## Run locally
```
./gradlew jettyRun
```
Then go to http://localhost:8080/swagger-ui.html

### Deploy to app engine
One of:
```
gradle appengineDeploy
mvn appengine:deploy
```

## Creating the app in app engine
See https://github.com/GoogleCloudPlatform/getting-started-java/tree/master/helloworld-springboot

In cloud shell, update to Java 8:
```
sudo update-java-alternatives -s java-1.8.0-openjdk-amd64
```
Ignore errors. https://cloud.google.com/shell/docs/features#language_support

## Local Installation
```
gcloud components install app-engine-java
```

Set projest ID (not name):
```
gcloud config set project  modular-buffer-165700
```

Start app engine in the project
```
gcloud app create
```

Allows connecting to google DB from local machine
```
gcloud auth application-default login
```


## Manual depoloy:
```
gradle appengineStage
cd build/staged-app 
gcloud app deploy 
```
