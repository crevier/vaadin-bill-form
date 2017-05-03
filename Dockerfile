FROM jboss/keycloak

EXPOSE 8080
EXPOSE 9990

COPY target/bills-forms-*.war /opt/jboss/keycloak/standalone/deployments/bills-form.war