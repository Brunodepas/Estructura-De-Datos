package colecciones.pilas;

public class PilaPrioridadArreglo<T extends Comparable<T>> implements Pila<T> {
    private Comparable<T>[] arreglo;
    private int elementos;
    public static final int CAPACIDAD_POR_DEFECTO = 100;


    public PilaPrioridadArreglo() {
        this(CAPACIDAD_POR_DEFECTO);
        elementos = 0;
    }
    public PilaPrioridadArreglo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad tiene que ser mayor a 0");
        }
        else {
            arreglo = new Comparable[capacidad];
            elementos = 0;
        }
    }

    public boolean isEmpty() {
        return (arreglo == null || elementos == 0);
    }

    public int elementos() {
        return elementos;
    }

    public T tope() {
        if (isEmpty()) {
            throw new IllegalStateException("Pila vacia");
        }
        else {
            return (T) arreglo[elementos() - 1];
        }
    }

    public T elemento(int posicion) {
        if (isEmpty()) {
            throw new IllegalStateException("Pila vacia");
        }
        if (posicion > elementos() || posicion <= 0) {
            throw new IllegalArgumentException("Posicion invalida");
        }
        else {
            return (T) arreglo[posicion - 1];
        }
    }

    public void vaciar() {
        elementos = 0;
    }

    public void apilar(T elem) {
        int posicion = 0;
        for (int i = 0; i < elementos() && (arreglo[i].compareTo(elem) <= 0); i++) {
            posicion++;
        }
        for (int i = elementos(); i > posicion; i--) {
            arreglo[i] = arreglo[i - 1];
        }
        arreglo[posicion] = elem;
        elementos++;
    }

    public void desapilar() {
        arreglo[elementos() - 1] = null;
        elementos--;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Pila)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Pila<?> objPila = (Pila<?>) obj;
        if (objPila.elementos() != this.elementos()) {
            return false;
        }
        for (int i = 1; i <= this.elementos(); i++) {
            if (!objPila.elemento(i).equals(this.elemento(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean repOK() {
        return true;
    }

    public String toString() {
        if (isEmpty()) {
            return "-->  <--";
        }
        String cadena = "--> ";
        for (int i = 1; i <= elementos(); i++) {
            cadena = cadena + elemento(i) + " , ";
        }
        cadena = cadena.substring(0, cadena.length() - 2);
        cadena += "<--";
        return cadena;
    }

    public static void main(String[] args) {
        Pila<Integer> pila1 = new PilaPrioridadArreglo<>(10);
        pila1.apilar(5);
        pila1.apilar(6);
        pila1.apilar(1);
        pila1.apilar(8);
        pila1.apilar(3);
        pila1.apilar(9);
        System.out.println(pila1.toString());
        pila1.desapilar();
        System.out.println(pila1.toString());
        System.out.println(pila1.tope());
        System.out.println(pila1.elementos());
        pila1.vaciar();
        pila1.apilar(8);
        pila1.apilar(10);
        pila1.apilar(1);
        pila1.apilar(20);
        System.out.println(pila1.toString());
        Pila<Integer> pila2 = new PilaPrioridadArreglo<>(8);
        pila2.apilar(8);
        pila2.apilar(10);
        pila2.apilar(1);
        pila2.apilar(20);
        System.out.println(pila1.equals(pila2));
    }
}
