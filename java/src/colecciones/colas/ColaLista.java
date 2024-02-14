package colecciones.colas;

public class ColaLista<T> implements Cola<T> {
    private NodoLista<T> lista;
    private int elementos;


    public ColaLista() {
        lista = null;
        elementos = 0;
    }
    public ColaLista(T elem) {
        lista = new NodoLista<>(elem);
        elementos++;
    }

    public boolean isEmpty() {
        return (lista == null || elementos == 0);
    }

    public int elementos() {
        return elementos;
    }

    public T primero() {
        if (isEmpty()) {
            throw new IllegalStateException("Cola vacia");
        }
        else {
            NodoLista<T> cursor = lista;
            for (int i = 0; i < elementos()-1; i++) {
                cursor = cursor.getSig();
            }
            return cursor.getValor();
        }
    }

    public T elemento(int posicion) {
        if (posicion > elementos() || posicion <= 0) {
            throw new IllegalArgumentException("posicion invalida");
        }
        else {
            NodoLista<T> cursor = lista;
            for (int i = 1; i < posicion; i++) {
                cursor = cursor.getSig();
            }
            return cursor.getValor();
        }
    }

    public void vaciar() {
        lista = null;
        elementos = 0;
    }

    public void encolar(T elem) {
        NodoLista<T> nodo = new NodoLista<>(elem);
        if (lista == null) {
            lista = nodo;
        }
        else {
            nodo.setSig(lista);
            lista = nodo;
        }
        elementos++;
    }

    public void desencolar() {
        if (isEmpty()) {
            throw new IllegalStateException("Cola vacia");
        }
        NodoLista<T> cursor = lista;
        while (elemento(elementos()-1) != cursor.getValor()) {
            cursor = cursor.getSig();
        }
        cursor.setSig(null);
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
        Cola<Integer> cola1 = new ColaLista<>();
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
        Cola<Integer> cola2 = new ColaLista<>();
        cola2.encolar(8);
        cola2.encolar(10);
        cola2.encolar(1);
        cola2.encolar(20);
        System.out.println(cola1.equals(cola2));
    }
}
