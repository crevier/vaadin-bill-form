#!/bin/bash

keycloakid=$(docker ps -aqf name=keycloak);docker kill $keycloakid;docker rm $keycloakid
docker run -d -p 8081:8080 -p 9990:9990 --name keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin $(docker build -q .)