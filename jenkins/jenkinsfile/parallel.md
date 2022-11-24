

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

### 一次下载代码，并发执行命令
> https://stackoverflow.com/a/64114377 
```
pipeline {
    agent any

    options {
        skipDefaultCheckout true
    }

    environment {
        BRANCH_NAME = 'master'
    }

    stages {
        stage('Checkout source') {
            steps {
                //1.这里有个代码下载B
                checkout([
                        $class                           : 'GitSCM',
                        branches                         : [[name: "${env.BRANCH_NAME}"]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[
                                                                    credentialsId: '...xx...',
                                                                    url          : 'git@gitserver.io:acme/acme-repo.git'
                                                            ]]
                ])
            }
        }

        //2.这里需要pipeline from scm上的文件，对应下载A；所以不设置skipDefaultCheckout
        stage('init') {
            steps {
                sh 'cp shell/test.sh'
            }
        }

        stage('Publish') {
            //3.这里需要的下载B对应的目录，需要设置skipDefaultCheckout
            options {
                skipDefaultCheckout()
            }
            parallel {
                stage('[Publish] Mac') {
                    steps {
                        sh 'yarn publish-mac'
                    }
                }

                stage('[Publish] Linux') {
                    steps {
                        sh './node_modules/.bin/yarn publish-linux'
                    }
                }

                stage('[Publish] Windows') {
                    steps {
                        bat 'yarn publish-win'
                    }
                }
            }
        }

    }
}
```



