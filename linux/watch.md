

```bash
# watch --help

Usage:
 watch [options] command

Options:
  -b, --beep             beep if command has a non-zero exit
  -c, --color            interpret ANSI color and style sequences
  -d, --differences[=<permanent>]
                         highlight changes between updates
  -e, --errexit          exit if command has a non-zero exit
  -g, --chgexit          exit when output from command changes
  -n, --interval <secs>  seconds to wait between updates
  -p, --precise          attempt run command in precise intervals
  -t, --no-title         turn off header
  -x, --exec             pass command to exec instead of "sh -c"

 -h, --help     display this help and exit
 -v, --version  output version information and exit

For more details see watch(1).
```


> watch command：周期执行任务
```bash
# watch 'date'
```
![GIF 2023-5-23 9-57-24](https://github.com/GenweiWu/Blog/assets/16630659/5781deb4-6953-4ce9-a81e-ee2e268aa177)



> watch -n 指定间隔时间
```bash
# watch -n 1 'date'
```
![GIF 2023-5-23 10-00-04](https://github.com/GenweiWu/Blog/assets/16630659/17f06b3e-7cc9-4b87-9654-5bceead0ac70)




> watch -d 高亮变化
```bash
watch -n 1 -d 'date'
```
![GIF 2023-5-23 10-00-31](https://github.com/GenweiWu/Blog/assets/16630659/0aab5490-a768-4358-a638-f64d0fe3c531)


