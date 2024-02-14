package colecciones.colas;

public class ColaArreglo<T> implements Cola<T> {
    private T[] arreglo;
    private int elementos;
    public static final int CAPACIDAD_POR_DEFECTO = 100;


    public ColaArreglo() {
        this(CAPACIDAD_POR_DEFECTO);
        elementos = 0;
    }
    public ColaArreglo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad tiene que ser mayor a 0");
        }
        else {
            arreglo = (T[]) new Object[capacidad];
            elementos = 0;
        }
    }

    public boolean isEmpty() {
        return (arreglo == null || elementos == 0);
    }

    public int elementos() {
        return elementos;
    }

    public T primero() {
        if (isEmpty()) {
            throw new IllegalStateException("Cola vacia");
        }
        else {
            return arreglo[elementos - 1];
        }
    }

    public T elemento(int posicion) {
        if (posicion > elementos() || posicion <= 0) {
            throw new IllegalArgumentException("posicion invalida");
        }
        else {
            return arreglo[posicion - 1];
        }
    }

    public void vaciar() {
        elementos = 0;
    }

    public void encolar(T elem) {
        for (int i = elementos(); i > 0; i--) {
            arreglo[i] = arreglo[i - 1];
        }
        arreglo[0] = elem;
        elementos++;
    }

    public void desencolar() {
        if (isEmpty()) {
            throw new IllegalStateException("Cola vacia");
        }
        arreglo[elementos() - 1] = null;
        elementos--;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Cola)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Cola<?> objCola = (Cola<?>) obj;
        if (objCola.elementos() != this.elementos()) {
            return false;
        }
        for (int i = 1; i <= this.elementos(); i++) {
            if (!objCola.elemento(i).equals(this.elemento(i))) {
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
            return "-->  -->";
        }
        String cadena = "--> ";
        for (int i = 1; i <= elementos(); i++) {
            cadena = cadena + elemento(i) + " , ";
        }
        cadena = cadena.substring(0, cadena.length() - 2);
        cadena += "-->";
        return cadena;
    }

    public static void main(String[] args) {
        Cola<Integer> cola1 = new ColaArreglo<>(10);
        cola1.encolar(5);
        cola1.encolar(6);
        cola1.encolar(1);
        cola1.encolar(8);
        cola1.encolar(3);
        cola1.encolar(9);
        System.out.println(cola1.toString());
        cola1.desencolar();
        System.out.println(cola1.toString());
        System.out.println(cola1.primero());
        System.out.println(cola1.elementos());
        cola1.vaciar();
        cola1.encolar(8);
        cola1.encolar(10);
        cola1.encolar(1);
        cola1.encolar(20);
        System.out.println(cola1.toString());
        Cola<Integer> cola2 = new ColaArreglo<>(8);
        cola2.encolar(8);
        cola2.encolar(10);
        cola2.encolar(1);
        cola2.encolar(20);
        System.out.println(cola1.equals(cola2));
    }
}
