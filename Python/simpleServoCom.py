import smbus
import time
import random
import socket

udp_ip = "192.168.1.119"
udp_port = 5005
bus = smbus.SMBus(1)
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((udp_ip, udp_port))
address = 0x3c
channel = 0x00
data = 0x10
position = 0x10

while True:
    data, addr = sock.recvfrom(1024)
    #status = raw_input("Press enter to change position")
    #if status == "end":
    #    break
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
    #position = random.randint(0x08,0x1a)
    #bus.write_byte_data(address,channel,position)
    
    
