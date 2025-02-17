package clase11;

public class Actividad1 {

    private static final int N = 4;
    private static int configuracionesValidas = 0;

    // Función para imprimir el tablero
    private static void imprimirTablero(int[][] tablero) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Función para verificar si es seguro colocar una reina en tablero[fila][columna]
    private static boolean esSeguro(int[][] tablero, int fila, int columna) {
        // Verificar esta fila en el lado izquierdo
        for (int i = 0; i < columna; i++) {
            if (tablero[fila][i] == 1) {
                return false;
            }
        }

        // Verificar la diagonal superior en el lado izquierdo
        for (int i = fila, j = columna; i >= 0 && j >= 0; i--, j--) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }

        // Verificar la diagonal inferior en el lado izquierdo
        for (int i = fila, j = columna; i < N && j >= 0; i++, j--) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }

        // Verificar esta columna en el lado superior
        for (int i = 0; i < fila; i++) {
            if (tablero[i][columna] == 1) {
                return false;
            }
        }

        return true;
    }

    // Función para resolver el problema usando backtracking
    private static void resolver(int[][] tablero, int reinasColocadas) {
        // Si se han colocado dos reinas, imprimir el tablero y contar la configuración válida
        if (reinasColocadas == 2) {
            imprimirTablero(tablero);
            configuracionesValidas++;
            return;
        }

        // Intentar colocar la reina en todas las posiciones del tablero
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tablero[i][j] == 0 && esSeguro(tablero, i, j)) {
                    // Colocar esta reina en tablero[i][j]
                    tablero[i][j] = 1;

                    // Recurrir para colocar la siguiente reina
                    resolver(tablero, reinasColocadas + 1);

                    // Si colocar la reina en tablero[i][j] no lleva a una solución, hacer backtracking
                    tablero[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] tablero = new int[N][N];
        resolver(tablero, 0);
        System.out.println("Número total de configuraciones válidas: " + configuracionesValidas);
    }
}