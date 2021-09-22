import os
import grpc
import time
import line_pb2
import line_pb2_grpc

import logging

import linereader


def run():

    vowels = ['a', 'e', 'i', 'o', 'u']

    # metrics
    word_count = 0
    word_starting_with_vowel = 0
    total_word_length = 0
    boat_count = 0

    # Counter for tracking metrics
    counter = 0

    with grpc.insecure_channel('serverread:50052') as channel:
        stub = line_pb2_grpc.GetLineServiceStub(channel)

        for data in linereader.get_lines():
            data_split = data.split(" @ ")
            data_split.pop(-1)
            print("\nCounter: ", str(counter), "\n")

            for line in data_split:
                metrics = "<br>"
                line.lower()
                line.isalnum()
                words = line.split(" ")

                counter += 1

                for word in words:
                    word_count += 1
                    total_word_length += len(word)

                    if word[0] in vowels:
                        word_starting_with_vowel += 1

                    contains_boat = word.find("boat")
                    if contains_boat >= 0:
                        boat_count += 1
                
                


                metrics += "<br>------------------------------------------------------------<br>"
                metrics += "To The Lighthouse Metrics #:" + str(counter) + ":" + "<br>"
                metrics += "<br\"" + line + "\"<br>"
                metrics += "-----------------------------<br>"

                metrics += "Percentage of Words begining with vowel: " + str((( word_starting_with_vowel / word_count) * 100 ))+ "%<br>"

                metrics += "Percentage of Words NOT begining with vowel: " + str((((word_count - word_starting_with_vowel) / word_count) * 100 )) + "%<br>"

                metrics += "Average word length: " + str((total_word_length / word_count)) + "<br>"

                metrics += "Boat count: " + str(boat_count) + "<br>"

                # word_count = 0
                # word_starting_with_vowel = 0
                # total_word_length = 0
                # boat_count = 0

                metrics += "DEBUG_word_count: " + str(word_count) + "<br>"

                metrics += "DEBUG_word_starting_with_vowel: " + str(word_starting_with_vowel) + "<br>"

                metrics += "DEBUG_total_word_length: " + str(total_word_length) + "<br>"


                response = stub.GetLine(line_pb2.Lines(lines=metrics))


        print("Client.py: ", response.received)
        time.sleep(120)


if __name__ == "__main__":
    logging.basicConfig()
    run()