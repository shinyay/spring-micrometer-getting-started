#!/usr/bin/env fish

docker run --rm -d \
 --name=grafana \
 -p 3000:3000 \
 grafana/grafana:6.4.4
