#!/bin/bash

ACCESS_FILE=/tmp/temp/localhost_access_log.2017-12-15.txt
MYSQL=/usr/bin/mysql

while read LINE
do
  OLD_IFS="$IFS"
  IFS=" "
  field_arr=($LINE)
  IFS="$OLD_IFS"
  STATEMENT="insert into access values(null, '${field_arr[0]}', '${field_arr[6]}', '${field_arr[8]}', '${field_arr[3]}');"
  echo $STATEMENT
  $MYSQL xxl-job -u root -psurfilter1218 -h 192.168.0.166 -P 3306 -e "${STATEMENT}"
done < $ACCESS_FILE

exit 0;
