# User Authentication Java Microservice

The User register microservice, a `Spring Boot` with a `Maria-DB` database responsible create a new `User` register and returns the self link using `Spring HATEOS`

In this microservice, we have the following technologies:

 - Spring Boot
 - Spring Web with HATEOS
 - Spring Data
 - Docker and Docker Compose
 - Controller Advice (to handle Exceptions)
 - And some patterns as, Builder, Factory...

# Running on Local environment

To run this application in local environment we need to :

 * Clone this repository.
 * Build a docker image. (Is necessary to have a Gradle installed)
 * Run the docker-compose file.
 

## Clone this repository

`$ git clone https://github.com/jorgetavares89/user-token-auth-ms.git`

## Build Docker image

To build de docker image, we need to install the [https://gradle.org/install/](Gradle) or use the wrapper that can be find in root application path. After cloned the repos, run: `$ gradle buildDockerImage` (in application root path) and after that, the image should be generated with the name  `user-token-auth-ms:0.0.1`. To check, run: `$ docker images` 

	Notes: 
	1-The docker installed at host, needs to be in the admin domain (running without sudo)
	
## Running the application

To up the docker container generated, we have a `docker-compose`file on root path. That's contains all dependencies to up this microservice (isolated of the solution, just to unit test). To run the `docker-compose`file, run: `$ docker-compose up`

	Note: If you want to rebuild the application updating the docker image, we have a rebuildApplication.sh shell script thats:
	
	1 - Down the containers
	2 - Delete a last image (just to not consumer a desnecessary memory)
	3 - Build a new image
	4 - Up de cotainers
	
	So, just run: `$ bash rebuildApplication.sh`




