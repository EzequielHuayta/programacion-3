package clase4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Actividad5 {

    private static class Corredor {
        String nombre;
        double tiempo;
        String categoria;

        public Corredor(String nombre, double tiempo, String categoria) {
            this.nombre = nombre;
            this.tiempo = tiempo;
            this.categoria = categoria;
        }
    }

    public static void main(String[] args) {
        Corredor[] corredores = {
                new Corredor("Nico", 33.01, "100m"),
                new Corredor("Nico2", 34.31, "100m"),
                new Corredor("Nico3", 34.12, "150m"),
                new Corredor("Lucho", 35.89, "150m")
        };

        TreeMap<String, Corredor> ganadores = mejoresCorredores(corredores);

        for (String key : ganadores.keySet()) {
            System.out.println(String.format("El mejor corredor de la categoria %s fue %s", key, ganadores.get(key).nombre));
        }

    }

    public static TreeMap<String, Corredor> mejoresCorredores(Corredor[] corredores) {
        TreeMap<String, Corredor> corredorPorCategoria = new TreeMap<>();
        for (Corredor corredor : corredores) {
            if (!corredorPorCategoria.containsKey(corredor.categoria)) {
                corredorPorCategoria.put(corredor.categoria, null);
            }
        }

        for (String key : corredorPorCategoria.keySet()) {
            Corredor[] corredoresCategoria = Arrays.stream(corredores)
                    .filter(corredor -> corredor.categoria.equals(key))
                    .toArray(Corredor[]::new);
            corredorPorCategoria.put(key, mejorCorredor(corredoresCategoria, 0, corredoresCategoria.length - 1));
        }

        return corredorPorCategoria;
    }

    private static Corredor mejorCorredor(Corredor[] corredores, int inicio, int fin) {
        if (inicio == fin) return corredores[inicio];
        int medio = inicio + (fin - inicio) / 2;

        Corredor corredorIzquierda = mejorCorredor(corredores, inicio, medio);
        Corredor corredorDerecha = mejorCorredor(corredores, medio + 1, fin);
        return corredorDerecha.tiempo < corredorIzquierda.tiempo
                ? corredorDerecha
                : corredorIzquierda;
    }
}
