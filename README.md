# cicd-demo-app

## Docker 


**Push to Docker Registry**

**Step 1: Sign up for a Docker account**
A Docker ID grants you access to Docker Hub repositories and allows you to explore images that are available from the community and verified publishers. You’ll also need a Docker ID to share images on Docker Hub.
**Step 2: Create your first repository**
1. Sign in to Docker Hub.
1. Click Create a Repository on the Docker Hub welcome page:
1. Name it <your-username>/<your-repository-name>
1. Set the visibility to public.

**Step 3: Build and push a container image to Docker Hub from your computer**
1. Goto the directory where you have your `Dockerfile`
1. Run `docker build -t <your-username>/<your-repository-name>:<tag-id> .` to build your Docker image.
1. Run `docker run <your-username>/<your-repository-name>:<tag-id>` to test your Docker image locally.
1. Run `docker push <your-username>/<your-repository-name>:<tag-id>` to push your Docker image to Docker Hub.
1. Your repository in Docker Hub should now display a new latest tag under Tags `<tag-id>`


**Build docker image**
```
docker build -t ketankvishwakarma/cicd-demo-app:01 .
```

**Run build image & expose port**
```
docker run -d -p 9000:9000 ketankvishwakarma/cicd-demo-app:01
```
**Push to Docker Registry**
```
docker push ketankvishwakarma/cicd-demo-app:01
```

## Curl Resquest 
```
curl --location --request GET 'localhost:9000/' --header 'Content-Type: application/json'
```

```
curl --location --request GET 'localhost:9000/sweety' --header 'Content-Type: application/json'
```

## Jenkins Setting for Docker

1. Create a bridge network in Docker using the following docker network create command:
```
docker network create jenkins
```
1. In order to execute Docker commands inside Jenkins nodes, download and run the docker:dind Docker image using the following docker run command:
```
docker run \
  --name jenkins-docker \
  --rm \
  --detach \
  --privileged \
  --network jenkins \
  --network-alias docker \
  --env DOCKER_TLS_CERTDIR=/certs \
  --volume jenkins-docker-certs:/certs/client \
  --volume jenkins-data:/var/jenkins_home \
  --publish 2376:2376 \
  docker:dind \
  --storage-driver overlay2
```

1. Customise official Jenkins Docker image, by executing below two steps:
   1.1: Create Dockerfile with the following content:
```
FROM jenkins/jenkins:2.332.1-jdk11
USER root
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
  https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli
USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean:1.25.3 docker-workflow:1.28"   

```

1.2: Build a new docker image from this Dockerfile and assign the image a meaningful name, e.g. "myjenkins-blueocean:2.332.1-1":

```
docker build -t myjenkins-blueocean:2.332.1-1 .
```

Run your own myjenkins-blueocean:2.332.1-1 image as a container in Docker using the following docker run command:
```
docker run \
  --name jenkins-blueocean \
  --rm \
  --detach \
  --network jenkins \
  --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client \
  --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 \
  --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  --volume $(which docker):/usr/bin/docker \
  myjenkins-blueocean:2.332.1-1 
```

