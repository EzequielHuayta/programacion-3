import java.math.BigInteger;

public class Actividad4 {

    // Factorial con long (para valores pequeños)
    public static long factorialLong(int n) {
        if (n < 0 || n > 20) return -1; // Se devuelve -1 para indicar demasiado grande
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // Factorial con BigInteger (para valores grandes)
    public static BigInteger factorialBigInteger(int n) {
        if (n < 0) return BigInteger.valueOf(-1);
        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }

    public static void main(String[] args) {
        int n = 20;

        // Factorial con long
        long resultadoLong = factorialLong(n);
        if (resultadoLong == -1) {
            System.out.println("Factorial de " + n + " con long: No se puede calcular (desbordamiento o entrada inválida).");
        } else {
            System.out.println("Factorial de " + n + " con long: " + resultadoLong);
        }

        // Factorial con BigInteger
        n = 100;
        BigInteger resultadoBigInt = factorialBigInteger(n);
        if (resultadoBigInt.equals(BigInteger.valueOf(-1))) {
            System.out.println("Factorial de " + n + " con BigInteger: Entrada inválida.");
        } else {
            System.out.println("Factorial de " + n + " con BigInteger: " + resultadoBigInt);
        }

        //Usar long cuando el número factorial es pequeño (≤ 20).
        //Usar BigInteger cuando se necesitan valores grandes asi evitamos el desbordamiento.

    }
}
