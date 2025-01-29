import java.util.ArrayList;
import java.util.List;

class Cliente {
    int idCliente;
    String nombre;

    public Cliente(int idCliente, String nombre) {
        this.idCliente = idCliente;
        this.nombre = nombre;
    }
}

class Factura {
    int idFactura;
    int idCliente;
    double importe;

    public Factura(int idFactura, int idCliente, double importe) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.importe = importe;
    }
}

class Resultado {
    int idCliente;
    String nombre;
    double totalImporte;

    public Resultado(int idCliente, String nombre, double totalImporte) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.totalImporte = totalImporte;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " | Total: " + totalImporte;
    }
}

public class Actividad3_sinMap {
    public static void main(String[] args) {
        List<Cliente> clientes = List.of(
                new Cliente(1, "Juan"),
                new Cliente(2, "Maria"),
                new Cliente(3, "Pedro")
        );

        List<Factura> facturas = List.of(
                new Factura(101, 1, 200),
                new Factura(102, 2, 150),
                new Factura(103, 1, 100),
                new Factura(104, 3, 50),
                new Factura(105, 2, 75)
        );

        List<Resultado> resultados = new ArrayList<>();

        for (Cliente cliente : clientes) {
            double suma = 0;
            for (Factura factura : facturas) {
                if (factura.idCliente == cliente.idCliente) {
                    suma += factura.importe;
                }
            }
            resultados.add(new Resultado(cliente.idCliente, cliente.nombre, suma));
        }

        for (Resultado resultado : resultados) {
            System.out.println(resultado);
        }
    }
}
