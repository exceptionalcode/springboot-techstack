<h1 align="center">
  springboot-docker-techstack
  <br>
</h1>

Here I'll show how to deploy SpringBoot RESTful web service application with Docker.

### Steps
**Clone source code from git**
```
$ git clone https://github.com/exceptionalcode/springboot-techstack.git
```

**Maven build source code**\
To build counter-service cd inside the directory
```
$ mvn clean install
```

**Build a Docker Image**\
To build an image go inside the counter-service project directory on the location of **Dockerfile**
```
$ docker build -t counter-service .
```

**Docker Images**
```
REPOSITORY        TAG       IMAGE ID       CREATED          SIZE
counter-service   latest    1cff14b8d925   12 minutes ago   152MB

```
> With comamnd '$ docker images' you'll see one image build above

**Run Docker Container** 
```
$ docker run --rm -d --name counter-service -p 5000:5000 counter-service
```

**Test the container**\
Counter application swagger endopoint
```
$ http://localhost:5000/swagger-ui/#/counter-controller
```
