OBJECT_FILES=main.o base-contact-sdk.o
CC=g++
TARGET=base-contact-sdk

INC_PATH = -I src/inc 
LIB_PATH = -L lib/

base-contact-sdk.o: src/base-contact-sdk.cpp 
	$(CC) $(INC_PATH) $(LIB_PATH) -c src/base-contact-sdk.cpp 
	
main.o: src/main.cpp
	$(CC) $(INC_PATH) $(LIB_PATH) -c src/main.cpp

$(TARGET): $(OBJECT_FILES)
	$(CC) $(INC_PATH) $(OBJECT_FILES) $(LIB_PATH) -o $(TARGET)

all: base-contact-sdk.o main.o $(TARGET)
	@echo done

clean: base-contact-sdk.o main.o $(TARGET)
	rm base-contact-sdk.o; rm main.o; rm $(TARGET)

libProjects.so: base-contact-sdk.o 
	$(CXX) -shared $^ -o $@ -Wl
base-contact-sdk.o : CXXFLAGS+=-fPIC
