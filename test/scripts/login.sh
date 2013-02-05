#!/bin/sh
wget \
--debug \
--server-response \
--header='User-Agent: Minh Script' \
--header='Accept: */*' \
--header='Accept-Language: en-US' \
--header='Content-Type: text/xml; charset=utf-8' \
--header='SOAPAction: ""' \
--header='Cookie: "_smv_app_session=BAh7BjoPc2Vzc2lvbl9pZCIlNGQxNmM1YTc0NzZmNzViOWJjMWFiZWE5ODY0NmNmZDM%3D--0df7b048ce93df8af98fd1874fecd213057ac0fb"'\
--header='Accept-Encoding: gzip, deflate' \
--header='Connection: close' \
--header='Cache-Control: no-cache' \
http://localhost:8080/web/user/login \
--output-document='result.out'
