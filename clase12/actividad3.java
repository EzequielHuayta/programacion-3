package Clase12;

import java.util.*;

class Almacen {
    int id;
    String nombre;

    public Almacen(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() { // Sobrescribir toString para una impresión más clara
        return "Almacen{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    // Importante:  Implementar equals() y hashCode() para usar Almacen como clave en HashMap
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Almacen almacen = (Almacen) o;
        return id == almacen.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Grafo {
    private Map<Almacen, List<Almacen>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    public void agregarAlmacen(Almacen almacen) {
        listaAdyacencia.putIfAbsent(almacen, new ArrayList<>()); // Usar putIfAbsent
    }


    public void conectarAlmacenes(Almacen a1, Almacen a2) {
        // Verificar si los almacenes existen en el grafo.
        if (!listaAdyacencia.containsKey(a1)) {
            System.out.println("Error: El almacén " + a1 + " no existe.");
            return;
        }
        if (!listaAdyacencia.containsKey(a2)) {
            System.out.println("Error: El almacén " + a2 + " no existe.");
            return;
        }

        // Grafo no dirigido: agregar la arista en ambas direcciones.
        listaAdyacencia.get(a1).add(a2);
        listaAdyacencia.get(a2).add(a1);
    }

    public void dfs(Almacen inicio) {
        if (!listaAdyacencia.containsKey(inicio)) {
            System.out.println("Error: El almacén de inicio no existe en el grafo.");
            return;
        }

        Set<Almacen> visitados = new HashSet<>(); // Usar un Set para evitar visitar el mismo nodo dos veces.
        dfsRecursivo(inicio, visitados);
        System.out.println(); // Nueva línea después del recorrido
    }

    private void dfsRecursivo(Almacen actual, Set<Almacen> visitados) {
        visitados.add(actual);
        System.out.print(actual.nombre + " -> ");

        List<Almacen> vecinos = listaAdyacencia.get(actual);
        if (vecinos != null) {  // Verificar que la lista de vecinos no sea nula.
            for (Almacen vecino : vecinos) {
                if (!visitados.contains(vecino)) {
                    dfsRecursivo(vecino, visitados);
                }
            }
        }
    }


    public void bfs(Almacen inicio) {
        if (!listaAdyacencia.containsKey(inicio)) {
            System.out.println("Error: El almacén de inicio no existe en el grafo.");
            return;
        }

        Set<Almacen> visitados = new HashSet<>();
        Queue<Almacen> cola = new LinkedList<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            Almacen actual = cola.poll();
            System.out.print(actual.nombre + " -> ");

            List<Almacen> vecinos = listaAdyacencia.get(actual);
            if (vecinos != null) { // Verificar que la lista de vecinos no sea nula
                for (Almacen vecino : vecinos) {
                    if (!visitados.contains(vecino)) {
                        cola.add(vecino);
                        visitados.add(vecino);
                    }
                }
            }
        }
        System.out.println(); // Nueva línea después del recorrido
    }

    // Método para encontrar un almacen por su ID
    public Almacen encontrarAlmacenPorId(int id) {
        for (Almacen almacen : listaAdyacencia.keySet()) {
            if (almacen.id == id) {
                return almacen;
            }
        }
        return null; // Devuelve null si no se encuentra el almacen
    }
}

public class actividad3 {

    public static void main(String[] args) {
        Grafo redAlmacenes = new Grafo();

        // Agregar almacenes
        redAlmacenes.agregarAlmacen(new Almacen(1, "Almacen Central"));
        redAlmacenes.agregarAlmacen(new Almacen(2, "Almacen Norte"));
        redAlmacenes.agregarAlmacen(new Almacen(3, "Almacen Sur"));
        redAlmacenes.agregarAlmacen(new Almacen(4, "Almacen Este"));
        redAlmacenes.agregarAlmacen(new Almacen(5, "Almacen Oeste"));

        // Conectar almacenes
        // Obtener las instancias de Almacen
        Almacen almacenCentral = redAlmacenes.encontrarAlmacenPorId(1);
        Almacen almacenNorte = redAlmacenes.encontrarAlmacenPorId(2);
        Almacen almacenSur = redAlmacenes.encontrarAlmacenPorId(3);
        Almacen almacenEste = redAlmacenes.encontrarAlmacenPorId(4);
        Almacen almacenOeste = redAlmacenes.encontrarAlmacenPorId(5);

        //Si algun almacen no existe, salir
        if (almacenCentral == null || almacenNorte == null || almacenSur == null || almacenEste == null || almacenOeste == null) {
            System.out.println("Error: No se pudieron encontrar todos los almacenes.");
            return;
        }

        redAlmacenes.conectarAlmacenes(almacenCentral, almacenNorte);
        redAlmacenes.conectarAlmacenes(almacenCentral, almacenSur);
        redAlmacenes.conectarAlmacenes(almacenNorte, almacenEste);
        redAlmacenes.conectarAlmacenes(almacenSur, almacenOeste);
        redAlmacenes.conectarAlmacenes(almacenEste, almacenOeste);


        // Realizar recorridos DFS y BFS
        System.out.println("Recorrido DFS desde Almacen Central:");
        redAlmacenes.dfs(almacenCentral);

        System.out.println("Recorrido BFS desde Almacen Central:");
        redAlmacenes.bfs(almacenCentral);

        // Imprimir un almacen
        System.out.println("\n" + almacenCentral);
    }
}