#!/bin/sh

MAVEN_CONTAINER=mav
DB_CONTAINER=pos
GREEN="\033[0;32m"
NC="\033[0m"
SCRIPT_PATH=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
EXECUTION_START=`date +%s`

echo "${GREEN}Launching Docker containers...${NC}"
cd $SCRIPT_PATH/../docker && docker-compose up -d

echo "${GREEN}Waiting 5 second for containers to finish their initialization...${NC}"
sleep 5

echo "${GREEN}Importing PostgreSQL database dump...${NC}"
docker exec -it $DB_CONTAINER bash -c "psql -f dump.sql"

docker exec -it $MAVEN_CONTAINER /maven.sh

echo "${GREEN}Deploying bootapp example in the background...${NC}"
docker exec -d $MAVEN_CONTAINER bash -c "cd bootapp && mvn clean spring-boot:run > run.log"

EXECUTION_END=`date +%s`
echo "${GREEN}Execution time - $((EXECUTION_END-EXECUTION_START)) seconds${NC}"