# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.23

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2022.2.1\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2022.2.1\bin\cmake\win\bin\cmake.exe" -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\krist\Documents\project\algorithms\quicksort

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\krist\Documents\project\algorithms\quicksort\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/quicksort.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/quicksort.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/quicksort.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/quicksort.dir/flags.make

CMakeFiles/quicksort.dir/quicksort.cpp.obj: CMakeFiles/quicksort.dir/flags.make
CMakeFiles/quicksort.dir/quicksort.cpp.obj: ../quicksort.cpp
CMakeFiles/quicksort.dir/quicksort.cpp.obj: CMakeFiles/quicksort.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\krist\Documents\project\algorithms\quicksort\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/quicksort.dir/quicksort.cpp.obj"
	C:\PROGRA~1\JETBRA~1\CLION2~1.1\bin\mingw\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/quicksort.dir/quicksort.cpp.obj -MF CMakeFiles\quicksort.dir\quicksort.cpp.obj.d -o CMakeFiles\quicksort.dir\quicksort.cpp.obj -c C:\Users\krist\Documents\project\algorithms\quicksort\quicksort.cpp

CMakeFiles/quicksort.dir/quicksort.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/quicksort.dir/quicksort.cpp.i"
	C:\PROGRA~1\JETBRA~1\CLION2~1.1\bin\mingw\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\krist\Documents\project\algorithms\quicksort\quicksort.cpp > CMakeFiles\quicksort.dir\quicksort.cpp.i

CMakeFiles/quicksort.dir/quicksort.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/quicksort.dir/quicksort.cpp.s"
	C:\PROGRA~1\JETBRA~1\CLION2~1.1\bin\mingw\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\krist\Documents\project\algorithms\quicksort\quicksort.cpp -o CMakeFiles\quicksort.dir\quicksort.cpp.s

CMakeFiles/quicksort.dir/single_pivot.cpp.obj: CMakeFiles/quicksort.dir/flags.make
CMakeFiles/quicksort.dir/single_pivot.cpp.obj: ../single_pivot.cpp
CMakeFiles/quicksort.dir/single_pivot.cpp.obj: CMakeFiles/quicksort.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\krist\Documents\project\algorithms\quicksort\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/quicksort.dir/single_pivot.cpp.obj"
	C:\PROGRA~1\JETBRA~1\CLION2~1.1\bin\mingw\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/quicksort.dir/single_pivot.cpp.obj -MF CMakeFiles\quicksort.dir\single_pivot.cpp.obj.d -o CMakeFiles\quicksort.dir\single_pivot.cpp.obj -c C:\Users\krist\Documents\project\algorithms\quicksort\single_pivot.cpp

CMakeFiles/quicksort.dir/single_pivot.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/quicksort.dir/single_pivot.cpp.i"
	C:\PROGRA~1\JETBRA~1\CLION2~1.1\bin\mingw\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\krist\Documents\project\algorithms\quicksort\single_pivot.cpp > CMakeFiles\quicksort.dir\single_pivot.cpp.i

CMakeFiles/quicksort.dir/single_pivot.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/quicksort.dir/single_pivot.cpp.s"
	C:\PROGRA~1\JETBRA~1\CLION2~1.1\bin\mingw\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\krist\Documents\project\algorithms\quicksort\single_pivot.cpp -o CMakeFiles\quicksort.dir\single_pivot.cpp.s

CMakeFiles/quicksort.dir/dual_pivot.cpp.obj: CMakeFiles/quicksort.dir/flags.make
CMakeFiles/quicksort.dir/dual_pivot.cpp.obj: ../dual_pivot.cpp
CMakeFiles/quicksort.dir/dual_pivot.cpp.obj: CMakeFiles/quicksort.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\krist\Documents\project\algorithms\quicksort\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/quicksort.dir/dual_pivot.cpp.obj"
	C:\PROGRA~1\JETBRA~1\CLION2~1.1\bin\mingw\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/quicksort.dir/dual_pivot.cpp.obj -MF CMakeFiles\quicksort.dir\dual_pivot.cpp.obj.d -o CMakeFiles\quicksort.dir\dual_pivot.cpp.obj -c C:\Users\krist\Documents\project\algorithms\quicksort\dual_pivot.cpp

CMakeFiles/quicksort.dir/dual_pivot.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/quicksort.dir/dual_pivot.cpp.i"
	C:\PROGRA~1\JETBRA~1\CLION2~1.1\bin\mingw\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\krist\Documents\project\algorithms\quicksort\dual_pivot.cpp > CMakeFiles\quicksort.dir\dual_pivot.cpp.i

CMakeFiles/quicksort.dir/dual_pivot.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/quicksort.dir/dual_pivot.cpp.s"
	C:\PROGRA~1\JETBRA~1\CLION2~1.1\bin\mingw\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\krist\Documents\project\algorithms\quicksort\dual_pivot.cpp -o CMakeFiles\quicksort.dir\dual_pivot.cpp.s

# Object files for target quicksort
quicksort_OBJECTS = \
"CMakeFiles/quicksort.dir/quicksort.cpp.obj" \
"CMakeFiles/quicksort.dir/single_pivot.cpp.obj" \
"CMakeFiles/quicksort.dir/dual_pivot.cpp.obj"

# External object files for target quicksort
quicksort_EXTERNAL_OBJECTS =

quicksort.exe: CMakeFiles/quicksort.dir/quicksort.cpp.obj
quicksort.exe: CMakeFiles/quicksort.dir/single_pivot.cpp.obj
quicksort.exe: CMakeFiles/quicksort.dir/dual_pivot.cpp.obj
quicksort.exe: CMakeFiles/quicksort.dir/build.make
quicksort.exe: CMakeFiles/quicksort.dir/linklibs.rsp
quicksort.exe: CMakeFiles/quicksort.dir/objects1.rsp
quicksort.exe: CMakeFiles/quicksort.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\krist\Documents\project\algorithms\quicksort\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Linking CXX executable quicksort.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\quicksort.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/quicksort.dir/build: quicksort.exe
.PHONY : CMakeFiles/quicksort.dir/build

CMakeFiles/quicksort.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\quicksort.dir\cmake_clean.cmake
.PHONY : CMakeFiles/quicksort.dir/clean

CMakeFiles/quicksort.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\krist\Documents\project\algorithms\quicksort C:\Users\krist\Documents\project\algorithms\quicksort C:\Users\krist\Documents\project\algorithms\quicksort\cmake-build-debug C:\Users\krist\Documents\project\algorithms\quicksort\cmake-build-debug C:\Users\krist\Documents\project\algorithms\quicksort\cmake-build-debug\CMakeFiles\quicksort.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/quicksort.dir/depend

