# Spring Actuator with Micrometer

The `metrics actuator` to create a self-hosted monitoring solution for Spring Boot applications.


- [Metrics Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-metrics.html)

## Description

### Metrics Database: InfluxDB
```
docker run --rm -d \
--name=influxdb \
-p 8086:8086 \
-v /tmp:/var/lib/influxdb \
influxdb
```

```yaml
management:
  metrics:
    enable:
      root: true
      jvm: true
    export:
      influx:
        enabled: true
        uri: "http://localhost:8086"
```

### Prometheus
- [Micrometer Prometheus](https://micrometer.io/docs/registry/prometheus)

```
$ docker run --rm -d \
--name=prometheus \
-p 9090:9090 \
-v (pwd)/prometheus.yml:/etc/prometheus/prometheus.yml \
prom/prometheus:v2.13.1 \
--config.file=(pwd)/prometheus.yml
```

- prometheus.yml
```yaml
scrape_configs:
  - job_name: 'spring-actuator'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['127.0.0.1:8080']
```

```
implementation("io.micrometer:micrometer-registry-prometheus:latest.release")
```

### Metrics Database: Graphite

```
docker run --rm -d \
 --name graphite \
 -p 80:80 \
 -p 2003-2004:2003-2004 \
 -p 2023-2024:2023-2024 \
 -p 8125:8125/udp \
 -p 8126:8126 \
 graphiteapp/graphite-statsd:1.1.6-1
```

```
implementation("io.micrometer:micrometer-registry-graphite:1.3.1")
```

```yaml
management:
  metrics:
    export:
      graphite:
        host: 127.0.0.1
        port: 2004
```

### Grafana

```yaml
docker run --rm -d \
 --name=grafana \
 -p 3000:3000 \
 grafana/grafana:6.4.4
```

- user/password : `admin/admin`

## Demo

## Features

- feature:1
- feature:2

## Requirement

## Usage

## Installation

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
