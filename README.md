# cicd-demo-app

## Docker 

**Docker build**
```
docker build -t ketan/cicd-demo-app .
```

```
docker run -d -p 9000:9000 ketan/cicd-demo-app
```

**Curl Resquest** 
```
curl --location --request GET 'localhost:9000/' --header 'Content-Type: application/json'
```

```
curl --location --request GET 'localhost:9000/sweety' --header 'Content-Type: application/json'
```