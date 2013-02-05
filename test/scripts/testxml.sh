#!/bin/sh
wget \
--debug \
--server-response \
--post-data='<Event><Action>createevent</Action><Address>ACT0</Address><Address>ACT1</Address></Event>' \
--header='User-Agent: Minh Script' \
--header='Accept: */*' \
--header='Accept-Language: en-US' \
--header='Content-Type: text/xml; charset=utf-8' \
--header='SOAPAction: ""' \
--header='Accept-Encoding: gzip, deflate' \
--header='Connection: close' \
--header='Cache-Control: no-cache' \
http://localhost:8080/web/item/ce \
--output-document='result.out'
