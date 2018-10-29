#!/bin/bash

export TERM=${TERM:-dumb}
cd resource-git
./gradlew --no-daemon build
