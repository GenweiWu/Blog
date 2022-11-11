

parallel用来实现并发执行
==



### 并发执行

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



### 动态的并发执行

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
            steps {
                script {
                    def tests = [:]

                    def arr = [11, 22, 33]
                    for (i in arr) {
                        tests["${i}"] = {
                            node {
                                stage("stage_${i}") {
                                    echo "-->stage${i}"
                                }
                            }
                        }
                    }

                    parallel tests
                }
            }
        }
    }
}
```


> 并发2
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
            steps {
                script {
                    def arr = [11, 22]

                    //
                    def tests = [:]
                    for (i in arr) {
                        tests["${i}_AA"] = {
                            node {
                                stage("stage_${i}") {
                                    echo "-->stage${i}"
                                }
                            }
                        }
                    }
                    parallel tests


                    tests = [:]
                    for (i in arr) {
                        tests["${i}_BB"] = {
                            node {
                                stage("stage_${i}") {
                                    echo "-->stage${i}"
                                }
                            }
                        }
                    }
                    parallel tests
                }
            }
        }
    }
}
```

![image](https://user-images.githubusercontent.com/16630659/201255443-a121a1a9-d6ea-4924-8609-4086ae6abd47.png)




