package colecciones.arboles;

public interface Diccionario<T> {
    public void insertar(T elem);
    public void eliminar(T elem);
    public Boolean buscar(T elem);
}
