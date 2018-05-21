
## 1. pip安装包
```
pip install XXX
```

#### [pip windows proxy设置](https://stackoverflow.com/a/11869484)
```
I have tried 2 options which both work on my company's NTLM authenticated proxy. Option 1 is to use --proxy http://user:pass@proxyAddress:proxyPort

If you are still having trouble I would suggest installing a proxy authentication service (I use CNTLM) and pointing pip at it ie something like --proxy http://localhost:3128
```


## 2. python 乱码问题
https://stackoverflow.com/a/14540022

## 3. 一些module
- print-colored-output-to-the-terminal-in-python  
https://pypi.org/project/termcolor/

---
## 参考
[python3-cookbook](http://python3-cookbook.readthedocs.io/zh_CN/latest/c02/p15_interpolating_variables_in_strings.html)
