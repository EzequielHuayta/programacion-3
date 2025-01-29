package clase4;

import java.util.ArrayList;
import java.util.List;

class Cliente {
    public double getScoring() {
        return scoring;
    }

    int id;

    public String getNombre() {
        return nombre;
    }

    String nombre;
    double scoring;

    public Cliente(int id, String nombre, double scoring) {
        this.id = id;
        this.nombre = nombre;
        this.scoring = scoring;
    }

}

public class Actividad1 {

    // Método Divide y Vencerás para encontrar el cliente con el scoring máximo
    public static Cliente encontrarClienteConMaxScoring(List<Cliente> clientes, int inicio, int fin) {
        // Caso base 1 solo cliente
        if (inicio == fin) {
            return clientes.get(inicio);
        }

        int medio = (inicio + fin) / 2;
        Cliente clienteIzq = encontrarClienteConMaxScoring(clientes, inicio, medio);
        Cliente clienteDer = encontrarClienteConMaxScoring(clientes, medio + 1, fin);

        return (clienteIzq.scoring >= clienteDer.scoring) ? clienteIzq : clienteDer;
    }

    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "Juan", 85));
        clientes.add(new Cliente(2, "Maria", 92));
        clientes.add(new Cliente(3, "Carlos", 76));

        Cliente maxCliente = encontrarClienteConMaxScoring(clientes, 0, clientes.size() - 1);
        System.out.println("Cliente con mayor scoring: " + maxCliente.getNombre() + " con un scoring de: "+ maxCliente.getScoring());
    }
}
