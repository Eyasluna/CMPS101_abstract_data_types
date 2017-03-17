cmake_minimum_required(VERSION 3.6)
project(PA2_2)

set(CMAKE_C_STANDARD 11)

set(SOURCE_FILES
        List.c
        List.h
        ListClient.c Lex.c)
add_executable(PA2_2 ${SOURCE_FILES})