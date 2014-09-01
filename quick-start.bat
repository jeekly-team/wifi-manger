@echo off

set base_path=%cd%

echo [INFO] install jar to local m2 repository.

call mvn clean source:jar install -Dmaven.test.skip=true

echo [INFO] create base curd project archetype.

cd %base_path%\wifi-main
call mvn archetype:create-from-project

echo [INFO] install base curd archetype to local m2 repository

cd %base_path%\wifi-main
call mvn clean install -Dmaven.test.skip=true

echo [INFO] delete base curd project generated-sources target
cd %base_path%\wifi-main
rd /S /Q target

echo [INFO] init h2 data

call mvn antrun:run

echo [INFO] start base curd app

start "base curd" mvn clean jetty:run

echo [INFO] Please wait a moment. When you see "[INFO] Started Jetty Server", you can visit: http://localhost:8080/biwifi/ to view the demo

cd %base_path%

pause