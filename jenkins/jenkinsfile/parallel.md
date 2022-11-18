

parallel用来实现并发执行
==



### 并发执行

> 1
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

> 2
![image](https://user-images.githubusercontent.com/16630659/201258656-a8c6f160-0ca6-4e29-9323-8de604a7d951.png)

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
                stage("111_AA") {
                    steps {
                        echo '-->stage111'
                    }
                }
                stage("222_AA") {
                    steps {
                        echo '-->stage222'
                    }
                }
            }
        }
        stage('Parallel Stage222') {
            parallel {
                stage("111_BB") {
                    steps {
                        echo '-->stage111'
                    }
                }
                stage("222_BB") {
                    steps {
                        echo '-->stage222'
                    }
                }
            }
        }
    }
}
```


### 动态的并发执行

> 1
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


> 2
![image](https://user-images.githubusercontent.com/16630659/201255443-a121a1a9-d6ea-4924-8609-4086ae6abd47.png)
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

> 3.在2的基础上指定agent
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
                            node("build") {  //如果要执行agent，则在这里指定
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





