package colecciones.arboles;

import java.util.ArrayList;

public class ANario<T> {
    private NodoNario<T> raiz;

    public ANario(T elem) {
        raiz = new NodoNario<>(elem);
    }

    public int altura() {
        return raiz.altura();
    }

    public int elementos() {
        return raiz.elementos();
    }

    public Boolean buscar(T elem) {
        return buscarNodo(raiz, elem) != null;
    }
    public NodoNario<T> buscarNodo(NodoNario<T> raiz, T elem) {
        if (raiz.getValor().equals(elem)) {
            return raiz;
        }
        if (raiz.getHijos() != null) {
            for (NodoNario<T> hijo : raiz.getHijos()) {
                NodoNario<T> resultado = buscarNodo(hijo, elem);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }

    public void insertar(T elem, T padre) {
        if (buscarNodo(raiz, elem) != null) {
            throw new IllegalArgumentException("El elemento ya esta en el arbol");
        }
        NodoNario<T> nodoPadre = buscarNodo(raiz, padre);
        if (nodoPadre != null) {
            if (nodoPadre.getHijos() == null) {
                nodoPadre.setHijos(new ArrayList<>());
            }
            nodoPadre.getHijos().add(new NodoNario<>(elem));
        }
        else {
            throw new IllegalAccessError("Padre no encontrado");
        }
    }

    public void eliminar(T elem) {
        if (raiz.getValor().equals(elem) && (raiz.getHijos().size() != 0 || raiz.getHijos() != null)) {
            System.out.println("no se puede eliminar la raiz,primero hay que eliminar los hijos");
        }
        else{
            if (raiz.getValor().equals(elem) && (raiz.getHijos().size() == 0 || raiz.getHijos() == null)) {
                raiz = null;
            }
            else {
                eliminar(raiz, elem);
            }
        }
    }
    private void eliminar(NodoNario<T> actual, T elem) {
        ArrayList<NodoNario<T>> hijos = actual.getHijos();
        if (hijos != null) {
            for (int i = 0; i < hijos.size(); i++) {
                NodoNario<T> hijo = hijos.get(i);
                if (hijo.getValor().equals(elem)) {
                    if (hijo.getHijos() != null) {
                        T nuevoelem = hijo.getHijos().get(hijo.getHijos().size()-1).getValor();
                        hijo.setValor(nuevoelem);
                        eliminar(hijo, nuevoelem);
                        return;
                    }
                    else {
                        hijos.remove(i);
                        return;
                    }
                }
                else {
                    eliminar(hijo, elem);
                }
            }
        }
    }
    
    private void imprimir(NodoNario<T> nodo, int nivel) {
        StringBuilder espacios = new StringBuilder();

        System.out.println(espacios + nodo.getValor().toString());
        if (nodo.getHijos() != null) {
            for (NodoNario<T> hijo : nodo.getHijos()) {
                imprimir(hijo, nivel + 1);
            }
        }
    }

    public static void main(String[] args) {
        ANario<Integer> arbol = new ANario<Integer>(50);
        arbol.insertar(10, 50);
        arbol.insertar(20, 50);
        arbol.insertar(30, 50);
        arbol.insertar(40, 50);
        arbol.insertar(5, 10);
        arbol.insertar(3, 5);
        arbol.insertar(1, 3);
        arbol.insertar(15, 20);
        arbol.insertar(25, 30);
        arbol.insertar(35, 40);
        System.out.println("Árbol original:");
        arbol.imprimir(arbol.raiz, 0);
        System.out.println("\n¿Existe 5 en el árbol? " + arbol.buscar(5));

        arbol.eliminar(30);

        System.out.println("\nÁrbol después de eliminar 30:");
        arbol.imprimir(arbol.raiz, 0);
        System.out.println("\n¿Altura del arbol? " + arbol.altura());
    }
}
