package clase4;

import java.util.ArrayList;
import java.util.List;
/*

ALGORITHM encontrarDosClientesConMaxScoring(listaClientes, inicio, fin)
    IF inicio == fin THEN
        RETURN (listaClientes[inicio], null)

    medio ← (inicio + fin) / 2

    (clienteMayorIzq, segundoMayorIzq) ← encontrarDosClientesConMaxScoring(listaClientes, inicio, medio)
    (clienteMayorDer, segundoMayorDer) ← encontrarDosClientesConMaxScoring(listaClientes, medio + 1, fin)

    IF clienteMayorIzq.scoring >= clienteMayorDer.scoring THEN
        mayor ← clienteMayorIzq
        segundoMayor ← max(segundoMayorIzq, clienteMayorDer)
    ELSE
        mayor ← clienteMayorDer
        segundoMayor ← max(clienteMayorIzq, segundoMayorDer)

    RETURN (mayor, segundoMayor)
 */

class DosClientes {
    Cliente mayor;
    Cliente segundoMayor;

    public DosClientes(Cliente mayor, Cliente segundoMayor) {
        this.mayor = mayor;
        this.segundoMayor = segundoMayor;
    }
}

public class Actividad3 {

    public static DosClientes encontrarDosClientesConMaxScoring(List<Cliente> clientes, int inicio, int fin) {
        if (inicio == fin) {
            return new DosClientes(clientes.get(inicio), null);
        }

        int medio = (inicio + fin) / 2;
        DosClientes izq = encontrarDosClientesConMaxScoring(clientes, inicio, medio);
        DosClientes der = encontrarDosClientesConMaxScoring(clientes, medio + 1, fin);

        Cliente mayor;
        Cliente segundoMayor;

        if (izq.mayor.getScoring() >= der.mayor.getScoring()) {
            mayor = izq.mayor;
            segundoMayor = (izq.segundoMayor != null && izq.segundoMayor.getScoring() >= der.mayor.getScoring()) ? izq.segundoMayor : der.mayor;
        } else {
            mayor = der.mayor;
            segundoMayor = (der.segundoMayor != null && der.segundoMayor.getScoring() >= izq.mayor.getScoring()) ? der.segundoMayor : izq.mayor;
        }

        return new DosClientes(mayor, segundoMayor);
    }

    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "Juan", 85));
        clientes.add(new Cliente(2, "Maria", 92));
        clientes.add(new Cliente(3, "Carlos", 76));

        DosClientes resultado = encontrarDosClientesConMaxScoring(clientes, 0, clientes.size() - 1);
        System.out.println("Cliente con mayor scoring: " + resultado.mayor.getNombre() + " con un scoring de: " + resultado.mayor.getScoring());
        System.out.println("Cliente con segundo mayor scoring: " + resultado.segundoMayor.getNombre() + " con un scoring de: " + resultado.segundoMayor.getScoring());
    }
}