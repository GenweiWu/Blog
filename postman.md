

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
