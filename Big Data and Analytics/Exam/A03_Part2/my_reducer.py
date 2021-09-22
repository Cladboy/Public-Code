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
def process_line(line):
    temp = line.split("\t")
    temp = temp[1]
    temp = temp.split(", ")
    #print(temp)
    #print("\n\ntemp[1]: ", temp[1], "\ntemp[1][:-2]: ", temp[1][:-2], "\n\n")
    trip_id = temp[0][1:]
    trip_duration = temp[1][:-2]
    #print("trip_id : '", trip_id, "' || trip_duration : '", trip_duration, "'")
    return int(trip_id), int(trip_duration)


def my_reduce(my_input_stream, my_output_stream, my_reducer_input_parameters):
    longest_trip_id = 0
    longest_trip_duration = 0

    for line in my_input_stream:
        trip_id, trip_duration = process_line(line)
        #print("\n\ntrip_id : '", trip_id, "' || trip_duration : '", trip_duration, "'")
        if trip_duration > longest_trip_duration:
            #print(trip_duration, ">", longest_trip_duration)
            longest_trip_id = trip_id
            longest_trip_duration = trip_duration

    output = str(longest_trip_id) + "\t(" + str(longest_trip_duration) + ")\n"
    my_output_stream.write(output)


# ---------------------------------------------------------------
#           PYTHON EXECUTION
# This is the main entry point to the execution of our program.
# It provides a call to the 'main function' defined in our
# Python program, making the Python interpreter to trigger
# its execution.
# ---------------------------------------------------------------
if __name__ == '__main__':
    # 1. We collect the input values
    my_input_stream = sys.stdin
    my_output_stream = sys.stdout
    my_reducer_input_parameters = []

    # 5. We call to my_reduce
    my_reduce(my_input_stream,
              my_output_stream,
              my_reducer_input_parameters
              )
