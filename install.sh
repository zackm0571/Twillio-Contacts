#!/bin/bash

ANDROID_LIB_DIR=Android/TwilioContacts/app/src/main/cpp

git submodule update --init --recursive
cd Twillio-Contacts-Core-Lib
make all
cd ..
cp Twillio-Contacts-Core-Lib/base-contact-sdk.so $ANDROID_LIB_DIR/libcontact-lib.so
cp -r Twillio-Contacts-Core-Lib/src/inc $ANDROID_LIB_DIR/.
