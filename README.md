# url-shortener-svc

* Simple URL shortener service based on Spring Boot. 
* Generate increasing
id for each URL and use Base62 alg to encoding the id. Generating
increasing ID can avoid any duplication/collision on the short URL, so
we don't have to check data store for duplication. This will improve
throughput and laytency. The ID generator should be scalable, right now
  is dummy, will move to Snowflake ID generator later.
* Data store is a local hashmap, will move to NoSQL later.
* Currently using Spring Security default basic auth.

## Run The Service
### Install Spring Boot CLI
```bash
$ brew tap spring-io/tap
$ brew install spring-boot
```
### Run Service
```bash
$ mvn spring-boot:run
```
After the service is up, copy the password from console log.
```
...
Using generated security password: 0856ca6a-e836-4314-b1ec-9e13fd454f48
...
```

## Access Endpoints
First create a var in the shell for password:
```bash
$ PWD=0856ca6a-e836-4314-b1ec-9e13fd454f48
```

### Shorten URL
```bash
$ curl -i -H "Authorization: Basic $(echo -n user:${PWD} | base64)" -H "Content-Type: application/json" -d '{"url":"https://google.ca"}' -X POST http://localhost:8080/shorten
```
You will get a response like below:
```json
{"url":"http://localhost:8080/b"}
```
Paste the URL in browser, you should be able to be redirected to the Google.

### Retrieve Long URL From Short URL
```bash
curl -i -H "Authorization: Basic $(echo -n user:${PWD} | base64)" -X GET http://localhost:8080/retrieve?shortenedUrl=http://localhost:8080/b
```

## Create Project
### Create Spring Boot Project
```bash
$ spring init --dependencies web,security url-shortener-service && \
  cd url-shortener-service
```

## Frontend

See https://github.com/alanxu/url-shortener-app
