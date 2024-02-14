package colecciones.arboles;

public class AVL<T extends Comparable<T>> implements Diccionario<T> {
    private NodoBinario<T> raiz;

    public AVL(T elem) {
        raiz = new NodoBinario<T>(elem);
    }
    public AVL() {
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

    public int balance() {
        return balance(raiz);
    }
    private int balance(NodoBinario<T> raiz) {
        if (raiz == null) {
            return 0;
        }
        int alturaHi = raiz.getHi() != null ? raiz.getHi().altura() : 0;
        int alturaHd = raiz.getHd() != null ? raiz.getHd().altura() : 0;
        return alturaHi - alturaHd;
    }

    private NodoBinario<T> RotD(NodoBinario<T> k1) {
        NodoBinario<T> k2 = k1.getHi();
        k1.setHi(k2.getHd());
        k2.setHd(k1);
        return k2;
    }
    private NodoBinario<T> RotI(NodoBinario<T> k1) {
        NodoBinario<T> k2 = k1.getHd();
        k1.setHd(k2.getHi());
        k2.setHi(k1);
        return k2;
    }

    private NodoBinario<T> balancear(NodoBinario<T> raiz) {
        int balanceraiz = balance(raiz);
        int balancehi = balance(raiz.getHi());
        int balancehd = balance(raiz.getHd());
        if (balanceraiz >= 2) {
            if (balancehi <= -1) {
                raiz.setHi(RotI(raiz.getHi()));
            }
            return RotD(raiz);
        }
        if (balanceraiz <= -2) {
            if (balancehd >= 1) {
                raiz.setHd(RotD(raiz.getHd()));
            }
            return RotI(raiz);
        }
        return raiz;
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
        return balancear(raiz);
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
        return balancear(raiz);
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
    private Boolean balanceado(NodoBinario<T> raiz) {
        if (raiz == null) {
            return true;
        }
        return (balance(raiz) <= 1 && balance(raiz) >= -1) ? true && balanceado(raiz.getHi()) && balanceado(raiz.getHd()) : false;
    }

    public Boolean repOK() {
        Comparable[] arreglo = arregloinorder();
        for (int i = 0; i < arreglo.length -1; i++) {
            if (arreglo[i].compareTo(arreglo[i+1]) >= 0) {
                return false;
            }
        }
        return true && balanceado(raiz);
    }

    public static void main(String[] args) {
        AVL<Integer> arbol1 = new AVL<>();
        arbol1.insertar(8);
        arbol1.insertar(10);
        arbol1.insertar(5);
        arbol1.insertar(15);
        arbol1.insertar(3);
        arbol1.insertar(20);
        arbol1.insertar(30);
        arbol1.eliminar(5);
        System.out.println(arbol1.buscar(4));
        arbol1.inorder();
        arbol1.arregloinorder();
        System.out.println(arbol1.repOK());
    }
}
