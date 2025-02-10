package clase7;

public class Actividad3 {

    public static int maximoBeneficio(int[] costos, int[] beneficios, int presupuesto) {
        int n = costos.length;
        int[][] dp = new int[n + 1][presupuesto + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= presupuesto; j++) {
                if (costos[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - costos[i - 1]] + beneficios[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][presupuesto];
    }

    public static void main(String[] args) {
        int[] costos = {10, 15, 20, 25};
        int[] beneficios = {100, 200, 150, 300};
        int presupuesto = 40;

        int maximoBeneficio = maximoBeneficio(costos, beneficios, presupuesto);
        System.out.println("El beneficio máximo es: " + maximoBeneficio);
    }
    //Complejidad de tiempo: O(n * W), donde n es el número de proyectos y W es el presupuesto disponible.
    //Complejidad de espacio: O(n * W) para la matriz dp.
}