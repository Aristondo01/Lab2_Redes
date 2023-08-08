import java.util.Scanner;
import java.util.Random;

public class Ruido {

    public String flipBits() {
        // 1: CRC; 2: Hamming

        Scanner scanner = new Scanner(System.in);
        // System.out.println("Ingrese la probabilidad de error (entre 0 y 1)");
        // System.out.print("> ");
        // String prob = scanner.nextLine();
        // System.out.println("Ingrese 1 si desea usar CRC o 2 si desea usar Hamming");
        // System.out.print("> ");
        // String tipo = scanner.nextLine();
        // int tipoInt = Integer.parseInt(tipo);
        // double probability = Double.parseDouble(prob);
        int tipoInt = 1;
        double probability = 0.0;
        EmisorFactory emisorFactory = new EmisorFactory();
        EmisorEnlace emisor = emisorFactory.create(tipoInt);

        String bits = emisor.obtenerMensaje();

        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("La probabilidad debe estar en el rango de 0 a 1");
        }

        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < bits.length(); i++) {
            char bit = bits.charAt(i);
            char flippedBit = bit;

            if (random.nextDouble() < probability) {
                flippedBit = (bit == '0') ? '1' : '0';
            }

            result.append(flippedBit);
        }
        scanner.close();
        return result.toString();
    }
}
