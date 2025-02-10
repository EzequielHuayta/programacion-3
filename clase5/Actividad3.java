package clase5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Elemento {
    String nombre;
    double peso;
    double valor;

    public Elemento(String nombre, double peso, double valor) {
        this.nombre = nombre;
        this.peso = peso;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public double getValor() {
        return valor;
    }

    public double getValorPorPeso() {
        return valor / peso;
    }

    @Override
    public String toString() {
        return nombre + ": " + peso + "kg, $" + valor;
    }
}

public class Actividad3 {

    public static List<Elemento> cargarCamion(List<Elemento> elementos, double capacidad) {
        Collections.sort(elementos, (a, b) -> Double.compare(b.getValorPorPeso(), a.getValorPorPeso()));
        List<Elemento> resultado = new ArrayList<>();
        double pesoTotal = 0;
        double valorTotal = 0;

        for (Elemento elemento : elementos) {
            if (pesoTotal + elemento.getPeso() <= capacidad) {
                resultado.add(elemento);
                pesoTotal += elemento.getPeso();
                valorTotal += elemento.getValor();
            } else {
                double fraccion = (capacidad - pesoTotal) / elemento.getPeso();
                resultado.add(new Elemento(elemento.getNombre() + " (fracción)", elemento.getPeso() * fraccion, elemento.getValor() * fraccion));
                valorTotal += elemento.getValor() * fraccion;
                break;
            }
        }

        System.out.println("Valor total: $" + valorTotal);
        return resultado;
    }

    public static void main(String[] args) {
        List<Elemento> elementos = new ArrayList<>();
        elementos.add(new Elemento("Elemento1", 10, 60));
        elementos.add(new Elemento("Elemento2", 20, 100));
        elementos.add(new Elemento("Elemento3", 30, 120));

        double capacidad = 50;
        List<Elemento> resultado = cargarCamion(elementos, capacidad);

        System.out.println("Elementos cargados:");
        for (Elemento elemento : resultado) {
            System.out.println(elemento);
        }
    }
}