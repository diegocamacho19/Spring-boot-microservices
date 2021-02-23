@echo off
set RABBIT_ADDRESSES=localhost:5672
set STORAGE_TYPE=mysql
set MYSQL_USER=root
set MYSQL_PASS=123456
java -jar ./zipkin-server-2.23.2-exec.jar