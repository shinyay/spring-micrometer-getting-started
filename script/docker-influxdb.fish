#!/usr/bin/env fish

docker run --rm -d \
--name=influxdb \
-p 8086:8086 \
-v /tmp:/var/lib/influxdb \
influxdb
