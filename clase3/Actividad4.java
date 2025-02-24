public class Actividad4 {
    public static void main(String[] args) {
        int[] array = {15, 22, 13, 27, 12, 10, 20, 25};
        printArray("\nArray desordenado", array);
        clase3.MergeSort.mergeSort(array);
        printArray("\nArray ordenado", array);
    }

    public static void printArray(String prevText, int[] arr) {
        System.out.println(prevText);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
