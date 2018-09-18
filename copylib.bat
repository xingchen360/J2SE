@echo off
echo ******深圳市博安达软件开发有限公司******
echo SHENZHEN POWERDATA SOFTWARE CO.,LTD.
set  CURRENT_DIR=%~dp0
echo %cd%
echo %~dp0
if not exist %CURRENT_DIR%\src\main\webapp\WEB-INF\lib set CURRENT_DIR=%~dp0
set LIB_DIR=%CURRENT_DIR%\src\main\webapp\WEB-INF\lib
if exist %LIB_DIR% del %LIB_DIR%\*.jar /s /q
cd/%CURRENT_DIR:~0,1% %CURRENT_DIR%
::call mvn eclipse:clean
call mvn dependency:sources
call mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true
call mvn dependency:copy-dependencies -DincludeScope=runtime -DoutputDirectory=%LIB_DIR%
pause