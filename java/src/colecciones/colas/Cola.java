package colecciones.colas;

public interface Cola<T> {

    public boolean isEmpty();

    public int elementos();

    public T primero();

    public T elemento(int posicion);

    public void vaciar();

    public void encolar(T elem);

    public void desencolar();

    public boolean equals(Object obj);

    public boolean repOK();

    public String toString();
}