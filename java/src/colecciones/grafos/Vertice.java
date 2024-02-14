package colecciones.grafos;
import java.util.ArrayList;

public class Vertice<T> {
    private T valor;
    private ArrayList<Vertice<T>> adyacentes;
    
    public Vertice(T valor) {
        this.valor = valor;
        adyacentes = null;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
    public T getValor() {
        return valor;
    }

    public void setAdyacentes(ArrayList<Vertice<T>> adyacentes) {
        this.adyacentes = adyacentes;
    }
    public ArrayList<Vertice<T>> getAdyacentes() {
        return adyacentes;
    }

    public Vertice<T> buscarAdyacente(T valor) {
        if (getAdyacentes() != null) {
            for (Vertice<T> adyacente : getAdyacentes()) {
                if (adyacente.getValor().equals(valor)) {
                    return adyacente;
                }
            }
        }
        return null;
    }
    public void agregarAdyacente(Vertice<T> vertice) {
        if (buscarAdyacente(vertice.getValor()) != null) {
            throw new IllegalArgumentException("El vertice ya se encuentra en los adyacentes");
        }
        if (getAdyacentes() == null) {
            adyacentes = new ArrayList<>();
        }
        adyacentes.add(vertice);
    }
    public void eliminarAdyacente(Vertice<T> vertice) {
        if (getAdyacentes() == null || getAdyacentes().size() == 0) {
            throw new IllegalArgumentException("El Vertice no tiene adyacentes");
        }
        Vertice<T> v = buscarAdyacente(vertice.getValor());
        if (v != null) {
            adyacentes.remove(v);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vertice<?>)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Vertice<?> objVertice = (Vertice<?>) obj;
        if (!this.getValor().equals(objVertice.getValor())) {
            return false;
        }
        if (this.getAdyacentes() == null) {
            return objVertice.getAdyacentes() == null;
        }
        if (objVertice.getAdyacentes() == null) {
            return this.getAdyacentes() == null;
        }
        if (this.getAdyacentes().size() != objVertice.getAdyacentes().size()) {
            return false;
        }
        for (int i = 0; i < getAdyacentes().size(); i++) {
            if (!this.getAdyacentes().get(i).equals(objVertice.getAdyacentes().get(i))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String cadena = "{" + getValor() + "} --> ";
        if (getAdyacentes() != null) {
            for (Vertice<T> adyacente : getAdyacentes()) {
                cadena = cadena + "[" + adyacente.getValor() + "] -->";
            }
        } 
        cadena = cadena + "[Null]";
        return cadena;
    }
}
