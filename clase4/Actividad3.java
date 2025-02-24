package clase4;

public class Actividad3 {

    private static class Resultado {
        Cliente mayor;
        Cliente segundoMayor;

        public Resultado(Cliente mayor, Cliente segundoMayor) {
            this.mayor = mayor;
            this.segundoMayor = segundoMayor;
        }
    }

    public static void main(String[] args) {
        Cliente[] clientes = {
                new Cliente(0, "Nico", 150),
                new Cliente(0, "Nico2", 130),
                new Cliente(0, "Nico3", 120),
                new Cliente(0, "Lucho", 10),
                new Cliente(0, "Lucho2", 20),
        };

        Resultado resultado = mayoresDos(clientes);
        System.out.println(resultado.mayor.getNombre() + " " + resultado.segundoMayor.getNombre());
    }

    public static Resultado mayoresDos(Cliente[] clientes) {
        return mayoresDos(clientes, 0, clientes.length - 1);
    }

    private static Resultado mayoresDos(Cliente[] clientes, int inicio, int fin) {
        if (inicio == fin) {
            return new Resultado(clientes[inicio], new Cliente(0, "", Integer.MIN_VALUE));
        }

        if (fin == inicio + 1) {
            if (clientes[inicio].getScore() > clientes[fin].getScore()) {
                return new Resultado(clientes[inicio], clientes[fin]);
            }
            return new Resultado(clientes[fin], clientes[inicio]);
        }

        int medio = inicio + (fin - inicio) / 2;
        Resultado izq = mayoresDos(clientes, inicio, medio);
        Resultado der = mayoresDos(clientes, medio + 1, fin);
        return combinar(izq, der);
    }

    private static Resultado combinar(Resultado izq, Resultado der) {
        Resultado resultado;
        if (izq.mayor.getScore() > der.mayor.getScore()) {
            resultado = new Resultado(izq.mayor, izq.segundoMayor.getScore() > der.mayor.getScore() ? izq.segundoMayor : der.mayor);
        } else {
            resultado = new Resultado(der.mayor, der.segundoMayor.getScore() > izq.mayor.getScore() ? der.segundoMayor : izq.mayor);
        }
        return resultado;
    }
}