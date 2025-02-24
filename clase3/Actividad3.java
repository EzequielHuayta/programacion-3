public class Actividad3 {

    public static void main(String[] args) {
        int[] array = {15, 22, 13, 27, 12, 10, 20, 25};
        int inicio = 0;
        int fin = array.length - 1;

        printArray("Arreglo original:", array);
        clase3.QuickSort.quickSort(array, inicio, fin);
        printArray("\nArray ordenado:", array);
    }
   
    public static void printArray(String prevText, int[] arr) {
        System.out.println(prevText);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}