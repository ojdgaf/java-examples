#!/bin/sh

GREEN="\033[0;32m"
NC="\033[0m"

echo "${GREEN}Deploying webapp example...${NC}"
cd /examples/webapp && mvn clean tomcat7:redeploy

echo "${GREEN}Packaging hibernate example...${NC}"
cd /examples/hibernateapp && mvn clean package
echo "${GREEN}Executing hibernate example...${NC}"
java -jar target/hibernateapp-1.0-SNAPSHOT.jar

echo "${GREEN}Deploying springapp example...${NC}"
cd /examples/springapp && mvn clean tomcat7:redeploy