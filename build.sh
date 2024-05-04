#!/bin/bash

rm -r ./out/

SOURCES=$(find ./src/ -type f -name "*.java")
CLASSPATH=$(find ./lib/ -type f -name "*.jar" -printf '%p:')

javac -d ./out/ -cp $CLASSPATH $SOURCES

cp -a ./src/main/resources/. ./out/

java -cp ./lib/flatlaf-3.4.1.jar:./out/ AmazeingGui.Main
