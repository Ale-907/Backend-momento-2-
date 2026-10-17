@echo off
setlocal
cd /d "%~dp0"
if not exist target\classes mkdir target\classes
if not exist target mkdir target
(
echo src/main/java/Main.java
for %%f in (src\main\java\app\domain\*.java) do echo src/main/java/app/domain/%%~nxf
) > target\fuentes.txt
javac --release 17 -encoding UTF-8 -d target\classes @target\fuentes.txt
if errorlevel 1 exit /b 1
java -cp target\classes Main
pause
