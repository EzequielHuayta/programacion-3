public class Actividad1 {
    public static void main(String[] args) {
        int N = 4; // Tamaño del tablero
        int[] tablero = new int[N];
        resolverNReinas(tablero, 0, N);
    }

    public static void resolverNReinas(int[] tablero, int fila, int N) {
        if (fila == N) {
            imprimirSolucion(tablero, N);
            return;
        }

        for (int col = 0; col < N; col++) {
            if (esValida(tablero, fila, col)) {
                tablero[fila] = col;
                resolverNReinas(tablero, fila + 1, N);
                tablero[fila] = -1; // Backtracking
            }
        }
    }

    public static boolean esValida(int[] tablero, int fila, int col) {
        for (int i = 0; i < fila; i++) {
            if (tablero[i] == col ||
                    tablero[i] - i == col - fila ||
                    tablero[i] + i == col + fila) {
                return false;
            }
        }
        return true;
    }

    public static void imprimirSolucion(int[] tablero, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tablero[i] == j) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
