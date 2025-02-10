package clase4;

import java.util.Arrays;

public class Actividad2 {

    /*
    ALGORITMO encontrarDosMayores(lista, inicio, fin)
    SI inicio == fin ENTONCES
        RETURN (lista[inicio],Integer.MIN_VALUE)

    medio ← (inicio + fin) / 2

    (mayorIzq, segundoMayorIzq) ← encontrarDosMayores(lista, inicio, medio)
    (mayorDer, segundoMayorDer) ← encontrarDosMayores(lista, medio + 1, fin)

    mayor ← max(mayorIzq, mayorDer)
    segundoMayor ← max(min(mayorIzq, mayorDer), max(segundoMayorIzq, segundoMayorDer))

    RETURN (mayor, segundoMayor)

     */

    public static int[] encontrarDosMayores(int[] arreglo, int inicio, int fin) {
        // Caso base
        if (inicio == fin) {
            return new int[]{arreglo[inicio], Integer.MIN_VALUE};
        }

        int medio = (inicio + fin) / 2;
        int[] izq = encontrarDosMayores(arreglo, inicio, medio);
        int[] der = encontrarDosMayores(arreglo, medio + 1, fin);

        int mayor = Math.max(izq[0], der[0]);
        int segundoMayor = Math.max(Math.min(izq[0], der[0]), Math.max(izq[1], der[1]));

        //El primer valor del array devuelve el numero mayor y el segundo el 2do mayor

        return new int[]{mayor, segundoMayor};
    }

    public static void main(String[] args) {
        int[] arreglo = {8, 3, 1, 7, 0, 10, 2, 5};

        int[] resultado = encontrarDosMayores(arreglo, 0, arreglo.length - 1);

        System.out.println("Mayor: " + resultado[0]);
        System.out.println("Segundo mayor: " + resultado[1]);
    }
}
