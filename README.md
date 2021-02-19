# 16-bits
Code for the channel 16 bits

## Build status
![Build status](https://github.com/luizgustavocosta/16-bits/workflows/Java%20CI%20with%20Maven/badge.svg) 

![Allure](https://github.com/luizgustavocosta/16-bits/workflows/allure-junit5-maven/badge.svg) 

## Hello World
Is expected the following behaviors
1. Print a message using System.out
2. Print a message using Log api
3. Print a message using a REST service using Spring
4. Print a message using a REST service using Quarkus

### Docker Hub
All images made so far are there.

This is the [link](https://hub.docker.com/u/16bits) for the Docker Hub

Test Report
mvn clean test
mvn allure:serve