@echo off
setlocal

REM Start BookClub from the folder containing this script and pom.xml.
cd /d "%~dp0"

if not exist "pom.xml" (
    echo ERROR: pom.xml was not found.
    echo Put start.bat in the project root, next to pom.xml.
    pause
    exit /b 1
)

if not exist "mvnw.cmd" (
    echo ERROR: mvnw.cmd was not found.
    echo Keep the Maven Wrapper files in the GitHub repository.
    pause
    exit /b 1
)

echo Starting BookClub...
echo When the server is ready, visit http://localhost:8080/home
echo Stop the server with Ctrl+C.
echo.

call "%~dp0mvnw.cmd" spring-boot:run
set "RESULT=%ERRORLEVEL%"

echo.
if not "%RESULT%"=="0" (
    echo BookClub could not be started. See error messages above.
) else (
    echo BookClub has stopped.
)
pause
exit /b %RESULT%

