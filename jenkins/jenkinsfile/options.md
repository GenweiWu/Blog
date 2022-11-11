



```
pipeline {
    agent any

    options {
        timestamps()  //日志前显示时间
        retry(2)   //失败则重试2次，包括一开始的那次，一共执行2次
    }

    stages {
        stage("Build") {
            steps {
                sh('date |grep xxx')
            }
        }
    }
}
```

