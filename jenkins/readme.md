

## 开启jenkins日志

> https://stackoverflow.com/a/58826732
```
Go to -> Manage Jenkins -> System Log
Click on add new log recorder and name it anything you like for example -> user_access_details
Then click on add loggers once prompted and add jenkins.security.SecurityListener and set it to Finest level
```
