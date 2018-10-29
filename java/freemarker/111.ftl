1 ==> hello
2 ==> hello:${name}

3 ==> [list]
<ul>
<#list weeks as weekName>
  <li>${weekName}</li>
</#list>
</ul>

4 ==> [map]
<#list scoreMap?keys as key>
 ${key!} --> ${scoreMap[key]!}
</#list>

