
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

---
## 参考
[python3-cookbook](http://python3-cookbook.readthedocs.io/zh_CN/latest/c02/p15_interpolating_variables_in_strings.html)
