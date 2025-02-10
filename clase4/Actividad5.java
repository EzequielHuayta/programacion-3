package clase4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
ALGORITHM encontrarMejoresTiempos(corredores, inicio, fin)
    IF inicio == fin THEN
        RETURN {corredores.categoria: (corredores[inicio].nombre, corredores[inicio].tiempo)}

    medio ← (inicio + fin) / 2

    mejoresIzq ← encontrarMejoresTiempos(corredores, inicio, medio)
    mejoresDer ← encontrarMejoresTiempos(corredores, medio + 1, fin)

    RETURN combinar(mejoresIzq, mejoresDer)

ALGORITHM combinar(mejoresIzq, mejoresDer)
    FOR cada categoria en mejoresIzq
        IF categoria en mejoresDer THEN
            IF mejoresIzq[categoria].tiempo < mejoresDer[categoria].tiempo THEN
                resultado[categoria] ← mejoresIzq[categoria]
            ELSE
                resultado[categoria] ← mejoresDer[categoria]
        ELSE
            resultado[categoria] ← mejoresIzq[categoria]

    FOR cada categoria en mejoresDer
        IF categoria no en resultado THEN
            resultado[categoria] ← mejoresDer[categoria]

    RETURN resultado
 */



class Corredor {
    String nombre;
    String categoria;
    double tiempo;

    public Corredor(String nombre, String categoria, double tiempo) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getTiempo() {
        return tiempo;
    }
}

public class Actividad5 {

    public static Map<String, Corredor> encontrarMejoresTiempos(List<Corredor> corredores, int inicio, int fin) {
        if (inicio == fin) {
            Map<String, Corredor> resultado = new HashMap<>();
            Corredor corredor = corredores.get(inicio);
            resultado.put(corredor.getCategoria(), corredor);
            return resultado;
        }

        int medio = (inicio + fin) / 2;
        Map<String, Corredor> mejoresIzq = encontrarMejoresTiempos(corredores, inicio, medio);
        Map<String, Corredor> mejoresDer = encontrarMejoresTiempos(corredores, medio + 1, fin);

        return combinar(mejoresIzq, mejoresDer);
    }

    private static Map<String, Corredor> combinar(Map<String, Corredor> mejoresIzq, Map<String, Corredor> mejoresDer) {
        Map<String, Corredor> resultado = new HashMap<>(mejoresIzq);

        for (Map.Entry<String, Corredor> entry : mejoresDer.entrySet()) {
            String categoria = entry.getKey();
            Corredor corredorDer = entry.getValue();

            if (resultado.containsKey(categoria)) {
                Corredor corredorIzq = resultado.get(categoria);
                if (corredorDer.getTiempo() < corredorIzq.getTiempo()) {
                    resultado.put(categoria, corredorDer);
                }
            } else {
                resultado.put(categoria, corredorDer);
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        List<Corredor> corredores = new ArrayList<>();
        corredores.add(new Corredor("Juan", "A", 12.5));
        corredores.add(new Corredor("Maria", "B", 11.2));
        corredores.add(new Corredor("Carlos", "A", 13.1));
        corredores.add(new Corredor("Ana", "B", 10.9));
        corredores.add(new Corredor("Luis", "C", 14.3));

        Map<String, Corredor> mejoresTiempos = encontrarMejoresTiempos(corredores, 0, corredores.size() - 1);

        for (Map.Entry<String, Corredor> entry : mejoresTiempos.entrySet()) {
            System.out.println("Categoría: " + entry.getKey() + ", Corredor: " + entry.getValue().getNombre() + ", Tiempo: " + entry.getValue().getTiempo());
        }
    }
}
