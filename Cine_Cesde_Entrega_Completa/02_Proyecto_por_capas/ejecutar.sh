#!/usr/bin/env bash
set -eu
cd "$(dirname "$0")"
mkdir -p target/classes
find src/main/java -name '*.java' > target/fuentes.txt
javac --release 17 -encoding UTF-8 -d target/classes @target/fuentes.txt
java -cp target/classes app.Application
