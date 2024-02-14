package colecciones.pilas;

public interface Pila<T> {

    public boolean isEmpty();

    public int elementos();

    public T tope();

    public T elemento(int posicion);

    public void vaciar();

    public void apilar(T elem);

    public void desapilar();

    public boolean equals(Object obj);

    public boolean repOK();

    public String toString();
}