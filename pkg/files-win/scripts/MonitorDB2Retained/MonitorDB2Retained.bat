@ECHO OFF
if "%UPTIME_DBSSL%" == "true" (
	Set ENABLE_SSL=ssl
) else (	
	Set ENABLE_SSL=
)

..\..\jre\bin\java -classpath .:lib -Djavax.net.ssl.trustStore="%UPTIME_DBTRUSTSTORE%" -Djavax.net.ssl.trustStorePassword="%UPTIME_DBTRUSTSTOREPASSWORD%" -jar "db2retained.jar" "%UPTIME_HOSTNAME%" "%UPTIME_DBPORT%" "%UPTIME_DBUSER%" "%UPTIME_DBPASSWORD%" "%UPTIME_DBNAME%" "%UPTIME_DBQUERY%" "%UPTIME_QUERYTYPE%" "%ENABLE_SSL%"

