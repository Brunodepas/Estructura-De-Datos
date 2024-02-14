package colecciones.arraysorter;
import colecciones.arboles.ABB;
import colecciones.heaps.Minheap;

public class ArraySorter {
    public static <T extends Comparable<? super T>> void mostrarArreglo(T[] arreglo) {
        for (T elem : arreglo) {
            System.out.print(elem + " ");
        }
        System.out.print("\n");
    }
    public static <T extends Comparable<? super T>> void swap(T[] arreglo, int i, int j) {
        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    public static <T extends Comparable<? super T>> int indexOfLargest(T[] arreglo, int n) {
        int largest = 0;
        for (int i = 1; i < n; i++) {
            if (arreglo[i].compareTo(arreglo[largest]) > 0) {
                largest = i;
            }
        }
        return largest;
    }

    public static <T extends Comparable<? super T>> int particion(T[] arreglo, int p, int f) {
        //particion con pivote al inicio
        /*T pivot = arreglo[p];
        int i = p - 1;
        int j = f + 1;
        while (i < j) {
            do j--; while(arreglo[j].compareTo(pivot) > 0);
            do i++; while(arreglo[i].compareTo(pivot) < 0);
            if (i < j) {swap(arreglo, i, j);}
        }
        return j;*/
        
        //particion con pivote a la mitad
        int mitad = (p + f) / 2;
        T pivot = arreglo[mitad];
        int i = p;
        int j = f;
        while (i <= j) {
            while (arreglo[i].compareTo(pivot) < 0) {
                i++;
            }
            while (arreglo[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                swap(arreglo, i, j);
                i++;
                j--;
            }
        }
        return i;

        //particion con pivote al final
        /*T pivot = arreglo[f];
        int i = p - 1;
        for (int j = p; j < f; j++) {
            if (arreglo[j].compareTo(pivot) <= 0) {
                i++;
                swap(arreglo, i, j);
            }
        }
        swap(arreglo, i + 1, f);
        return i + 1;*/
    }

    public static <T extends Comparable<? super T>> void merge(T[] arreglo, T[] temp, int p, int m, int f) {
        for (int i = p; i <= f; i++) {
            temp[i] = arreglo[i];
        }
        int i = p;
        int j = m + 1;
        int k = p;

        while (i <= m && j <= f) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                arreglo[k] = temp[i];
                i++;
            }
            else {
                arreglo[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i <= m) {
            arreglo[k] = temp[i];
            i++;
            k++;
        }
    }




    public static <T extends Comparable<? super T>> void bubbleSort(T[] arreglo) {
        boolean sorted = false;
        int n = arreglo.length;

        for (int v = 1; v < n && !sorted; v++) {
            sorted = true;
            for (int p = 0; p < n - v; p++) {
                int sigP = p + 1;
                if (arreglo[p].compareTo(arreglo[sigP]) > 0) {
                    swap(arreglo, p, sigP);
                    sorted = false;
                }
            }
        }
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] arreglo) {
        int n = arreglo.length;
        for (int unsorted = 1; unsorted < n; unsorted++) {
            T nextItem = arreglo[unsorted];
            int loc = unsorted;
            while ((loc > 0) && (arreglo[loc - 1].compareTo(nextItem) > 0)) {
                arreglo[loc] = arreglo[loc - 1];
                loc--;
            }
            arreglo[loc] = nextItem;
        }
    }

    public static <T extends Comparable<? super T>> void selectionSort(T[] arreglo) {
        int n = arreglo.length;
        for (int i = n - 1; i >= 1; i--) {
            int largest = indexOfLargest(arreglo, i + 1);
            swap(arreglo, i, largest);
        }
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] arreglo) {
        quickSort(arreglo, 0, arreglo.length - 1);
    }
    public static <T extends Comparable<? super T>> void quickSort(T[] arreglo, int inicio, int fin) {
        if (inicio < fin) {
            int p = particion(arreglo, inicio, fin);

            //quicksort con pivote al inicio
            /*quickSort(arreglo, inicio, p);
            quickSort(arreglo, p+1, fin);*/
            
            //quicksort con pivote a la mitad
            quickSort(arreglo, inicio, p-1);
            quickSort(arreglo, p, fin);
            
            //quicksort con pivote al final
            /*quickSort(arreglo, inicio, p - 1);
            quickSort(arreglo, p + 1, fin);*/
        }
    }
    
    public static <T extends Comparable<? super T>> void mergeSort(T[] arreglo) {
        if (arreglo.length > 1) {
            T[] temp = (T[]) new Comparable[arreglo.length];
            mergeSort(arreglo, temp, 0, arreglo.length - 1);
        }
    }
    public static <T extends Comparable<? super T>> void mergeSort(T[] arreglo, T[] temp, int p, int f) {
        if (p < f) {
            int m = (p + f) / 2;
            mergeSort(arreglo, temp, p, m);
            mergeSort(arreglo, temp, m + 1, f);
            merge(arreglo, temp, p, m, f);
        }
    }

    public static <T extends Comparable<? super T>> void countingSort(Integer[] arreglo) {
        int n = arreglo.length;
        int k = arreglo[indexOfLargest(arreglo, n)];
        countingSort(arreglo, n, k);
    }
    public static <T extends Comparable<? super T>> void countingSort(Integer[] arreglo, int n, int k) {
        Integer[] b = new Integer[n];
        Integer[] c = new Integer[k + 1];

        for (int i = 0; i < c.length; i++) {
            c[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            c[arreglo[i]]++;
        }

        for (int i = 1; i < c.length; i++) {
            c[i] += c[i-1];
        }

        for (int i = n-1; i >= 0; i--) {
            int elem = arreglo[i];
            b[c[elem]-1] = elem;
            c[elem]--;
        }

        for (int i = 0; i < n; i++) {
            arreglo[i] = b[i];
        }
    }

    public static <T extends Comparable<T>> void treeSort(T[] arreglo) {
        ABB<T> arbol = new ABB<>();
        for (int i = 0; i < arreglo.length; i++) {
            arbol.insertar(arreglo[i]);
        }
        for (int i = 0; i < arreglo.length; i++) {
            T elem = arbol.menorValor();
            arbol.eliminar(elem);
            arreglo[i] = elem;
        }
    }

    public static <T extends Comparable<T>> void heapSort(T[] arreglo) {
        Minheap<T> heap = new Minheap<>();
        for (int i = 0; i < arreglo.length; i++) {
            heap.insertar(arreglo[i]);
        }
        for (int i = 0; i < arreglo.length; i++) {
            T elem = heap.remover();
            arreglo[i] = elem;
        }
    }

    public static void main(String[] args) {
        System.out.println("Arreglo a ordenar:");
        Integer[] a1 = {5,10,2,9,4,1};
        Integer[] a2 = {5,10,2,9,4,1};
        Integer[] a3 = {5,10,2,9,4,1};
        Integer[] a4 = {5,10,2,9,4,1};
        Integer[] a5 = {5,10,2,9,4,1};
        Integer[] a6 = {5,10,2,9,4,1};
        Integer[] a7 = {5,10,2,9,4,1};
        Integer[] a8 = {5,10,2,9,4,1};
        mostrarArreglo(a1);
        System.out.println("Arreglo ordenado con BubbleSort:");
        bubbleSort(a1);
        mostrarArreglo(a1);
        System.out.println("Arreglo ordenado con InsertionSort:");
        insertionSort(a2);
        mostrarArreglo(a2);
        System.out.println("Arreglo ordenado con SelectionSort:");
        selectionSort(a3);
        mostrarArreglo(a3);
        System.out.println("Arreglo ordenado con QuickSort:");
        quickSort(a4);
        mostrarArreglo(a4);
        System.out.println("Arreglo ordenado con MergeSort:");
        mergeSort(a5);
        mostrarArreglo(a5);
        System.out.println("Arreglo ordenado con CountingSort:");
        countingSort(a6);
        mostrarArreglo(a6);
        System.out.println("Arreglo ordenado con TreeSort:");
        treeSort(a7);
        mostrarArreglo(a7);
        System.out.println("Arreglo ordenado con HeapSort:");
        heapSort(a8);
        mostrarArreglo(a8);
    }
}
