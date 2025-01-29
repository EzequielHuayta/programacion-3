import java.util.*;

public class Actividad3_Map {
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

        Map<Integer, Double> sumaImportes = new HashMap<>();

        for (Factura factura : facturas) {
            if (sumaImportes.containsKey(factura.idCliente)) {
                sumaImportes.put(factura.idCliente, sumaImportes.get(factura.idCliente) + factura.importe);
            } else {
                sumaImportes.put(factura.idCliente, factura.importe);
            }
        }

        List<Resultado> resultados = new ArrayList<>();
        for (Cliente cliente : clientes) {
            double total = 0;
            if (sumaImportes.containsKey(cliente.idCliente)) {
                total = sumaImportes.get(cliente.idCliente);
            }
            resultados.add(new Resultado(cliente.idCliente, cliente.nombre, total));
        }

        for (Resultado resultado : resultados) {
            System.out.println(resultado);
        }
    }
}
