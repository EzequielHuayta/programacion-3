package clase6;

import java.util.*;

public class Actividad4 {

    public static void dijkstra(GrafoAct3 grafo, int verticeInicial) {
        int vertices = grafo.getListaAdyacencia().size();
        int[] distancias = new int[vertices];
        boolean[] visitado = new boolean[vertices];
        PriorityQueue<Arista> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.peso));

        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[verticeInicial] = 0;
        pq.add(new Arista(verticeInicial, 0));

        while (!pq.isEmpty()) {
            Arista arista = pq.poll();
            int verticeActual = arista.destino;

            if (!visitado[verticeActual]) {
                visitado[verticeActual] = true;

                for (Arista vecino : grafo.getListaAdyacencia().get(verticeActual)) {
                    int nuevaDistancia = distancias[verticeActual] + vecino.peso;

                    if (nuevaDistancia < distancias[vecino.destino]) {
                        distancias[vecino.destino] = nuevaDistancia;
                        pq.add(new Arista(vecino.destino, nuevaDistancia));
                    }
                }
            }
        }

        // Mostrar el tiempo mínimo de entrega desde el centro de distribución principal hasta cada ciudad
        System.out.println("Tiempos mínimos de entrega desde el centro de distribución principal:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Hasta la ciudad " + i + ": " + distancias[i] + " minutos");
        }
    }

    public static void main(String[] args) {
        GrafoAct3 grafo = new GrafoAct3(5);
        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 2, 3);
        grafo.agregarArista(1, 2, 1);
        grafo.agregarArista(1, 3, 2);
        grafo.agregarArista(2, 3, 8);
        grafo.agregarArista(2, 4, 2);
        grafo.agregarArista(3, 4, 7);

        dijkstra(grafo, 0);
    }
}