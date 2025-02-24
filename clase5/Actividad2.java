package clase5;

import java.util.ArrayList;
import java.util.Arrays;

abstract class Comprobante {
    protected double valor;

    public abstract double getValor();
}

class Cheque extends Comprobante {
    private final double valor;

    Cheque(double valor) {
        this.valor = valor;
    }

    @Override
    public double getValor() {
        return this.valor;
    }
}

class Bono extends Comprobante {
    private final double valor;

    Bono(double valor) {
        this.valor = valor;
    }

    @Override
    public double getValor() {
        return this.valor;
    }
}


class Billete extends Comprobante {
    private final double valor;

    Billete(double valor) {
        this.valor = valor;
    }

    @Override
    public double getValor() {
        return this.valor;
    }
}

public class Actividad2 {
    public static void main(String[] args) {
        Comprobante[] comprobantes = {
                new Billete(50),
                new Billete(100),
                new Billete(10),
                new Bono(200),
                new Cheque(1000)
        };

        for (Comprobante comprobante : maxValor(comprobantes, 1355)) {
            System.out.println(comprobante.getValor());
        }
    }

    public static ArrayList<Comprobante> maxValor(Comprobante[] comprobantes, double montoAComprar) {
        Comprobante[] localComprobantes = Arrays.copyOf(comprobantes, comprobantes.length);

        // Ordenar de mayor a menor por el valor
        Arrays.sort(localComprobantes, (a, b) -> Double.compare(b.getValor(), a.getValor()));

        ArrayList<Comprobante> result = new ArrayList<>();

        for (int i = 0; i < localComprobantes.length && montoAComprar > 0; i++) {
            Comprobante comprobante = localComprobantes[i];
            result.add(comprobante);
            montoAComprar -= comprobante.getValor();
        }

        return result;
    }
}
