
kubectl get pods -A|grep coc

## 打印pod中的环境变量
`kubectl exec envar-demo -- printenv`


## 查看pods中有哪些container
`kubectl describe pods coc-ms-coc-78b45fbcb6-ssct5 -n admin`

## 重启pods
`kubectl get pod api-manage-api-manage-ms-1-skl9l -n isv  -o yaml |kubectl replace --force -f -`

## 拷贝进去
`kubectl cp /paasdata/dave2/tcptransfer -n isv manager-k2rjx:/`

## 拷贝出来
```
这个不行  kubectl cp isv/dn-api-manage-api-manage-ms-1-5h9gt:/home/dexcloud/api-manage/api-manage.jar ./aa.jar
kubectl cp isv/dn-api-manage-api-manage-ms-1-5h9gt:api-manage/api-manage.jar ./api-manage.jar
```

> 如果报错tar not found
```
kubectl exec -n <namespace> <pod> -- cat <filename with path>  > <filename>
比如 kubectl exec -n test xxxx-pod -- cat /home/test/111.zip > 111.zip 
```


## 看日志
```
kubectl logs -f $(echo `kubectl get pods -n isv -o wide |grep dn-api`|cut -d' ' -f1) -n isv
kubectl logs --tail 100 -f $(echo `kubectl get pods -n isv -o wide |grep dn-api`|cut -d' ' -f1) -n isv
```

```
search=api-manage
aaa="$(kubectl get pods -n isv -o wide |grep $search)"
kubectl logs -f $(echo $aaa|cut -d' ' -f1) -n isv
```
