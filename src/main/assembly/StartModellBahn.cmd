@SETLOCAL
@ECHO OFF
START "ModellBahn" /MIN java -jar modellbahn-1.0.0-SNAPSHOT.jar
WAITFOR /T 5 DummyEvent 2>NUL
JPS -l | FIND "ModellBahn">.pid
SET loop=0
:Probe
curl --insecure --user admin:password --connect-timeout 5 --no-keepalive --request GET https://localhost:8087/management/health
IF %ERRORLEVEL% EQU 0 @GOTO End
SET /A loop=%loop%+1
IF %loop% GEQ 10 @GOTO Failed
GOTO Probe
:Failed
@ENDLOCAL
@EXIT /B -1
:End
@ENDLOCAL
@EXIT /B 0