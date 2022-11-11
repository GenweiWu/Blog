

parallel用来实现并发执行
==



```
pipeline {
    agent any
    options {
        timestamps()
    }

    stages {
        stage('Non-Parallel Stage') {
            steps {
                echo 'This stage will be executed first.'
            }
        }
        stage('Parallel Stage') {
            parallel {
                stage("111") {
                    steps {
                        echo '-->stage111'
                    }
                }
                stage("222") {
                    steps {
                        echo '-->stage222'
                    }
                }
                stage("333") {
                    steps {
                        echo '-->stage333'
                    }
                }
            }
        }
    }
}
```





