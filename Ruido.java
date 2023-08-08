import java.util.Scanner;
import java.util.Random;

public class Ruido {

    public String flipBits(int tipo, double probability) {
        // 1: CRC; 2: Hamming
        int tipoInt = tipo;
        EmisorFactory emisorFactory = new EmisorFactory();
        EmisorEnlace emisor = emisorFactory.create(tipoInt);

        String bits = emisor.obtenerMensaje();

        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("La probabilidad debe estar en el rango de 0 a 1");
        }

        Random random = new Random();
        StringBuilder result = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < bits.length(); i++) {
            char bit = bits.charAt(i);
            char flippedBit = bit;

            if (random.nextDouble() < probability) {
                flippedBit = (bit == '0') ? '1' : '0';
                counter += 1;
            }

            result.append(flippedBit);
        }
        // System.out.println(counter);
        // System.out.println(result);
        // System.out.println("");
        return result.toString();
    }
}
