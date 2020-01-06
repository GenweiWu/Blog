namei
==

展示指定路径的每一层的权限

```console
# namei -l /var/log/nginx/error.log
f: /var/log/nginx/error.log
drwxr-xr-x root  root  /
drwxr-xr-x root  root  var
drwxr-xr-x root  root  log
drwx------ nginx nginx nginx
-rw-r--r-- nginx nginx error.log
```
