#!/usr/bin/python
# --------------------------------------------------------
#
# PYTHON PROGRAM DEFINITION
#
# The knowledge a computer has of Python can be specified in 3 levels:
# (1) Prelude knowledge --> The computer has it by default.
# (2) Borrowed knowledge --> The computer gets this knowledge from 3rd party libraries defined by others
#                            (but imported by us in this program).
# (3) Generated knowledge --> The computer gets this knowledge from the new functions defined by us in this program.
#
# When launching in a terminal the command:
# user:~$ python3 this_file.py
# our computer first processes this PYTHON PROGRAM DEFINITION section of the file.
# On it, our computer enhances its Python knowledge from levels (2) and (3) with the imports and new functions
# defined in the program. However, it still does not execute anything.
#
# --------------------------------------------------------

# ------------------------------------------
# IMPORTS
# ------------------------------------------
import sys
import codecs

# ------------------------------------------
# FUNCTION my_reduce
# ------------------------------------------
from my_code.A01_Part4.my_mapper import my_map

def process_line(line):
    res = ()

    content = line.strip().split("\t")
    content_ints = content[1].strip().split(",")
    #content[1]

    station = content[0]
    start = int(content_ints[0][1:])
    finish = int(content_ints[1][:-1])

    res = (station, start, finish)

    return res

def write_line(my_output_stream, current_station, current_start, current_finish):
    # 1. We create the String
    my_str = current_station + "\t" + "(" + str(current_start) + ", " + str(current_finish) + ")\n"

    # 2. We write it to the file
    my_output_stream.write(my_str)

def my_reduce(my_input_stream, my_output_stream, my_reducer_input_parameters):

    current_station = ""
    current_start = 0
    current_finish = 0

    for line in my_input_stream:
        (station, start, finish) = process_line(line)

        if(station != current_station):
            if(current_station != ""):
                write_line(my_output_stream, current_station, current_start, current_finish)

            current_station = station
            current_start = 0
            current_finish = 0

        current_start += start
        current_finish += finish

    if (current_station != ""):
        write_line(my_output_stream, current_station, current_start, current_finish)


# ---------------------------------------------------------------
#           PYTHON EXECUTION
# This is the main entry point to the execution of our program.
# It provides a call to the 'main function' defined in our
# Python program, making the Python interpreter to trigger
# its execution.
# ---------------------------------------------------------------
if __name__ == '__main__':
    # 1. We collect the input values
    file_name = "sort_1.txt"

    # 1.1. If we call the program from the console then we collect the arguments from it
    if (len(sys.argv) > 1):
        file_name = sys.argv[1]

    # 2. Local or Hadoop
    local_False_hadoop_True = False

    # 3. We set the path to my_dataset and my_result
    my_input_stream = sys.stdin
    my_output_stream = sys.stdout

    if (local_False_hadoop_True == False):
        my_input_stream = "../../my_results/A01_Part4/2_my_sort_simulation/" + file_name
        my_output_stream = "../../my_results/A01_Part4/3_my_reduce_simulation/reduce_" + file_name[5:]

    # 4. my_reducer.py input parameters
    my_reducer_input_parameters = []

    # 5. We call to my_main
    my_map(my_input_stream, my_output_stream, my_reducer_input_parameters)
