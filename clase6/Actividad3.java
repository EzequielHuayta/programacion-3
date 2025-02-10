package clase6;

import java.util.*;

class GrafoAct3 {
    private int vertices;
    private List<List<Arista>> listaAdyacencia;

    public GrafoAct3(int vertices) {
        this.vertices = vertices;
        listaAdyacencia = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            listaAdyacencia.add(new ArrayList<>());
        }
    }

    public void agregarArista(int origen, int destino, int peso) {
        listaAdyacencia.get(origen).add(new Arista(destino, peso));
        listaAdyacencia.get(destino).add(new Arista(origen, peso));
    }

    public List<List<Arista>> getListaAdyacencia() {
        return listaAdyacencia;
    }
}

class Arista {
    int destino;
    int peso;

    public Arista(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

public class Actividad3 {
    public static void Actividad3(GrafoAct3 grafo) {
        int vertices = grafo.getListaAdyacencia().size();
        boolean[] visitado = new boolean[vertices];
        PriorityQueue<Arista> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.peso));
        List<Arista> mst = new ArrayList<>();
        int costoTotal = 0;

        // Empezar desde el vértice 0
        visitado[0] = true;
        pq.addAll(grafo.getListaAdyacencia().get(0));

        while (!pq.isEmpty()) {
            Arista arista = pq.poll();
            if (!visitado[arista.destino]) {
                visitado[arista.destino] = true;
                mst.add(arista);
                costoTotal += arista.peso;
                for (Arista siguienteArista : grafo.getListaAdyacencia().get(arista.destino)) {
                    if (!visitado[siguienteArista.destino]) {
                        pq.add(siguienteArista);
                    }
                }
            }
        }

        // Mostrar el MST y el costo total
        System.out.println("Aristas en el Árbol de Recubrimiento Mínimo:");
        for (Arista e : mst) {
            System.out.println("Arista: " + e.destino + " - Peso: " + e.peso);
        }
        System.out.println("Costo total: " + costoTotal);
    }

    public static void main(String[] args) {
        GrafoAct3 grafo = new GrafoAct3(5);
        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 3, 6);
        grafo.agregarArista(1, 2, 3);
        grafo.agregarArista(1, 3, 8);
        grafo.agregarArista(1, 4, 5);
        grafo.agregarArista(2, 4, 7);
        grafo.agregarArista(3, 4, 9);

        Actividad3(grafo);
    }
}