#!/bin/sh
wget \
--debug \
--server-response \
--post-file='data' \
--header='User-Agent: Minh Script' \
--header='Accept: */*' \
--header='Accept-Language: en-US' \
--header='Content-Type: text/xml; charset=utf-8' \
--header='Accept-Encoding: gzip, deflate' \
--header='Connection: close' \
--header='Cache-Control: no-cache' \
http://sharemyvision.com:8080/main/upload \
--output-document='result.out'
