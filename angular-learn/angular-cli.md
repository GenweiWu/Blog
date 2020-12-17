## angular/cli
https://github.com/angular/angular-cli

```
npm uninstall -g @angular/cli
npm uninstall --save-dev @angular/cli
npm cache clean
npm install -g @angular/cli@latest
npm install --save-dev @angular/cli@latest
```

### 1、ng generaete xxx -d 可以让你提前知道效果，但不会真的生成
```
D:\daveCode\adc-demo222>ng generate component hello-route -d
installing component
You specified the dry-run flag, so no changes will be written.
  identical src\app\hello-route\hello-route.component.css
  identical src\app\hello-route\hello-route.component.html
  identical src\app\hello-route\hello-route.component.spec.ts
  identical src\app\hello-route\hello-route.component.ts
  update src\app\app.module.ts
```

### 2、ng g without test

to generate a component without a "...spec.ts" file   
you simply run "--spec false". Example below
```
ng g c testfile --spec false
```

### 3、ng g c只生成ts，html和css都放在ts里
```bash
# --inline-style=true,不生成css
# --inline-template=true,不生成html
# --spec=false,不生成spec
# -d尝试不真正生成
ng g c xx --inline-style=true --inline-template=true --spec=false -d
```

### 4、ng命令传参数
下面的效果是一样的：
```
npm start -- --port 4301
ng serve --port 4301
```
