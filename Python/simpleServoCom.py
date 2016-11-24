"""  
Servo control reciever for cloud car project
This module receives commands via UDP from 
the controller and sends servo channel and
position data to the servo controller via
I2C.

Author: Nathan Larson
Date: 11/12/2016

Changelog:
11/23/2016 - Added deadman switch logic
11/24/2016 - Added automatic IP address discovery

"""

import smbus
import time
import random
import socket
import select

#udp_ip = "192.168.1.119"
udp_port = 5005
bus = smbus.SMBus(1)
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.connect(('8.8.8.8',0))
udp_ip = str(sock.getsockname()[0])
sock.close()
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((udp_ip, udp_port))
address = 0x3c
channel = 0x00
data = 0x10
position = 0x10

print 'running'
sock.settimeout(.2)
while True:
    try:
        data, addr = sock.recvfrom(1024)
    except:
        bus.write_byte_data(address,0x00,0x09)
        pass
    if data == "chan0":
        channel = 0x00
    elif data == "chan1":
        channel = 0x01
    elif data == "chan2":
        channel = 0x02
    elif data == "chan3":
        channel = 0x03
    elif data == "end":
        break
    elif data != position:
        position = int(data)
        bus.write_byte_data(address,channel,position)
