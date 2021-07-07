#!/bin/zsh
java -jar modellbahn-1.0.0-SNAPSHOT.jar &!
echo $!>.pid
LOOP=0
EXIT_CODE=0
until [[ $LOOP -gt 10 ]]
do
    curl --insecure --user 'admin:password' --connect-timeout 5 --no-keepalive --request GET https://localhost:8087/management/health
    EXIT_CODE=$?
    if [[ $EXIT_CODE -eq 0 ]]
    then
        exit 0
    fi
    let LOOP+=1
    sleep 5
done
exit -1