
//Sockets en Java - Emisor
//Para TCP usar Socket, para UDP DatagramSocket
//En versiones anteriores de java todo era via Socket(stream/datagram)
//para recibir se usar ServerSocket
import java.net.Socket;
import java.net.InetAddress;
//input stream reader para leer
import java.io.OutputStreamWriter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;

public class EmisorTransmision {

    private static String HOST = "127.0.0.1";
    private static int PORT = 1235;

    public static void main(String[] args) throws IOException, UnknownHostException, InterruptedException {
        // ObjectOutputStream oos = null; //para serialized objects
        OutputStreamWriter writer = null;
        ObjectInputStream ois = null;
        System.out.println("Emisor Java Sockets\n");

        // crear socket/conexion
        Socket socketCliente = new Socket(InetAddress.getByName(HOST), PORT);
        int cant = 5;
        Ruido ruido = new Ruido();
        for (int i = 0; i < cant; i++) {
            String payload = ruido.flipBits();
            // mandar data
            // System.out.println("Enviando Data\n");
            writer = new OutputStreamWriter(socketCliente.getOutputStream());
            // String payload = "Hola Mundo Java 11";
            writer.write(payload); // enviar payload
            writer.flush();
            Thread.sleep(10);
        }

        // limpieza
        System.out.println("Liberando Sockets\n");
        writer.close();
        socketCliente.close();

        // TODO escuchar response
    }
}
