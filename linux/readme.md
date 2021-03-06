> 数组遍历

```console
[root@SZX1000538971 ~]# aa=(
> 1
> 2
> 3
> )
[root@SZX1000538971 ~]# echo $aa
1
[root@SZX1000538971 ~]# echo ${aa[0]}
1
[root@SZX1000538971 ~]# echo ${aa[1]}
2
[root@SZX1000538971 ~]# echo ${aa[2]}
3
[root@SZX1000538971 ~]# echo ${aa[@]}
1 2 3
```

```bash
images=(
k8s.gcr.io/kube-apiserver:v1.15.0
k8s.gcr.io/kube-controller-manager:v1.15.0
k8s.gcr.io/kube-scheduler:v1.15.0
k8s.gcr.io/kube-proxy:v1.15.0
k8s.gcr.io/pause:3.1
k8s.gcr.io/etcd:3.3.10
k8s.gcr.io/coredns:1.3.1
)

for i in ${images[@]}; do 
  imageName=${i#k8s.gcr.io/}
  docker pull registry.aliyuncs.com/google_containers/$imageName
  docker tag registry.aliyuncs.com/google_containers/$imageName k8s.gcr.io/$imageName
  docker rmi registry.aliyuncs.com/google_containers/$imageName
done;
```

> 免交互写入文件
```bash
cat <<EOF > /etc/docker/daemon.json
{
  "insecure-registries": ["10.11.12.13:8090"],  
  "exec-opts": ["native.cgroupdriver=systemd"]
}
EOF
```
