# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Contact library
# Navigate to core cpp library, make all and then copy the output (base-contact-sdk.so) into src/cpp/
# Copy the inc folder from the cpp library located in src/inc to src/main/cpp/inc
add_library( contact-lib
             SHARED
             IMPORTED )

set_target_properties( # Specifies the target library.
                    contact-lib

                    # Specifies the parameter you want to define.
                    PROPERTIES IMPORTED_LOCATION

                    # Provides the path to the library you want to import.
                    src/cpp/base-contact-sdk.so )

include_directories( src/main/cpp/inc )
