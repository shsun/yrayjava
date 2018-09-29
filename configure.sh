#!/bin/sh





cd spring-cloud-parent
mvn -U clean install -DskipTests
sleep 1;

cd ../spring-cloud-client
mvn -U clean install -DskipTests
sleep 1;

cd ../spring-cloud-starter
mvn -U clean install -DskipTests
sleep 1;



echo -e "\n\n\n"
echo "starting..................... redis"

sudo redis-cli -p 6379 shutdown;
sleep 1;

sudo cp -rfv redis/*.conf /opt/;

sudo redis-server /opt/redis_6379.conf;

sleep 1;
echo '';
