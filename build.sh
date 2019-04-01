#!/bin/sh

MAVEN_CONTAINER=mav
DB_CONTAINER=pos
GREEN="\033[0;32m"
NC="\033[0m"
SCRIPT_PATH=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
EXECUTION_START=`date +%s`

echo "${GREEN}Launching Docker containers...${NC}"
cd $SCRIPT_PATH/docker && docker-compose up -d

echo "${GREEN}Waiting 5 second for containers to finish their initialization...${NC}"
sleep 5

echo "${GREEN}Importing PostgreSQL database dump...${NC}"
docker exec $DB_CONTAINER psql -f database_dump.sql

echo "${GREEN}Deploying webapp example...${NC}"
docker exec -it $MAVEN_CONTAINER bash -c "cd webapp && mvn clean tomcat7:redeploy"

echo "${GREEN}Packaging hibernate example...${NC}"
docker exec -it $MAVEN_CONTAINER bash -c "cd hibernateapp && mvn clean package"
echo "${GREEN}Executing hibernate example...${NC}"
docker exec -it $MAVEN_CONTAINER bash -c "cd hibernateapp && java -jar target/hibernateapp-1.0-SNAPSHOT.jar"

echo "${GREEN}Deploying springapp example...${NC}"
docker exec -it $MAVEN_CONTAINER bash -c "cd springapp && mvn clean tomcat7:redeploy"

EXECUTION_END=`date +%s`
echo "${GREEN}Execution time - $((EXECUTION_END-EXECUTION_START)) seconds${NC}"