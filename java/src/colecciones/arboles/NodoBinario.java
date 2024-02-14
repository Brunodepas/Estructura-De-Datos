package colecciones.arboles;

public class NodoBinario<T> {
    private T valor;
    private NodoBinario<T> hi;
    private NodoBinario<T> hd;

    public NodoBinario(T elem) {
        valor = elem;
        hi = null;
        hd = null;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
    public T getValor() {
        return valor;
    }

    public void setHi(NodoBinario<T> hi) {
        this.hi = hi;
    }
    public NodoBinario<T> getHi() {
        return hi;
    }

    public void setHd(NodoBinario<T> hd) {
        this.hd = hd;
    }
    public NodoBinario<T> getHd() {
        return hd;
    }

    public int altura() {
        int alturaIzquierda = getHi() != null ? getHi().altura() : 0;
        int alturaDerecha = getHd() != null ? getHd().altura() : 0;
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    public int tamaño() {
        int tamañoIzquierdo = getHi() != null ? getHi().tamaño() : 0;
        int tamañoDerecho = getHd() != null ? getHd().tamaño() : 0;
        return 1 + tamañoIzquierdo + tamañoDerecho;
    }

    public void preorder() {
        if (this != null) {
            System.out.println(getValor());
            if (getHi() != null) getHi().preorder();
            if (getHd() != null) getHd().preorder();
        }
    }
    public void inorder() {
        if (this != null) {
            if (getHi() != null) getHi().inorder();
            System.out.println(getValor());
            if (getHd() != null) getHd().inorder();
        }
    }
    public void posorder() {
        if (this != null) {
            if (getHi() != null) getHi().posorder();
            if (getHd() != null) getHd().posorder();
            System.out.println(getValor());
        }
    }
}
