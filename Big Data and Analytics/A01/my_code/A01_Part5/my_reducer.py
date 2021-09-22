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
from my_code.A01_Part5.my_mapper import my_map

def process_line(line):
    res = ()

    content = line.strip().split("\t")
    content_ints = content[1].strip().split(",")

    bike_id = int(content_ints[0][1:])
    total_time = int (content_ints[1])
    total_trips = int(content_ints[2][:-1])

    res = (bike_id, total_time, total_trips)

    return res

def my_reduce(my_input_stream, my_output_stream, my_reducer_input_parameters):

    bike_dict = {}

    for line in my_input_stream:
        (bike_id, total_time, total_trips) = process_line(line)

        if (bike_id not in bike_dict):
            bike_dict[bike_id] = {"total_duration": total_time, "total_trips": total_trips}
        else:
            bike_dict[bike_id]["total_duration"] += total_time
            bike_dict[bike_id]["total_trips"] += total_trips

    bike_dict_sorted = reversed(sorted(bike_dict.items(), key=lambda x: x[1]["total_duration"]))

    count = 0
    for bike in bike_dict_sorted:
        if (count < my_reducer_input_parameters[0]):
            output = str(bike[0]) + "\t(" + str(bike[1]["total_duration"]) + ", " + str(bike[1]["total_trips"]) + ")\n"
            my_output_stream.write(output)
            count += 1
        else:
            break

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
    top_n_bikes = 10

    # 1.1. If we call the program from the console then we collect the arguments from it
    if (len(sys.argv) > 1):
        file_name = sys.argv[1]

    # 2. Local or Hadoop
    local_False_hadoop_True = False

    # 3. We set the path to my_dataset and my_result
    my_input_stream = sys.stdin
    my_output_stream = sys.stdout

    if (local_False_hadoop_True == False):
        my_input_stream = "../../my_results/A01_Part5/2_my_sort_simulation/" + file_name
        my_output_stream = "../../my_results/A01_Part5/3_my_reduce_simulation/reduce_" + file_name[5:]

    # 4. my_reducer.py input parameters
    my_reducer_input_parameters = []
    my_reducer_input_parameters.append( top_n_bikes )

    # 5. We call to my_main
    my_map(my_input_stream, my_output_stream, my_reducer_input_parameters)
