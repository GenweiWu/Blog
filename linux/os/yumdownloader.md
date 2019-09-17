
## 先下载

> https://www.linuxprobe.com/yumdownloader-download-rpm.html
```console
yumdownloader java-1.8.0-openjdk.x86_64 --resolve --destdir=/opt/java/
```

## 再安装

```
you can use the rpm command to install samba packages. Go to the directory where you have downloaded the samba rpms and run the command :

# rpm -ivh *.rpm

OR You can also use yum command to install rpm

#yum localinstall “*.rpm”
```

