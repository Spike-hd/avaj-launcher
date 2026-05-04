#!/bin/sh

find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java ua.unit.avaj.Simulator scenario.txt