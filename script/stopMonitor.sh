#!/bin/bash

SERVER_NAME='transferMonitor'  #修改项

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

PIDS=`ps -ef | grep sh | grep "$SERVER_NAME" |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "stopAccount error, the $SERVER_NAME does not started!"
    exit 1
fi

echo -e "Stopping the $SERVER_NAME ...\n"
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
done
