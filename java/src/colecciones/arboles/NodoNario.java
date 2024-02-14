package colecciones.arboles;
import java.util.ArrayList;

public class NodoNario<T> {
    private T valor;
    private ArrayList<NodoNario<T>> hijos;

    public NodoNario(T valor) {
        this.valor = valor;
        hijos = null;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
    public T getValor() {
        return valor;
    }

    public void setHijos(ArrayList<NodoNario<T>> hijos) {
        this.hijos = hijos;
    }
    public ArrayList<NodoNario<T>> getHijos() {
        return hijos;
    }

    public int altura() {
        int maxAltura = 0;
        if (getHijos() != null) {
            for (NodoNario<T> hijo : getHijos()) {
                int alturaHijo = hijo.altura();
                maxAltura = Math.max(maxAltura, alturaHijo);
            }
            maxAltura = 1 + maxAltura;
        }
        else {
            if (getValor() != null) {
                maxAltura++;
            }
        }
        return maxAltura;
    }

    public int elementos() {
        int elementos = 0;
        if (getHijos() != null) {
            for (NodoNario<T> hijo : getHijos()) {
                int elementosHijo = hijo.elementos();
                elementos += elementosHijo;
            }
            elementos = 1 + elementos;
        }
        else {
            if (getValor() != null) {
                elementos++;
            }
        }
        return elementos;
    }
}
