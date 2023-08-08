import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmisorApp {

    private static int cont = -1;
    private static boolean flag = false;
    private static String[] palabras = new String[100000];

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

    private String leerArchivo() {
        cont++;
        if (!flag) {
            String filename = "palabras.txt"; // Reemplaza "ruta/del/archivo.txt" con la ubicación de tu archivo.

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                int mini_contador = 0;
                while ((line = br.readLine()) != null) {
                    palabras[mini_contador] = line.trim();
                    mini_contador++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = true;
            return palabras[cont];
        } else {
            return palabras[cont];
        }

    }

    public String solicitarMensaje() {
        System.out.println("Ingrese el mensaje que desea usar:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String trama = scanner.nextLine();
        Object[] response = checkInput(trama);
        boolean allGood = (boolean) response[0];
        int[] data_int = (int[]) response[1];
        // if (!allGood) {
        // System.out.println("Error: Ingrese correctamente la trama.");
        // }
        scanner.close();
        return trama;
        // String trama = leerArchivo();
        // return trama;
    }
}
