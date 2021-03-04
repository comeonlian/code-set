#!/bin/sh

source /etc/profile

# 可修改项
FILE_PATH=/tmp/ga-mass-dataetl/log



# 创建远程目录
nowTime=`date '+%Y-%m-%d %H:%M:%S'`
curday=`date +%Y%m%d`
echo "$nowTime - start clean $FILE_PATH logs..."

for file in $(find $FILE_PATH -name "*.log")
do
  `echo > $file`
  echo "$nowTime - now clean log  $file ..."
  # exit
done
echo "$nowTime - end clean $FILE_PATH logs..."

