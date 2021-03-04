#!/bin/sh

source /etc/profile

# 检查-民情线索程序是否启动
SERVER_NAME_ONE='mqxs'  #修改项

# 检查-请求详情程序是否启动
SERVER_NAME_TWO='alarmDetail'  #修改项


# mqxs
PIDS_ACC=`ps -ef | grep java | grep "$SERVER_NAME_ONE" |awk '{print $2}' | wc -l`

nowTime=`date '+%Y-%m-%d %H:%M:%S'`
echo "$nowTime - check progress $SERVER_NAME_ONE, pid [$PIDS_ACC]."

# 
if [ 0 == $PIDS_ACC ];then
  #sh /apps/ga-mass-dataetl/rest/mqxs.sh >> /tmp/ga-mass-dataetl/log/mqxs.log &
fi


# kafka
PIDS_ACC=`ps -ef | grep java | grep "$SERVER_NAME_TWO" |awk '{print $2}' | wc -l`

nowTime=`date '+%Y-%m-%d %H:%M:%S'`
echo "$nowTime - check progress $SERVER_NAME_TWO, pid [$PIDS_ACC]."

#
if [ 0 == $PIDS_ACC ];then
  sh /apps/ga-mass-dataetl/kafka/start.sh
fi


