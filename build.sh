#!/bin/bash

if [ -d "./target/" ]
then
    rm -r target/
    mkdir target
fi

mkdir -p .m2


docker run -it --rm -u 1000 \
    -e MAVEN_CONFIG=/var/maven/.m2 \
    -v "$PWD":/usr/src/mymaven \
    -v "$PWD/.m2":/var/maven/.m2 \
    -w /usr/src/mymaven \
    maven:3-openjdk-11 mvn -Duser.home=/var/maven clean package
