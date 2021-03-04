#!/bin/bash

function error_exit {
  echo "$1" 1>&2
  exit 1
}

# 本地目录(可修改项)
sourcePath=/opt/http-reader/download/monitor

# 远程服务器IP,端口,目录(可修改项)
targetIp=15.48.117.13
targetPort=22
targetPath=/appslog/http-reader/monitor

# 间隔多久扫描一次目录(可修改项)
sleepTime=120

# 删除几天前的目录(可修改项)
delDay=3


while true
do
  nowTime=`date '+%Y-%m-%d %H:%M:%S'`
  echo "$nowTime - start scan dir files..."
  # 删除本地历史目录
  dayBefore=`date --date="$delDay day ago" "+%Y%m%d"`
  sourceDatePath="$sourcePath/$dayBefore"
  if [ -d "$sourceDatePath" ]; then
    echo "$nowTime - delete dir $sourceDatePath...";`rm -rf  "$sourceDatePath"`
  fi
  
  # 创建远程目录
  curday=`date +%Y%m%d`
  targetDatePath="$targetPath/$curday"
  ssh -p $targetPort $targetIp "[ -d $targetDatePath ]" >/dev/null 2>&1
  if [ $? != 0 ]
  then
    echo "$nowTime - auto create remote dir $targetDatePath ..."
    ssh -p $targetPort $targetIp "mkdir $targetDatePath" || error_exit "$nowTime - Line number:$LINENO ,create remote dir failed, exit..."
  fi
  
  for file in $(find $sourcePath/$curday -name "*.xml")
  do
    scp -P $targetPort $file $targetIp:$targetDatePath || error_exit "$nowTime - Line number:$LINENO ,scp file failed, exit..."
    echo "$nowTime - scp xml file to $targetIp $targetDatePath : $file ..."
    rm -rf $file
  done
  echo "$nowTime - end scan dir files..."
  sleep $sleepTime
done
