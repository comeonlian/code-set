@echo off
echo Starting, please wait ...
echo path:%~dp0

set class_path=%CLASSPATH%;..\conf\logback.xml

set libs=..\lib

set class_path=%class_path%;%libs%\chat-server-0.1-SNAPSHOT.jar;
set class_path=%class_path%;%libs%\logback-classic-1.1.11.jar;
set class_path=%class_path%;%libs%\logback-core-1.1.11.jar;
set class_path=%class_path%;%libs%\slf4j-api-1.7.25.jar;

echo %class_path%

java -classpath %class_path% com.leolian.chat.server.ChatServerStartup
@pause
