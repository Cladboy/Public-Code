import grpc
from concurrent import futures
import time
import line_pb2
import line_pb2_grpc
import threading

import logging
import datetime
import redis


class GetLineService(line_pb2_grpc.GetLineService):
    def __init__(self, *args, **kwargs):
        pass

    def GetLine(self, request, context):
        response = line_pb2.CheckReponse(received="Server.py: YES!")

        try:
            conn = redis.StrictRedis(host='redis', port=6379)
            conn.set("Line_Info:." + str(datetime.datetime.now()), ":" + request.lines)
        
        except Exception as ex:
            print('Error:', ex)
        
        return response

def serve():
    serverread = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    line_pb2_grpc.add_GetLineServiceServicer_to_server(GetLineService(), serverread)
    serverread.add_insecure_port('[::]:50052')
    serverread.start()
    serverread.wait_for_termination()

if __name__ == '__main__':
    logging.basicConfig()
    serve()



