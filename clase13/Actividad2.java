import java.util.*;

class Edge {
    String destino;
    int costo;

    public Edge(String destino, int costo) {
        this.destino = destino;
        this.costo = costo;
    }
}

class Node implements Comparable<Node> {
    String ciudad;
    int costo;
    List<String> ruta;

    public Node(String ciudad, int costo, List<String> ruta) {
        this.ciudad = ciudad;
        this.costo = costo;
        this.ruta = new ArrayList<>(ruta);
        this.ruta.add(ciudad);
    }

    @Override
    public int compareTo(Node otro) {
        return Integer.compare(this.costo, otro.costo);
    }
}

public class Actividad2 {
    private Map<String, List<Edge>> grafo;

    public Actividad2() {
        grafo = new HashMap<>();
    }

    public void agregarRuta(String origen, String destino, int costo) {
        grafo.putIfAbsent(origen, new ArrayList<>());
        grafo.get(origen).add(new Edge(destino, costo));
    }

    public void encontrarRutaMasBarata(String origen, String destino) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(origen, 0, new ArrayList<>()));

        Map<String, Integer> costoMinimo = new HashMap<>();
        costoMinimo.put(origen, 0);

        while (!pq.isEmpty()) {
            Node actual = pq.poll();

            if (actual.ciudad.equals(destino)) {
                System.out.println("Costo mínimo: " + actual.costo);
                System.out.println("Itinerario: " + String.join(" -> ", actual.ruta));
                return;
            }

            if (!grafo.containsKey(actual.ciudad)) continue;

            for (Edge arista : grafo.get(actual.ciudad)) {
                int nuevoCosto = actual.costo + arista.costo;

                if (!costoMinimo.containsKey(arista.destino) || nuevoCosto < costoMinimo.get(arista.destino)) {
                    costoMinimo.put(arista.destino, nuevoCosto);
                    pq.add(new Node(arista.destino, nuevoCosto, actual.ruta));
                }
            }
        }

        System.out.println("No hay ruta disponible entre " + origen + " y " + destino);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Actividad2 buscador = new Actividad2();

        // Agregar algunas rutas de ejemplo
        buscador.agregarRuta("Buenos Aires", "Santiago", 200);
        buscador.agregarRuta("Buenos Aires", "Lima", 400);
        buscador.agregarRuta("Santiago", "Lima", 150);
        buscador.agregarRuta("Lima", "México DF", 300);
        buscador.agregarRuta("Santiago", "Bogotá", 250);
        buscador.agregarRuta("Bogotá", "México DF", 200);
        buscador.agregarRuta("México DF", "Los Ángeles", 350);

        System.out.print("Ingrese la ciudad de origen: ");
        String origen = scanner.nextLine();
        System.out.print("Ingrese la ciudad de destino: ");
        String destino = scanner.nextLine();

        buscador.encontrarRutaMasBarata(origen, destino);
        scanner.close();
    }
}
