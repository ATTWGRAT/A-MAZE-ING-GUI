#!/bin/bash

rm -r ./out/
rm AMAZEING.jar

SOURCES=$(find ./src/ -type f -name "*.java")
CLASSPATH=$(find ./lib/ -type f -name "*.jar" -printf '%p:')

javac -d ./out/ -cp $CLASSPATH $SOURCES

cp ./lib/flatlaf-3.4.1.jar ./out/

cd ./out/

jar xf flatlaf-3.4.1.jar

rm -rf META-INF
rm module-info.class
rm flatlaf-3.4.1.jar

cp -a ../src/main/resources/. .

jar cmvf META-INF/MANIFEST.MF AMAZEING.jar .

cp AMAZEING.jar ..

cd ..

rm -rf ./out/

#java -cp ./lib/flatlaf-3.4.1.jar:./out/ AmazeingGui.Main
