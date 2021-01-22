# Quarkus

## Introduction
This is the Quarkus project, that's it.

## How to run?

### By terminal
By terminal use the command ``mvn compile quarkus:dev``

![](src/main/resources/helloworld/mvn-dev.png) 
..
![](src/main/resources/helloworld/mvn-dev-success.png) 

### By jar
Execute the command ``mvn clean package``

![](src/main/resources/helloworld/mvn-package.png) 
..........
![](src/main/resources/helloworld/mvn-success.png) 

After the package the project execute the following command into your terminal

``java -jar target/hello-world-0.0.1-SNAPSHOT.jar``

![](src/main/resources/helloworld/running.png)

*Please, check again the message*, the application has started in 0.688s, is really fast!!  


## How to call

### cURL
cURL runs on terminal, so open the terminal and execute the following code 
``curl http://localhost:8080/api/quarkus/helloworld/Socrates``

![](src/main/resources/helloworld/curl.png)

### Browser

Using the browser, just type ``http://localhost:8080/api/quarkus/helloworld/nero``

![](src/main/resources/helloworld/browser.png)
 
 
### By docker

#### Build the image
docker build -t spring/hello-docker .

#### List images

``docker image ls``

#### Run
``docker run -p<expose port>:<internal port>  <imageid>``
 
``docker run -p8090:8090 a0733cb9a156``

#### See all containers
``docker container ls``

#### Run container
``docker exec -it <container_id> /bin/bash``
 
``docker exec -it 2c0b30ec415d /bin/bash``

#### Stop and remove containers
``docker stop $(docker ps -a -q)``

``docker rm $(docker ps -a -q)``

### Remove all images
``docker system prune``

### Remove all images 
``docker rmi -f $(docker images -a -q)``

#### See the Linux version
``docker exec -it 79c270d6d39e cat /etc/os-release``

### Native image
``export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.3.0/Contents/Home``

``export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.3.0/Contents/Home``

``./mvnw package -Pnative``

``./mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true -Dnative-image.xmx=6g``

``docker build -f src/main/docker/Dockerfile.native -t 16bits-quarkus/helloworld:0.0.1 .``

``docker run -i --rm -p 8080:8080 16bits-quarkus/helloworld:0.0.1``