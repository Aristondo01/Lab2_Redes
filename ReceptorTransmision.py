import socket
from HammingReceptor import Hamming
from CRC32Receptor import CR32

HOST = "127.0.0.1"  # IP, capa de Red. 127.0.0.1 es localhost
PORT = 1235        # Puerto, capa de Transporte

# AF_INET especifica IPv4,
#   tambien hay AF_UNIX para unix sockets y AF_INET6
# SOCK_STREAM especifica TCP,
#   tambien hay SOCK_DGRAM para UDP y otros...
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    #bind reserva/asigna tal socket a una IP:puerto especifica
    s.bind((HOST, PORT))
    s.listen()
    #accept() bloquea y deja esperando
    tipo = input("Ingrese el codificador que desea usar (1. CRC  2. Hamming):")
    conn, addr = s.accept()
    enlace = CR32() if tipo == "1" else Hamming()
    with conn:
        while True: #en caso se envien mas de 1024 bytes
            #recibir 1024 bytes
            data = conn.recv(1024)
            if not data:
                break   #ya se recibio todo
            decoded = str(data, "utf-8")
            if tipo == "2":
                enlace.detect_error(decoded)
            else:
                enlace.detect_error(decoded)

    enlace.get_estadisticas()
