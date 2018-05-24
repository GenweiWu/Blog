

# 字符串到json对象
```py
import json

json_data = '{"name":123}'
json_obj = json.loads(json_data)
print(json_obj)

json_data = "{\"name\":123}"
json_obj = json.loads(json_data)
print(json_obj)

# Expecting property name enclosed in double quotes
# json_data = "{'name':123}"
# json_obj = json.loads(json_data)
# print(json_obj)

```
