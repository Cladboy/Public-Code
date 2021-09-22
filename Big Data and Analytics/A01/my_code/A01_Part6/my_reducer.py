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
from my_code.A01_Part6.my_mapper import my_map

def process_line(line):
    res = []

    content = line.strip().split("\t")
    content_ints = content[1].strip().split(" @ ")

    content_ints[0] = content_ints[0][1:]
    content_ints[len(content_ints)-1] = content_ints[len(content_ints)-1][:-1]


    while len(content_ints)>0:
        start_time = content_ints[0]
        finish_time = content_ints[1]
        start_station = content_ints[2]
        finish_station = content_ints[3]

        temp_res = (start_time, finish_time, start_station, finish_station)
        res.append(temp_res)

        content_ints = content_ints[4:]

    return res

def my_reduce(my_input_stream, my_output_stream, my_reducer_input_parameters):
    trip_dict = {}
    trip_count = 0
    for line in my_input_stream:
        dataset = process_line(line)
        for trip in dataset:
            trip_dict[trip_count] = {"start_time": trip[0],
                                     "finish_time": trip[1],
                                     "start_station": trip[2],
                                     "finish_station": trip[3]}
            trip_count += 1

    # for item in trip_dict:
    #     print(item[1])
    # print("\n\n________________________________________\n\n")

    trucked_dict = {}
    trucked_count = 0
    for i in range(trip_count - 1):
        if trip_dict[i]["finish_station"] != trip_dict[i + 1]["start_station"]:
            trucked_dict[trucked_count] = {"start_move_time": trip_dict[i]["finish_time"],
                                           "start_move_station": trip_dict[i]["finish_station"],
                                           "finish_move_time": trip_dict[i + 1]["start_time"],
                                           "finish_move_station": trip_dict[i + 1]["start_station"]}
            trucked_count += 1

    trucked_dict = trucked_dict.items()

    for trip in trucked_dict:
        #print(trip)
        output = "By_Truck\t(" + str(trip[1]["start_move_time"]) + ", " + str(trip[1]["start_move_station"]) + ", " + str(trip[1]["finish_move_time"]) + ", " + str(trip[1]["finish_move_station"]) + ")\n"
        my_output_stream.write(output)


    # trucked_dict = {}
    # trucked_count = 0
    # for i in range(trip_count - 1):
    #     if trip_dict[i]["finish_station"] != trip_dict[i + 1]["start_station"]:
    #         trucked_dict[trucked_count] = {"start_move_time": trip_dict[i]["finish_time"],
    #                                        "start_move_station": trip_dict[i]["finish_station"],
    #                                        "finish_move_time": trip_dict[i + 1]["start_time"],
    #                                        "finish_move_station": trip_dict[i + 1]["start_station"]}
    #         trucked_count += 1
    #
    # trucked_dict = trucked_dict.items()

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
        my_input_stream = "../../my_results/A01_Part6/2_my_sort_simulation/" + file_name
        my_output_stream = "../../my_results/A01_Part6/3_my_reduce_simulation/reduce_" + file_name[5:]

    # 4. my_reducer.py input parameters
    my_reducer_input_parameters = []

    # 5. We call to my_main
    my_map(my_input_stream, my_output_stream, my_reducer_input_parameters)
