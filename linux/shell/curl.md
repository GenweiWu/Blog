

## 批量curl

> https://stackoverflow.com/a/17032673 
```shell

#set -x 

generate_post_data()
{

#echo $id

  cat <<EOF
{
  "ID": "$id",
  "Type": "1",
  "showButtons": false
}
EOF
}

function deleteById(){
id=$1

			
curl --location --request POST 'http://1.2.3.4/test-service/deleteDoc' \
--header 'Content-Type: application/json' \
--data "$(generate_post_data)"

}


#source ./id.pub
ids=(
111
222
333
...
)


for i in "${ids[@]}";do
  deleteById $i
done

#deleteById 1234567



```
