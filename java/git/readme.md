jgit
==


> https://git-scm.com/book/en/v2/Appendix-B:-Embedding-Git-in-your-Applications-JGit
```pom.xml
<dependency>
    <groupId>org.eclipse.jgit</groupId>
    <artifactId>org.eclipse.jgit</artifactId>
    <version>3.5.0.201409260305-r</version>
</dependency>
```

其中，间接依赖了
```
[INFO] +- junit:junit:jar:4.12:compile
[INFO] |  \- org.hamcrest:hamcrest-core:jar:1.3:compile
```
