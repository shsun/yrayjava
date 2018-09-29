
**Update hosts**
```python
add peer to hosts as below
127.0.0.1 peer0 peer1 peer2 peer3 peer4 peer5 peer6 peer7 peer8 peer9

```
**How to run** 
* dev mode
```shell
mvn -Dcustom.mode=dev clean spring-boot:run;
```

* production mode
```shell
mvn -Dcustom.mode=prd -Dcustom.peer=0 clean spring-boot:run;
mvn -Dcustom.mode=prd -Dcustom.peer=1 clean spring-boot:run;
mvn -Dcustom.mode=prd -Dcustom.peer=2 clean spring-boot:run;
mvn -Dcustom.mode=prd -Dcustom.peer=3 clean spring-boot:run;
mvn -Dcustom.mode=prd -Dcustom.peer=4 clean spring-boot:run;
```