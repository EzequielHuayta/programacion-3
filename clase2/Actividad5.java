public class Actividad5 {

    public static int sumaRecursiva(int n) {
        if (n <= 0) return 0;
        return n + sumaRecursiva(n - 1);
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("La suma de los primeros " + n + " numeros es: " + sumaRecursiva(n));
    }
}
