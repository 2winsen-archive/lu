set JPDA_TRANSPORT=dt_socket
set JPDA_ADDRESS=5050
c:
cd \
cd "Program Files"
cd apache-tomcat-6.0.18
cd bin
set JPDA_TRANSPORT=dt_socket
set JPDA_ADDRESS=8000
set JPDA_SUSPEND=y
set JPDA_OPTS=-Xdebug -Xrunjdwp:transport=%JPDA_TRANSPORT%,address=%JPDA_ADDRESS%,server=y,suspend=%JPDA_SUSPEND%
call catalina.bat jpda run

