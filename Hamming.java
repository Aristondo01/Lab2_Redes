//import java.util.Scanner;

public class Hamming implements EmisorEnlace {

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

    private int calculate_p(int data) {
        int p = 0;
        while (Math.pow(2, p) < (p + data + 1)) {
            p++;
        }

        return p;
    }

    private int[] calculateTable(int[] data, int p) {

        int[] pos = new int[p];
        int j = 0;
        int[] result = new int[data.length + p];

        for (int i = 0; i < result.length; i++) {
            if (Math.pow(2, j) == i + 1) {
                pos[j] = i;
                j++;
            }
        }

        int cont_pos = 0;
        int data_pos = data.length - 1;
        for (int i = 0; i < result.length; i++) {
            if (pos[cont_pos] == i) {
                result[i] = 2;
                cont_pos++;
                if (cont_pos >= pos.length) {
                    cont_pos--;
                }
            } else {
                result[i] = data[data_pos];
                data_pos--;
                if (data_pos >= data.length) {
                    data_pos++;
                }
            }
        }

        int TableParity[] = new int[p];
        for (int i = 0; i < p; i++) {
            int count = 0;
            for (int k = 0; k < result.length; k++) {
                String s = Integer.toBinaryString(k + 1);
                int bit = ((Integer.parseInt(s)) / ((int) Math.pow(10, i))) % 10;
                if (bit == 1) {
                    if (result[k] == 1) {
                        count++;
                    }
                }
            }
            TableParity[i] = count % 2;
        }
        j = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 2) {
                result[i] = TableParity[j];
                j++;
            }
        }

        return result;
    }

    private String toString(int[] trama) {
        String result = "";
        for (int i = trama.length - 1; i >= 0; i--) {
            result += trama[i];
        }
        return result;
    }

    private String calculateHamming(int[] trama) {
        int data = trama.length;
        int p = calculate_p(data);
        int[] trama_array = calculateTable(trama, p);
        String transformacion = toString(trama_array);
        return transformacion;
    }

    public String obtenerMensaje() {
        EmisorPresentacion emisorPresentacion = new EmisorPresentacion();
        String trama = emisorPresentacion.convertToBinary();
        Object[] response = checkInput(trama);
        // boolean allGood = (boolean) response[0];
        int[] data_int = (int[]) response[1];
        return calculateHamming(data_int);

    }
}
