import java.util.ArrayList;

public class Actividad1 {
    public static void main(String[] args) {

        int cont = 0;
        double sum = 0;

        ArrayList<Integer>[] mat = new ArrayList[] {
                new ArrayList<>(java.util.Arrays.asList(4, 5, 6)),
                new ArrayList<>(java.util.Arrays.asList(7, 8, 9)),
                new ArrayList<>(java.util.Arrays.asList(5, 6, 7))
        };
        for (int i = 0; i < mat.length ; i++) {
            for ( int j = 0; j < mat[i].size(); j++) {
                cont++;
                sum+= mat[i].get(j);
            }
        }
        System.out.println("Promedio: " + sum/cont);
        System.out.println("Cantidad de instrucciones: " + cont);
    }

    //Complejijdad asintotica n cuadrado
}