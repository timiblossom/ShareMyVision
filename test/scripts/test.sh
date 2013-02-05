#!/bin/sh
wget \
--debug \
--server-response \
--post-data='{"Event":{"action":"cne","eventId":0,"model":0,"title":"Ti birthday","description":"Home party","uid":9001,"aid":111,"expirationDate":0,"isPublic":false}}' \
--header='SESSION: minh' \
--header='User-Agent: Minh Script' \
--header='Accept: */*' \
--header='Accept-Language: en-US' \
--header='Content-Type: application/json; charset=utf-8' \
--header='SOAPAction: ""' \
--header='Accept-Encoding: gzip, deflate' \
--header='Connection: close' \
--header='Cache-Control: no-cache' \
http://192.168.0.2:8081/web/item/ce \
--output-document='result.out'
