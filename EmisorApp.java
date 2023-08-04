import java.util.Scanner;

public class EmisorApp {

    private Object[] checkInput(String input) {
        input = input.trim();
        input = input.replace(" ", "");
        int data_int[] = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '0' && input.charAt(i) != '1') {
                return new Object[] { false, null };
            }
            data_int[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return new Object[] { true, data_int };
    }

    public String solicitarMensaje() {
        System.out.println("Ingrese el mensaje que desea usar:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String trama = scanner.nextLine();
        Object[] response = checkInput(trama);
        boolean allGood = (boolean) response[0];
        int[] data_int = (int[]) response[1];
        if (!allGood) {
            System.out.println("Error: Ingrese correctamente la trama.");
        }
        scanner.close();
        return trama;
    }
}
