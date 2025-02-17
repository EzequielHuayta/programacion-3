package clase11;

import java.util.ArrayList;
import java.util.List;

public class Actividad3 {
    private static final int TAMAÑO = 4;
    private static List<int[]> soluciones = new ArrayList<>();
    private static int contadorSoluciones = 0;

    public static void main(String[] args) {
        int[] computadoras = new int[TAMAÑO];
        int[] impresoras = new int[TAMAÑO];
        colocarEquipos(0, computadoras, impresoras);

        imprimirSoluciones();
        System.out.println("Total de soluciones encontradas: " + contadorSoluciones);
    }

    private static void colocarEquipos(int fila, int[] computadoras, int[] impresoras) {
        if (fila == TAMAÑO) {
            soluciones.add(computadoras.clone());
            soluciones.add(impresoras.clone());
            contadorSoluciones++;
            return;
        }

        for (int colComputadora = 0; colComputadora < TAMAÑO; colComputadora++) {
            if (esValido(fila, colComputadora, computadoras)) {
                computadoras[fila] = colComputadora;

                for (int colImpresora = 0; colImpresora < TAMAÑO; colImpresora++) {
                    if (esValido(fila, colImpresora, impresoras) && colComputadora != colImpresora) {
                        impresoras[fila] = colImpresora;
                        colocarEquipos(fila + 1, computadoras, impresoras);
                    }
                }
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
        for (int i = 0; i < soluciones.size(); i += 2) {
            imprimirTablero(soluciones.get(i), soluciones.get(i + 1));
            System.out.println();
        }
    }

    private static void imprimirTablero(int[] computadoras, int[] impresoras) {
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                if (computadoras[fila] == columna) {
                    System.out.print("C "); // C representa una computadora
                } else if (impresoras[fila] == columna) {
                    System.out.print("I "); // I representa una impresora
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}