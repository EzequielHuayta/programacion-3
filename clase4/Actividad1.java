package clase4;

public class Actividad1 {
    public static void main(String[] args) {
        Cliente[] clientes = {
                new Cliente(0, "Nico", 150),
                new Cliente(0, "Nico2", 130),
                new Cliente(0, "Nico3", 120),
                new Cliente(0, "Lucho", 10),
                new Cliente(0, "Lucho2", 20),
        };

        System.out.println(mejorCliente(clientes).getNombre());
    }

    public static Cliente mejorCliente(Cliente[] clientes) {
        return mejorCliente(clientes, 0, clientes.length - 1);
    }

    private static Cliente mejorCliente(Cliente[] clientes, int inicio, int fin) {
        if (inicio == fin) return clientes[inicio];
        int medio = inicio + (fin - inicio) / 2;

        Cliente clienteIzquierda = mejorCliente(clientes, inicio, medio);
        Cliente clienteDerecha = mejorCliente(clientes, medio + 1, fin);
        return clienteDerecha.getScore() > clienteIzquierda.getScore()
                ? clienteDerecha
                : clienteIzquierda;
    }
}
