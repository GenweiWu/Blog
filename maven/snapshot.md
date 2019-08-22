
## 不建议使用snapshot版本 
> https://stackoverflow.com/a/5901460  
snapshot一般是开发中的版本，比如`4.9.1-snapshot`，像这种版本会一直更新，不稳定；
不建议使用snapshot版本，甚至哪天snapshot就删除了


### 例子：junit/junit-dep
比如中央仓库是这样的：
```
../
4.10/                                             2011-09-29 19:12         -      
4.11/                                             2012-11-14 19:21         -      
4.11-beta-1/                                      2012-10-15 19:46         -      
4.4/                                                             -         -      
4.5/                                                             -         -      
4.6/                                              2010-10-05 08:47         -      
4.7/                                              2010-10-05 08:47         -      
4.8/                                              2010-10-05 08:49         -      
4.8.1/                                            2010-10-05 08:50         -      
4.8.2/                                            2010-10-05 08:50         -      
4.9/                                              2011-08-22 18:15         -      
maven-metadata.xml                                2012-11-14 19:24       563      
maven-metadata.xml.md5                            2012-11-14 19:24        32      
maven-metadata.xml.sha1                           2012-11-14 19:24        40    
```

某个镜像仓库是这样的：
```
../
4.10/                            07-Dec-2017 22:24    -
4.10-SNAPSHOT/->                     -    -
4.11/                            16-Aug-2017 10:49    -
4.11-beta-1/                     16-Aug-2017 10:49    -
4.11-SNAPSHOT/->                     -    -
4.11.20120805.1225/              25-Sep-2018 16:24    -
4.4/                             07-Dec-2017 22:24    -
4.5/                             07-Dec-2017 22:24    -
4.6/                             05-Sep-2017 00:34    -
4.7/                             05-Sep-2017 00:34    -
4.8/                             05-Sep-2017 00:34    -
4.8.1/                           04-Sep-2018 11:30    -
4.8.2/                           07-Dec-2017 22:24    -
4.9/                             07-Dec-2017 22:24    -
4.9.1-SNAPSHOT/->                    -    -
maven-metadata.xml               07-Dec-2017 22:24  483 bytes
maven-metadata.xml.md5           07-Dec-2017 22:24  32 bytes
maven-metadata.xml.sha1          07-Dec-2017 22:24  40 bytes
maven-metadata.xml.sha256        15-Nov-2018 01:39  64 bytes
maven-metadata.xml.sha256.md5    15-Nov-2018 01:39  32 bytes
maven-metadata.xml.sha256.sha1   15-Nov-2018 01:39  40 bytes
```
