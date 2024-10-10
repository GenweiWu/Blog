

## post请求如何发送多行json数据

> https://stackoverflow.com/a/47749389

In your body:
```
{
"query":{{query}}
}
```
In your pre-request script:
```
pm.environment.set("query", JSON.stringify(
    `
    query {
       organization(login: "MY-ORG-ID") {
          samlIdentityProvider {
             externalIdentities(first: 10) {
                edges {
                   node {
                      user {login}
                      samlIdentity {nameId}
                      scimIdentity {username}
                   }
                }
             }
          }
       }
    }
    `
));
```

## pre-request script
```
pm.globals.unset("variable_key"); 清除全局变量
pm.environment.unset("variable_key");  清除环境变量
pm.globals.get("variable_key");      获取全局变量
pm.variables.get("variable_key");    获取一个变量
pm.environment.get("variable_key");      获取环境变量
pm.sendRequest("https://postman-echo.com/get", function (err, response) {
    console.log(response.json());
});  发送一个请求
pm.globals.set("variable_key", "variable_value");   设置全局变量
pm.environment.set("variable_key", "variable_value"); 设置环境变量
```

### 样例
```
let _cookie = pm.environment.get("cookie");
if(_cookie){
  let _index=  _cookie.lastIndexOf("=");
  let _csrf = _cookie.substring(_index+1);
  pm.environment.set("csrf", _csrf);
  console.log("cookie:"+_cookie+" ==> csrf:"+_csrf)
}
```

## post-response script

> eg1 
```js
var resp = pm.response.json();
//将返回值设置为变量
if(resp.code == 0){
    var modelNo = resp.data.modelNo;
    pm.collectionVariables.set("modelNo", modelNo);
}
```

