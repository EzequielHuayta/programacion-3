package clase5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Actividad1 {

    public static List<Integer> encontrarMinimoMonedas(List<Integer> monedas, int monto) {
        Collections.sort(monedas, Collections.reverseOrder());
        List<Integer> resultado = new ArrayList<>();

        for (int moneda : monedas) {
            while (monto >= moneda) {
                monto -= moneda;
                resultado.add(moneda);
            }
        }


        return resultado;
    }

    public static void main(String[] args) {
        List<Integer> monedas = new ArrayList<>();
        Collections.addAll(monedas, 10, 1, 5, 2, 10, 10, 5, 2, 5, 5, 5, 5, 5, 5, 10);
        int monto = 33;

        List<Integer> resultado = encontrarMinimoMonedas(monedas, monto);
        System.out.println("Monedas usadas para hacer " + monto + ": " + resultado);

    }
}