## Introduction
This is the backend of Tennis-Mate, an application that will help you find partners to play tennis.
Basic functionalities include
* Sign Up and Log In with JWT token encryption
* Get and update match status with other users
* Send/Approve/Decline requests sent by other users

This application use Kotlin, Spring Boot, Spring Security, Docker and Postgres as major dev tools.

## Steps to run the application
1. run docker daemon in your local environment for docker commands
2. run `./gradlew build` to build the project with gradle
3. run `docker-compose down -v` to clear all existing docker images and volumes
4. run `docker-compose up -d db` to run Postgres service in background
5. run `./gradlew bootRun` to start the application