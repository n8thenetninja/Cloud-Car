import time
from socket import *

class CarCommand:
    """docstring for ."""
    def __init__(self, socketaddr):
        #super(, self).__init__()
        if re.match(r"^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$",ip):
            self.socketaddr = socketaddr
        else:
            raise ValueError('The socketaddr provided is not a valid IPv4 address')

    def send_command(self, number):
        pings = 1 #init
        #Send ping 10 times
        while pings < 11:

            #Create a UDP socket
            clientSocket = socket(AF_INET, SOCK_DGRAM)

            #Set a timeout value of 1 second
            clientSocket.settimeout(1)

            addr = (self.socketaddr, 12000)

            #may want to do more validation here...
            message = number

            #Send ping
            start = time.time()
            clientSocket.sendto(message, addr)

            #If data is received back from server, print
            try:
                data, server = clientSocket.recvfrom(1024)
                end = time.time()
                elapsed = end - start
                print data + " " + pings + " "+ elapsed

            #If data is not received back from server, print it has timed out
            except timeout:
                print 'REQUEST TIMED OUT'

            pings = pings - 1
