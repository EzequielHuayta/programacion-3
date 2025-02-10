package clase4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
ALGORITHM encontrarNMayores(lista, inicio, fin, n)
    IF inicio == fin THEN
        RETURN lista[inicio]

    medio ← (inicio + fin) / 2

    mayoresIzq ← encontrarNMayores(lista, inicio, medio, n)
    mayoresDer ← encontrarNMayores(lista, medio + 1, fin, n)

    RETURN combinar(mayoresIzq, mayoresDer, n)

ALGORITHM combinar(lista1, lista2, n)
    combinarLista ← merge(lista1, lista2)
    RETURN topN(combinarLista, n)
 */


public class Actividad4 {

    public static List<Integer> encontrarNMayores(List<Integer> lista, int inicio, int fin, int n) {
        if (inicio == fin) {
            List<Integer> result = new ArrayList<>();
            result.add(lista.get(inicio));
            return result;
        }

        int medio = (inicio + fin) / 2;
        List<Integer> mayoresIzq = encontrarNMayores(lista, inicio, medio, n);
        List<Integer> mayoresDer = encontrarNMayores(lista, medio + 1, fin, n);

        return combinar(mayoresIzq, mayoresDer, n);
    }

    private static List<Integer> combinar(List<Integer> lista1, List<Integer> lista2, int n) {
        List<Integer> combinarLista = new ArrayList<>(lista1);
        combinarLista.addAll(lista2);
        Collections.sort(combinarLista, Collections.reverseOrder());
        return combinarLista.subList(0, Math.min(n, combinarLista.size()));
    }

    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();
        Collections.addAll(lista, 8, 3, 1, 7, 0, 10, 2, 5);

        int n = 3;
        List<Integer> resultado = encontrarNMayores(lista, 0, lista.size() - 1, n);

        System.out.println("Los " + n + " elementos más grandes son: " + resultado);
    }
}