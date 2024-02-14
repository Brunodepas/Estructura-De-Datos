package colecciones.colas;

public class NodoListaComparable<T extends Comparable<T>> {
    private T valor;
    private NodoListaComparable<T> sig;


    public NodoListaComparable(T valor) {
        this.valor = valor;
        sig = null;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
    public T getValor() {
        return valor;
    }

    public void setSig(NodoListaComparable<T> sig) {
        this.sig = sig;
    }
    public NodoListaComparable<T> getSig() {
        return sig;
    }
}
