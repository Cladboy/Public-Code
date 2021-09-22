# --------------------------------------------------------
#           PYTHON PROGRAM
# Here is where we are going to define our set of...
# - Imports
# - Global Variables
# - Functions
# ...to achieve the functionality required.
# When executing > python 'this_file'.py in a terminal,
# the Python interpreter will load our program,
# but it will execute nothing yet.
# --------------------------------------------------------

# ------------------------------------------
# FUNCTION parse_in
# ------------------------------------------
def parse_in(input_name):
    f = open(input_name, "r")
    temp = f.readline()
    temp.split()

    matrix = []
    for x in f:
        tempMatrix = x.split()
        for i in range(int(temp[2])):
            if tempMatrix[i] == 'o':
                tempMatrix[i] = 0
        matrix.append(tempMatrix)
    f.close()
    return [temp[0], temp[2], matrix]


# ------------------------------------------
# FUNCTION solve
# ------------------------------------------
def solve(my_data):
    matrix = my_data[2]
    for x in range(int(my_data[1])):
        for y in range(int(my_data[0])):
            if matrix[y][x] == 'x':
                for subX in range(-1, 2):
                    for subY in range(-1, 2):
                        if y+subY >= 0 and x+subX >= 0:
                            if y + subY < int(my_data[0]) and x + subX < int(my_data[1]):
                                if matrix[y + subY][x + subX] != 'x':
                                    matrix[y + subY][x + subX] += 1

    return [my_data[0], my_data[1], matrix]


# ------------------------------------------
# FUNCTION parse_out
# ------------------------------------------
def parse_out(output_name, my_solution):
    f = open(output_name, "w")
    line1 = "" + my_solution[0] + " " + my_solution[1]
    f.write(line1)
    for x in my_solution[2]:
        f.write("\n")
        f.write(str(x))
    f.close()


# ------------------------------------------
# FUNCTION my_main
# ------------------------------------------
def my_main(input_name, output_name):
    # 1. We do the parseIn from the input file
    my_data = parse_in(input_name)

    # 2. We do the strategy to solve the problem
    my_solution = solve(my_data)

    # 3. We do the parse out to the output file
    parse_out(output_name, my_solution)


# ---------------------------------------------------------------
#           PYTHON EXECUTION
# This is the main entry point to the execution of our program.
# It provides a call to the 'main function' defined in our
# Python program, making the Python interpreter to trigger
# its execution.
# ---------------------------------------------------------------
if __name__ == '__main__':
    # 1. Name of input and output files
    input_name = "input_1.txt"
    output_name = "output.txt"

    # 2. Main function
    my_main(input_name, output_name)
