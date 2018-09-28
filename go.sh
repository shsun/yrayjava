#!/bin/sh


echo -e "\n\n\n"
echo "starting..................... redis"

sudo redis-cli -p 6379 shutdown;
sleep 1;

sudo cp -rfv redis/*.conf /opt/;

sudo redis-server /opt/redis_6379.conf;

sleep 1;
echo '';
