# Spring

## Introduction
This is the Spring project.

## How to run?

### By class
Execute the class HelloWorldApplication, then the application will start.

The default port has been modified to 8090.

### By jar
Execute the command ``mvn clean package``

![](src/main/resources/helloworld/mvn-package.png) 
..........
![](src/main/resources/helloworld/mvn-success.png) 

After the package the project execute the following command into your terminal

``java -jar target/hello-world-0.0.1-SNAPSHOT.jar``

![](src/main/resources/helloworld/running.png)
.. 
![](src/main/resources/helloworld/running-port.png) 

After the message *Started HelloWorldApplication* the application is ready to receive requests.


## How to call

### cURL
cURL runs on terminal, so open the terminal and execute the following code 
``curl http://localhost:8090/api/spring/helloworld/Ronaldo``

![](src/main/resources/helloworld/curl.png)

### Browser

Using the browser, just type ``http://localhost:8090/api/spring/helloworld/16bits``

![](src/main/resources/helloworld/browser.png)
 