Python入门
==

### 一、pip安装包
```
pip install XXX
```

#### pip windows proxy设置 
> [python - How to use pip on windows behind an authenticating proxy - Stack Overflow](https://stackoverflow.com/a/11869484)

```
I have tried 2 options which both work on my company's NTLM authenticated proxy. Option 1 is to use --proxy http://user:pass@proxyAddress:proxyPort

If you are still having trouble I would suggest installing a proxy authentication service (I use CNTLM) and pointing pip at it ie something like --proxy http://localhost:3128
```