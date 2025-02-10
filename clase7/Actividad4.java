package clase7;

public class Actividad4 {

    public static int maximoGanancia(int[] costos, int[] ganancias, int presupuesto) {
        int n = costos.length;
        int[][] dp = new int[n + 1][presupuesto + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= presupuesto; j++) {
                if (costos[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - costos[i - 1]] + ganancias[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][presupuesto];
    }

    public static void main(String[] args) {
        int[] costos = {12, 20, 15, 25};
        int[] ganancias = {150, 200, 100, 300};
        int presupuesto = 35;

        int maximoGanancia = maximoGanancia(costos, ganancias, presupuesto);
        System.out.println("La ganancia máxima es: " + maximoGanancia);
    }
    //Complejidad de tiempo: O(n * W), donde n es el número de paquetes y W es el presupuesto disponible.
    //Complejidad de espacio: O(n * W) para la matriz dp.

}