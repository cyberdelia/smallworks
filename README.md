# Smallworks

A service to upload file temporarely to S3.

## Usage

Smallworks is made to work with curl:

```console
$ curl -F file=@file.tar.gz smallworks.host.name
https://bucket.s3.amazonaws.com/1ae0329503d9b8334a14749cc9ff0ce1?Expires=1409865625&AWSAccessKeyId=ABCDEFGHIJKLMNOPQRST&Signature=9Zt0sOt73AdRfO1TONX6PzzM6%2Bg%3D
```
