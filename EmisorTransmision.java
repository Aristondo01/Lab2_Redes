
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
import java.util.Scanner;

public class EmisorTransmision {

    private static String HOST = "127.0.0.1";
    private static int PORT = 1235;

    public static void main(String[] args) throws IOException, UnknownHostException, InterruptedException {
        // ObjectOutputStream oos = null; //para serialized objects
        OutputStreamWriter writer = null;
        ObjectInputStream ois = null;
        System.out.println("Emisor Java Sockets\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la probabilidad de error (entre 0 y 1)");
        System.out.print("> ");
        String prob = scanner.nextLine();
        System.out.println("Ingrese 1 si desea usar CRC o 2 si desea usar Hamming");
        System.out.print("> ");
        String tipo = scanner.nextLine();
        int tipoInt = Integer.parseInt(tipo);
        double probability = Double.parseDouble(prob);
        // crear socket/conexion
        Socket socketCliente = new Socket(InetAddress.getByName(HOST), PORT);
        int cant = 10000;
        Ruido ruido = new Ruido();
        String payload = ruido.flipBits(tipoInt, probability);
        writer = new OutputStreamWriter(socketCliente.getOutputStream());
        writer.write(payload); // enviar payload
        writer.flush();
        Thread.sleep(30);
        // for (int i = 0; i < cant; i++) {
        //     String payload = ruido.flipBits(tipoInt, probability);
        //     // mandar data
        //     // System.out.println("Enviando Data\n");
        //     writer = new OutputStreamWriter(socketCliente.getOutputStream());
        //     // String payload = "Hola Mundo Java 11";
        //     writer.write(payload); // enviar payload
        //     writer.flush();
        //     Thread.sleep(30);
        // }

        // limpieza
        System.out.println("Liberando Sockets\n");
        writer.close();
        socketCliente.close();
        scanner.close();

        // TODO escuchar response
    }
}
