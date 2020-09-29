#!/bin/zsh
curl --insecure --user 'admin:password' --connect-timeout 5 --no-keepalive --request GET https://localhost:8087/management/shutdown
EXIT_CODE=$?
if [[ $EXIT_CODE -ne 0 ]]
then
    PID=$(<.pid)
    kill -9 $PID
fi