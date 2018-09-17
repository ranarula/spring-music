#!/usr/bin/env bash
set -e

export GRADLE_OPTS=-Dorg.gradle.native=false
version=`cat version/number`
cd music-repo
#echo $version
./gradlew assemble -PVersion=$version
ls build/libs/
#mv build/libs/spring-music-*.jar ~/workspace/

