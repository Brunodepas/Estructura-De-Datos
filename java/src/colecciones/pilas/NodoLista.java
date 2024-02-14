package colecciones.pilas;

public class NodoLista<T> {
    private T valor;
    private NodoLista<T> sig;


    public NodoLista(T valor) {
        this.valor = valor;
        sig = null;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
    public T getValor() {
        return valor;
    }

    public void setSig(NodoLista<T> sig) {
        this.sig = sig;
    }
    public NodoLista<T> getSig() {
        return sig;
    }
}
