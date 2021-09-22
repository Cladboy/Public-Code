from random import randint
import time
import codecs

def get_lines():
    # data = open("ClientRead/ToTheLighthouse.txt")
    data_input_stream = codecs.open("ClientRead/ToTheLighthouse.txt", "r", encoding='utf-8')

    file_lines = data_input_stream.readlines()



    size_of_data = len(file_lines)
    
    for i in range(size_of_data//20):
        chunk = file_lines[0:20]
        file_lines = file_lines[20:]
        
        message = ""

        for j in range(len(chunk)):
            # Take chunks out of the lines
            line = chunk[j]
            #print(line)

            # Construct the message string
            message += line + " @ "

        yield message

        time.sleep(120)
    
    data_input_stream.close()

#get_lines()