@SETLOCAL
@ECHO OFF
curl --insecure --user admin:password --connect-timeout 5 --no-keepalive --request GET https://localhost:8087/management/shutdown
IF %ERRORLEVEL% EQU 0 @GOTO End 
SET /P PID=< .pid
FOR /F "tokens=1 delims= " %%pid IN (.pid) DO @ECHO TASKKILL /F /PID %%pid
:End
@ENDLOCAL