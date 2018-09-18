#!/usr/bin/env sh
set -e
export ROOT_FOLDER=$( pwd )
export GRADLE_OPTS=-Dorg.gradle.native=false

GRADLE_HOME="${HOME}/.gradle"
GRADLE_CACHE="${ROOT_FOLDER}/gradle"

[[ -d "${GRADLE_CACHE}" && ! -d "${GRADLE_HOME}" ]] && ln -s "${GRADLE_CACHE}" "${GRADLE_HOME}"

version=`cat version/number`
cd music-repo
echo $version
./gradlew assemble --no-daemon
ls -la build/libs/
mv build/libs/music-repo-*.jar ../workspace/spring-music-$version.jar
ls -la ../workspace/


