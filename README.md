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