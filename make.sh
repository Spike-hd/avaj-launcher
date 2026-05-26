#!/bin/sh

set -e

find src -name "*.java" > sources.txt
mkdir -p out
javac -sourcepath src -d out @sources.txt
java -cp out fr.school42.avaj.simulator.Simulator scenario.txt
