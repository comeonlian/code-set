#!/bin/bash

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/lib
CURL=/usr/local/bin/curl
url_for_check="http://192.168.2.105:8080/selfcheck/check.htm"
result=`$CURL -s $url_for_check`

if [[ $result = *ok* ]];then
	echo 'check success'
else
	echo 'check error'
fi
exit 0