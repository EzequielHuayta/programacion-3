package clase11;

import java.util.ArrayList;
import java.util.List;

public class Actividad2 {
    private static final int tamaño = 4;
    private static List<int[]> soluciones = new ArrayList<>();
    private static int contador = 0;

    public static void main(String[] args) {
        int[] posiciones = new int[tamaño];
        colocarEscritorios(0, posiciones);

        imprimirSoluciones();
        System.out.println("Número total de configuraciones válidas: " + contador);
    }

    private static void colocarEscritorios(int fila, int[] posiciones) {
        if (fila == tamaño) {
            soluciones.add(posiciones.clone());
            contador++;
            return;
        }

        for (int columna = 0; columna < tamaño; columna++) {
            if (esValido(fila, columna, posiciones)) {
                posiciones[fila] = columna;
                colocarEscritorios(fila + 1, posiciones);
            }
        }
    }

    private static boolean esValido(int fila, int columna, int[] posiciones) {
        for (int i = 0; i < fila; i++) {
            if (posiciones[i] == columna) {
                return false;
            }
        }
        return true;
    }

    private static void imprimirSoluciones() {
        for (int[] sol : soluciones) {
            imprimirTablero(sol);
            System.out.println();
        }
    }

    private static void imprimirTablero(int[] posiciones) {
        for (int fila = 0; fila < tamaño; fila++) {
            for (int columna = 0; columna < tamaño; columna++) {
                if (posiciones[fila] == columna) {
                    System.out.print("E ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}