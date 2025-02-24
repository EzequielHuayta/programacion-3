package clase5;


import java.util.Arrays;

public class MochilaFraccional {

    // Clase para representar un objeto con su valor y peso
    static class Item {
        double value, weight, ratio;

        Item(double value, double weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = value / weight;
        }
    }

    public static double fractionalKnapsack(int W, Item[] items) {
        // Ordenar los objetos por la relación valor/peso en orden descendente
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double maxValue = 0.0;

        Item item;
        for (int i = 0; i < items.length && W > 0; i++) {
            item = items[i];
            maxValue += item.value * item.weight <= W ? item.value : ((double) W / item.weight) ;
            W -= item.weight;
        }

        return maxValue;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(30, 10),
            new Item(50, 20),
            new Item(60, 30)
        };

        int W = 50; // Capacidad de la mochila

        System.out.println("Valor máximo obtenido = " + fractionalKnapsack(W, items));
    }
}
