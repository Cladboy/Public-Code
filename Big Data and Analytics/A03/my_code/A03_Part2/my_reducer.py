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
    res = ()
    content = line.strip().split("\t")
    content_ints = content[1].strip().split(",")

    birth_year = content[0]
    start_hour = int(content_ints[0][1:])
    trip_num = int(content_ints[1][:-1])

    res = (birth_year, start_hour, trip_num)
    return res


def my_reduce(my_input_stream, my_output_stream, my_reducer_input_parameters):
    resDict = {}
    for line in my_input_stream:
        #print(process_line(line))
        (birth_year, start_hour, trip_num) = process_line(line)
        if birth_year not in resDict:
            #resDict[birth_year][start_hour] = trip_num
            resDict[birth_year] = {start_hour: trip_num}
        elif start_hour not in resDict[birth_year]:
            resDict[birth_year] = {start_hour: trip_num}
        else:
            resDict[birth_year][start_hour] += trip_num

    finalResDict = {}
    for i in sorted(resDict):
        sorted_starts = sorted(resDict[i].items(), key=lambda x: x[1], reverse=True)
        # print(i, sorted_starts[0])
        finalResDict[i] = sorted_starts[0]


    for year in finalResDict:
        output = str(year) + "\t(" + str(finalResDict[year][0]) + ", " + str(finalResDict[year][1]) + ")\n"
        my_output_stream.write(output)




'''
    finalResDict = {}
    for i in sorted(resDict):
        sorted_starts = sorted(resDict[i].items(), key=lambda x: x[1], reverse=True)
        # print(i, sorted_starts[0])
        finalResDict[i] = sorted_starts[0]

    output_data = open(output_file, "w")
    for year in finalResDict:
        output = str(year) + "\t(" + finalResDict[year][0] + ", " + str(finalResDict[year][1]) + ")\n"
        output_data.write(output)
    output_data.close()
'''
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
