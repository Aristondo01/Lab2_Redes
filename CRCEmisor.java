import java.util.Arrays;
import java.util.Scanner;

public class CRCEmisor {

    public static int[] XOR(int[] a, int[] b) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = (a[i] != 0) ^ (b[i] != 0) ? 1 : 0;
        }

        return result;
    }

    public static int calculateCRC(String trama) {
        int[] binaryData = new int[trama.length() + 32];
        for (int i = 0; i < trama.length(); i++) {
            binaryData[i] = Integer.parseInt(String.valueOf(trama.charAt(i)));
        }

        int[] CRC_32 = { 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0,
                0 };

        int start = 0;
        int end = 32;
        // while (end < binaryData.length) {
        // int[] subArray = Arrays.copyOfRange(binaryData, start, end);

        // }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la trama");
        System.out.print("> ");
        String trama = scanner.nextLine();
        calculateCRC(trama);
    }
}