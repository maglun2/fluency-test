#!/usr/bin/env bash

set -o nounset -o pipefail -o errexit

docker stop fluency_fluentd1_1
sleep 10
docker stop fluency_fluentd2_1

#sleep 240
for i in {001..240}; do
    sleep 1
    printf "\r $i"

done

docker start fluency_fluentd2_1
sleep 5
docker start fluency_fluentd1_1
