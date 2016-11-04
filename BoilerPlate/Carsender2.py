"""
    UDP server sender
"""


import socket
import sys


HOST =      "192.168.1.1"#host
PORT =      10#port number

try: 
    s = socket.socket(socket.AF.INET, socket.SOCK_DRAM)
except:  #socket.error, msg
	print "something broke"
	sys.exit()
    
while 1:
    d = s.recvfrom(1024)
    data = d[0]
    addr = d[1]
    if not data:
        break