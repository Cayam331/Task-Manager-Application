call runcrud.bat
if "%ERRORLEVEL%" == "0" goto launchchrome
echo.
echo Couldn't open browser
goto error

:launchchrome
explorer "http://localhost:8080/crud/v1/task/getTasks"

:error
echo.
echo There were errors