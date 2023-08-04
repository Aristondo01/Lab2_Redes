//import java.util.Scanner;

public class EmisorPresentacion {

    public String convertToBinary() {
        EmisorApp app = new EmisorApp();
        String input = app.solicitarMensaje();
        StringBuilder binaryStringBuilder = new StringBuilder();

        for (char c : input.toCharArray()) {
            String binary = Integer.toBinaryString(c);

            // while (binary.length() < 8) {
            // binary = "0" + binary;
            // }

            binaryStringBuilder.append(binary);
        }

        return binaryStringBuilder.toString();
    }
}
