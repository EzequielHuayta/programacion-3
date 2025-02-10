package clase5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Comprobante {
    String tipo;
    int valor;

    public Comprobante(String tipo, int valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return tipo + ": " + valor;
    }
}

public class Actividad2 {

    public static List<Comprobante> encontrarMinimoComprobantes(List<Comprobante> comprobantes, int monto) {
        Collections.sort(comprobantes, (a, b) -> b.getValor() - a.getValor());
        List<Comprobante> resultado = new ArrayList<>();

        for (Comprobante comprobante : comprobantes) {
            while (monto >= comprobante.getValor()) {
                monto -= comprobante.getValor();
                resultado.add(comprobante);
            }
        }

        return resultado;

    }

    public static void main(String[] args) {
        List<Comprobante> comprobantes = new ArrayList<>();
        comprobantes.add(new Comprobante("Moneda", 10));
        comprobantes.add(new Comprobante("Cheque", 50));
        comprobantes.add(new Comprobante("Bono", 20));
        comprobantes.add(new Comprobante("Documento", 5));

        int monto = 75;

        List<Comprobante> resultado = encontrarMinimoComprobantes(comprobantes, monto);
        System.out.println("Comprobantes usados para hacer " + monto + ": " + resultado);

    }
}