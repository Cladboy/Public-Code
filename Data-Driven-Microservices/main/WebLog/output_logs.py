
from flask import Flask

import redis

app = Flask(__name__)

@app.route('/')
def print_logs():
    output = ''
    try:
        conn = redis.StrictRedis(host='redis', port=6379)
        for key in conn.scan_iter("Line_Info:*"):
            value = str(conn.get(key))
            output += value + '<br>'
    except Exception as ex:
        output = 'Error:' + str(ex)
    return output

@app.route('/GetLines/', methods=['GET'])
def get_time():
    output = ''
    output_dict = {}

    try:
        conn = redis.StrictRedis(host='redis', port=6379)

        for key in conn.scan_iter("Line_Info:*"):
            value = conn.get(key).decode('utf-8')
            output += value
        
        output_dict["lines"] = output

    except Exception as ex:
        output = 'Error:' + str(ex)

    return output_dict, 200


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')