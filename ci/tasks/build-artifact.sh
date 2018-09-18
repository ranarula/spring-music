#!/usr/bin/env bash
set -e

export GRADLE_OPTS=-Dorg.gradle.native=false
version=`cat version/number`
cd music-repo
echo $version
./gradlew assemble
ls -la build/libs/
mv build/libs/music-repo-*.jar ../workspace/spring-music-$version.jar
ls -la ../workspace/


