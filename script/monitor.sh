#!/bin/sh

source /etc/profile


ACCOUNT_SERVER_NAME='transferAccount'  #修改项
VIRTUAL_SERVER_NAME='transferVirtual'  #修改项
MONITOR_SERVER_NAME='transferMonitor'  #修改项



PIDS_ACC=`ps -ef | grep sh | grep "$ACCOUNT_SERVER_NAME" |awk '{print $2}' | wc -l`

nowTime=`date '+%Y-%m-%d %H:%M:%S'`
echo "$nowTime - validate progress $ACCOUNT_SERVER_NAME, pid [$PIDS_ACC]."

# sh /opt/transfer/startAccount.sh
if [ 0 == $PIDS_ACC ];then
  sh /opt/transfer/startAccount.sh &
fi

sleep 2

PIDS_VIR=`ps -ef | grep sh | grep "$VIRTUAL_SERVER_NAME" |awk '{print $2}' | wc -l`

nowTime=`date '+%Y-%m-%d %H:%M:%S'`
echo "$nowTime - validate progress $VIRTUAL_SERVER_NAME, pid [$PIDS_VIR]."

# sh /opt/transfer/startVirtual.sh
if [ 0 == $PIDS_VIR ];then
  sh /opt/transfer/startVirtual.sh &
fi


sleep 2

PIDS_VIR=`ps -ef | grep sh | grep "$MONITOR_SERVER_NAME" |awk '{print $2}' | wc -l`

nowTime=`date '+%Y-%m-%d %H:%M:%S'`
echo "$nowTime - validate progress $MONITOR_SERVER_NAME, pid [$PIDS_VIR]."

# sh /opt/transfer/startMonitor
if [ 0 == $PIDS_VIR ];then
  sh /opt/transfer/startMonitor.sh &
fi


