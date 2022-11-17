
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

## 3. [Windows上Python2和3如何兼容](https://python.freelycode.com/contribution/detail/139)

```
使用pip

当Python2和Python3同时存在于windows上时，它们对应的pip都叫pip.exe，所以不能够直接使用 pip install 命令来安装软件包。而是要使用启动器py.exe来指定pip的版本。命令如下：

py -2 -m pip install XXXX

-2 还是表示使用 Python2，-m pip 表示运行 pip 模块，也就是运行pip命令了。如果是为Python3安装软件，那么命令类似的变成

py -3 -m pip install XXXX
```

## 4. 一些module
- print-colored-output-to-the-terminal-in-python  
https://pypi.org/project/termcolor/
  ```python
  from termcolor import colored, cprint

  text = colored('Hello, World!', 'red', attrs=['reverse', 'blink'])
  print(text)
  cprint('Hello, World111!', 'green', 'on_red')
  cprint('Hello, World111!', 'red')

  cprint('%s ==> %s' % ('param1', 3), 'red')
  ```

- pretty-table 表格输出  
https://pypi.org/project/PrettyTable/
  ```python
  import prettytable as pt

  # 按行添加数据
  tb = pt.PrettyTable()
  tb.field_names = ["City name", "Area", "Population", "Annual Rainfall"]
  tb.add_row(["Adelaide", 1295, 1158259, 600.5])
  tb.add_row(["Brisbane", 5905, 1857594, 1146.4])
  tb.add_row(["Darwin", 112, 120900, 1714.7])
  tb.add_row(["Hobart", 1357, 205556, 619.5])

  print(tb)
  ```

## 参考
- [python3-cookbook](http://python3-cookbook.readthedocs.io/zh_CN/latest/c02/p15_interpolating_variables_in_strings.html)
- https://docs.python.org/3.10/library/functions.html#sorted
