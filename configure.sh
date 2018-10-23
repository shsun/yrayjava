#!/bin/sh

echo -e ‘’;
echo -e ‘’;
echo -e ‘’;
echo -e ‘----------------------------install lib’;
echo -e ‘\n\n\n\n\n aliyun mirror’;
mv ~/.m2/settings.xml ~/.m2/settings.xml_bak
cp -rfv ./settings.xml ~/.m2/settings.xml
echo -e ‘\n\n\n\n\n’;
sleep 2;

cd spring-cloud-parent
mvn clean install -DskipTests -U;
echo -e ‘\n\n\n\n\n’;
sleep 2;


cd ../spring-cloud-client
mvn clean install -DskipTests -U;
echo -e ‘\n\n\n\n\n’;
sleep 2;


cd ../spring-cloud-starter
mvn clean install -DskipTests -U;
echo -e ‘\n\n\n\n\n’;
sleep 2;


echo -e ‘’;
echo -e ‘’;
echo -e ‘’;
echo -e ‘----------------------------redis’;
echo -e ‘restart redis on 6379’;
sudo redis-cli -p 6379 shutdown;
sleep 1;
sudo mkdir -p /opt;
sudo cp -rfv redis/*.* /opt/;
sudo redis-server /opt/redis_6379.conf;
sleep 1;
echo -e ‘\n\n\n\n\n’;


echo -e ‘’;
echo -e ‘’;
echo -e ‘’;
echo -e ‘----------------------------rabbitmq’;
echo -e ‘stop rabbitmq-server’;
sudo rabbitmqctl stop;
sleep 2;
echo -e ‘start rabbitmq-server on 5672 at background’;
sudo rabbitmq-server restart;
sleep 5;
