@echo off
echo ========================================
echo   JOGO DA FORCA - BOOTCAMP EDITION
echo ========================================
echo.
echo Compilando o projeto...
cd /d "C:\Users\olive\Documents\GitHub\jogo-da-forca"
gradle build

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✅ Compilação bem-sucedida!
    echo.
    echo Iniciando o jogo...
    echo.
    java -jar build\libs\jogo-da-forca-1.0-SNAPSHOT.jar
) else (
    echo.
    echo ❌ Erro na compilação!
    pause
)
