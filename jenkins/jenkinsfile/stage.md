
## 动态生成stage

> https://stackoverflow.com/a/42838326
```bash
def tests = params.Tests.split(',')
for (int i = 0; i < tests.length; i++) {
    stage("Test ${tests[i]}") {
        sh '....'
    }
}
```
