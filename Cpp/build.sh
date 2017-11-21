#!/bin/bash
FILES="src/main.cpp src/base-contact-sdk.cpp"
INC="-I src/inc"
CC=g++
OUTPUT=base-contact-sdk
OBJECTS=main.o base-contact-sdk.o

$(CC) $(INC) $(FILES)

$(CC) $(INC) $(OBJECTS) -o $(OUTPUT)
