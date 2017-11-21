OBJECT_FILES=main.o base-contact-sdk.o
CC=g++
TARGET=base-contact-sdk

INC_PATH = -I src/inc
LIB_PATH = -L lib

base-contact-sdk.o: src/base-contact-sdk.cpp 
	$(CC) $(INC_PATH) $(LIB_PATH) -c src/base-contact-sdk.cpp 
	
main.o: src/main.cpp
	$(CC) $(INC_PATH) $(LIB_PATH) -c src/main.cpp

$(TARGET): $(OBJECT_FILES)
	$(CC) $(INC_PATH) $(OBJECT_FILES) $(LIB_PATH) -o $(TARGET)
all: base-contact-sdk.o main.o $(TARGET)
	@echo done
