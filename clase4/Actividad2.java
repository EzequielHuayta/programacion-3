package clase4;

public class Actividad2 {

    private static class Resultado {
        int mayor;
        int segundoMayor;

        public Resultado(int mayor, int segundoMayor) {
            this.mayor = mayor;
            this.segundoMayor = segundoMayor;
        }
    }

    public static void main(String[] args) {
        int[] numeros = {1, 5, 20, 4, 2, 100, 223, 221};
        Resultado resultado = mayoresDos(numeros);
        System.out.println(String.format("Los dos mayores son %s y %s", resultado.mayor, resultado.segundoMayor));
    }

    public static Resultado mayoresDos(int[] numeros) {
        return mayoresDos(numeros, 0, numeros.length - 1);
    }

    private static Resultado mayoresDos(int[] numeros, int inicio, int fin) {
        if (inicio == fin) {
            return new Resultado(numeros[inicio], Integer.MIN_VALUE);
        }

        if (fin == inicio + 1) {
            return new Resultado(
                    Math.max(numeros[inicio], numeros[fin]),
                    Math.min(numeros[inicio], numeros[fin])
            );
        }

        int medio = inicio + (fin - inicio) / 2;
        Resultado izq = mayoresDos(numeros, inicio, medio);
        Resultado der = mayoresDos(numeros, medio + 1, fin);
        return combinar(izq, der);
    }

    private static Resultado combinar(Resultado izq, Resultado der) {
        return new Resultado(
                Math.max(izq.mayor, der.mayor),
                izq.mayor > der.mayor
                        ? Math.max(izq.segundoMayor, der.mayor)
                        : Math.max(der.segundoMayor, izq.mayor)
        );
    }

}
