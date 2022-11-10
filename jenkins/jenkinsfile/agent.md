### 不同的stage可以指定不同的agent

```
pipeline {
    agent none   //<--配置none则要求stage配置自己的agent

    options {
        timestamps()
    }

    stages {
        stage("Build") {
            agent {
                label 'build'  //选择不同的label
            }
            steps {
                echo "Some code compilation here..."
            }
        }

        stage("Test") {
            agent {
                label 'LABEL_1.2.3.4' //选择不同的label
            }
            steps {
                echo "Some tests execution here..."
            }
        }
    }
}
```

