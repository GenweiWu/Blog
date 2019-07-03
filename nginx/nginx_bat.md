
>nginx start.bat
```cmd
start nginx 

::nginx -s reload
pause
```

>nginx reload.bat
```cmd
nginx -s reload
pause
```

>kill.bat
```cmd
@echo off

color 2f

taskkill /f /t /im  nginx.exe 

pause
```
