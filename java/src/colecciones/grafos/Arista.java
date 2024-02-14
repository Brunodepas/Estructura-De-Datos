package colecciones.grafos;

public class Arista<T> {
    private Vertice<T> v1;
    private Vertice<T> v2;
    private int costo;

    public Arista(Vertice<T> v1, Vertice<T> v2, int costo) {
        this.v1 = v1;
        this.v2 = v2;
        this.costo = costo;
    }
    public Arista(Vertice<T> v1, Vertice<T> v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.costo = 0;
    }

    public void setV1(Vertice<T> v1) {
        this.v1 = v1;
    }
    public Vertice<T> getV1() {
        return v1;
    }

    public void setV2(Vertice<T> v2) {
        this.v2 = v2;
    }
    public Vertice<T> getV2() {
        return v2;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    public int getCosto() {
        return costo;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Arista<?>)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Arista<?> objArista = (Arista<?>) obj;
        boolean e1 = this.getV1().equals(objArista.getV1());
        boolean e2 = this.getV2().equals(objArista.getV2());
        return e1 && e2;
    }

    public String toString() {
        String cadena = "(" + getV1().getValor() + "," + getV2().getValor() + ") [costo: " + getCosto() + "]";
        return cadena;
      }
    
}
