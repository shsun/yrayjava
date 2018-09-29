#!/bin/sh


echo -e ‘\n\n\n\n\n aliyun mirror’;
sudo mv ~/.m2/settings.xml ~/.m2/settings.xml_bak
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



echo -e ‘\n\n\n\n\n’;
echo -e ‘starting..................... redis’;

sudo redis-cli -p 6379 shutdown;
sleep 1;

sudo cp -rfv redis/*.conf /opt/;
sudo redis-server /opt/redis_6379.conf;
sleep 1;
echo -e ‘\n\n\n\n\n’;


