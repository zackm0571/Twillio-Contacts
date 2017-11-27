#!/bin/bash

ANDROID_CPP_DIR=Android/TwilioContacts/app/src/main/cpp
BASE_CPP=Cpp/src/base-contact-sdk.cpp 
BASE_H=Cpp/src/inc/base-contact-sdk.h
RAPID_JSON=Cpp/src/inc/rapidjson

cd Cpp
make all
cd ..
cp -r $BASE_CPP  $ANDROID_CPP_DIR/.
cp -r $BASE_H  $ANDROID_CPP_DIR/.
cp -r $RAPID_JSON $ANDROID_CPP_DIR/.

