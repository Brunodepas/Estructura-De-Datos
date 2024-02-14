package colecciones.arboles;

public class ABB<T extends Comparable<T>> implements Diccionario<T> {
    private NodoBinario<T> raiz;

    public ABB(T elem) {
        raiz = new NodoBinario<T>(elem);
    }
    public ABB() {
        raiz = null;
    }

    public void setRaiz(NodoBinario<T> raiz) {
        this.raiz = raiz;
    }
    public NodoBinario<T> getRaiz() {
        return raiz;
    }

    public int altura() {
        return raiz != null ? raiz.altura() : 0;
    }

    public int tamaño() {
        return raiz != null ? raiz.tamaño() : 0;
    }

    public Boolean isEmpty() {
        return (raiz == null || altura() == 0);
    }

    public void preorder() {
        raiz.preorder();
    }
    public void inorder() {
        raiz.inorder();
    }
    public void posorder() {
        raiz.posorder();
    }

    public void vaciar() {
        raiz = null;
    }

    public void insertar(T elem) {
        raiz = insertar(raiz, elem);
    }
    private NodoBinario<T> insertar(NodoBinario<T> raiz, T elem) {
        if (raiz == null) {
            raiz = new NodoBinario<T>(elem);
        }
        else {
            int comparador = raiz.getValor().compareTo(elem);
            if (comparador > 0) {
                raiz.setHi(insertar(raiz.getHi(), elem));
            }
            if (comparador < 0) {
                raiz.setHd(insertar(raiz.getHd(), elem));
            }
        }
        return raiz;
    }

    public T menorValor() {
        return menorValor(raiz);
    }
    private T menorValor(NodoBinario<T> raiz) {
        if (raiz.getHi() != null) {
            return menorValor(raiz.getHi());
        }
        return raiz.getValor();
    }

    public T mayorValor() {
        return mayorValor(raiz);
    }
    private T mayorValor(NodoBinario<T> raiz) {
        if (raiz.getHd() != null) {
            return mayorValor(raiz.getHd());
        }
        return raiz.getValor();
    }

    public void eliminar(T elem) {
        raiz = eliminar(raiz, elem);
    }
    private NodoBinario<T> eliminar(NodoBinario<T> raiz, T elem) {
        if (raiz == null) {
            throw new IllegalArgumentException("No se encontro el elemento a borrar");
        }
        int comparador = raiz.getValor().compareTo(elem);
        if (comparador > 0) {
            raiz.setHi(eliminar(raiz.getHi(), elem));
        }
        if (comparador < 0) {
            raiz.setHd(eliminar(raiz.getHd(), elem));
        }
        if (comparador == 0) {
            if (raiz.getHi() == null) {
                return raiz.getHd();
            }
            if (raiz.getHd() == null) {
                return raiz.getHi();
            }
            raiz.setValor(menorValor(raiz.getHd()));
            raiz.setHd(eliminar(raiz.getHd(), raiz.getValor()));
        }
        return raiz;
    }

    public Boolean buscar(T elem) {
        return buscar(raiz, elem);
    }
    private Boolean buscar(NodoBinario<T> raiz, T elem) {
        if (raiz == null) {
            return false;
        }
        else {
            int comparador = raiz.getValor().compareTo(elem);
            if (comparador == 0) {
                return true;
            }
            if (comparador > 0) {
                return buscar(raiz.getHi(), elem);
            }
            if (comparador < 0) {
                return buscar(raiz.getHd(), elem);
            }
        }
        return false;
    }

    public T predecesor(T elem) {
        T p = null;
        Comparable[] arreglo = arregloinorder();
        for (int i = 0; i < arreglo.length && arreglo[i].compareTo(elem) < 0; i++) {
            p = (T) arreglo[i];
        }
        return p;
    }
    public T sucesor(T elem) {
        T s = null;
        Comparable[] arreglo = arregloinorder();
        for (int i = arreglo.length - 1; i >= 0 && arreglo[i].compareTo(elem) > 0; i--) {
            s = (T) arreglo[i];
        }
        return s;
    }

    private Comparable[] arregloinorder() {
        Comparable[] arreglo = new Comparable[tamaño()];
        int[] index = {0};
        arregloinorder(raiz, arreglo, index);
        return arreglo;
    }
    private void arregloinorder(NodoBinario<T> raiz, Comparable[] arreglo, int[] index) {
        if (raiz != null) {
            if (raiz.getHi() != null) arregloinorder(raiz.getHi(), arreglo, index);
            arreglo[index[0]] = raiz.getValor();
            index[0]++;
            if (raiz.getHd() != null) arregloinorder(raiz.getHd(), arreglo, index);
        }
    }

    public Boolean repOK() {
        Comparable[] arreglo = arregloinorder();
        for (int i = 0; i < arreglo.length -1; i++) {
            if (arreglo[i].compareTo(arreglo[i+1]) >= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ABB<Integer> arbol1 = new ABB<>();
        arbol1.insertar(7);
        arbol1.insertar(9);
        arbol1.insertar(4);
        arbol1.insertar(15);
        arbol1.insertar(1);
        arbol1.eliminar(15);
        System.out.println(arbol1.predecesor(9));
        System.out.println(arbol1.sucesor(7));
        System.out.println(arbol1.buscar(4));
        arbol1.inorder();
        arbol1.arregloinorder();
        System.out.println(arbol1.repOK());
    }
}
