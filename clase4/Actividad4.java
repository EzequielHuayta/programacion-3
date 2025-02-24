package clase4;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Actividad4 {
    public static void main(String[] args) {
        int[] numeros = {1, 5, 20, 999, 11, 25, 10, 200000, 59, 3, 2, 1, 6, 73, 234};
        int[] nMasGrandes = obtenerMasGrandes(numeros, 5);
        for (int i = 0; i < nMasGrandes.length; i++) {
            System.out.println(nMasGrandes[i]);
        }
    }

    private static int[] obtenerMasGrandes(int[] numeros, int cant) {
        int[] result = Arrays.copyOf(numeros, numeros.length);
        QuickSort.quickSort(result, 0, result.length - 1); // nlogn

        return Arrays.copyOfRange(result, result.length - cant, result.length);
        // O(n log n)
    }
}
