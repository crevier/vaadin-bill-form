bills-forms
==============

A simple project base on the template for a simple Vaadin application that only requires a Servlet 3.0 container to run.
The purpose is to create a form that generate CSV file containing my daily life expenses.

Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

Keycloak Docker
---------------

WIP : still some setup to do to make it works

In order to build and run the cotainer run the `docker-build-run.sh` script