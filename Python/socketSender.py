import socket

udp_ip = "192.168.1.119"
udp_port = 5005
message = 0x00

while True:
    message = raw_input("enter position: ")
    if message == "end":
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.sendto(message, (udp_ip, udp_port))
        break
    else:
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.sendto(message, (udp_ip, udp_port))
