package colecciones.pilas;

public class PilaLista<T> implements Pila<T> {
    private NodoLista<T> lista;
    private int elementos;


    public PilaLista() {
        lista = null;
        elementos = 0;
    }
    public PilaLista(T elem) {
        lista = new NodoLista<>(elem);
        elementos++;
    }

    public boolean isEmpty() {
        return (lista == null || elementos == 0);
    }

    public int elementos() {
        return elementos;
    }

    public T tope() {
        if (isEmpty()) {
            throw new IllegalStateException("Pila vacia");
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
        if (isEmpty()) {
            throw new IllegalStateException("Pila vacia");
        }
        if (posicion > elementos() || posicion <= 0) {
            throw new IllegalArgumentException("Posicion invalida");
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

    public void apilar(T elem) {
        NodoLista<T> nodo = new NodoLista<>(elem);
        if (lista == null) {
            lista = nodo;
        }
        else {
            NodoLista<T> cursor = lista;
            for (int i = 0; i < elementos()-1; i++) {
                cursor = cursor.getSig();
            }
            cursor.setSig(nodo);
        }
        elementos++;
    }

    public void desapilar() {
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
        Pila<Integer> pila1 = new PilaLista<>();
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
        Pila<Integer> pila2 = new PilaLista<>();
        pila2.apilar(8);
        pila2.apilar(10);
        pila2.apilar(1);
        pila2.apilar(20);
        System.out.println(pila1.equals(pila2));
    }
}
