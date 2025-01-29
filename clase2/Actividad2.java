public class Actividad2 {
    public static void main(String[] args) {
        int[][] A = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] B = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        System.out.println("Matriz A:");
        printMatrix(A);
        System.out.println("Matriz B:");
        printMatrix(B);

        int[][] C = multiplyMatrices(A, B, 3);

        System.out.println("Resultado de A x B:");
        printMatrix(C);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " \t");
            }
            System.out.println();
        }
    }

    public static int[][] multiplyMatrices(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
