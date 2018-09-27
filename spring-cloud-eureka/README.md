

**How to run** 
* dev mode
```shell
mvn -Dcustom.mode=dev clean spring-boot:run;
```

* production mode
```shell
mvn -Dcustom.mode=prd -Dcustom.peer=1 clean spring-boot:run;
mvn -Dcustom.mode=prd -Dcustom.peer=2 clean spring-boot:run;
```