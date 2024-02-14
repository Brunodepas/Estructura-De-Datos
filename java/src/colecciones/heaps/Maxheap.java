package colecciones.heaps;
import java.util.ArrayList;

public class Maxheap<T extends Comparable<T>> {
    private ArrayList<T> raiz;

    public Maxheap() {
        raiz = null;
    }
    public Maxheap(T elem) {
        raiz = new ArrayList<>();
        raiz.add(elem);
    }

    public int elementos() {
        return raiz.size();
    }

    public void vaciar() {
        raiz.clear();
    }

    public boolean isEmpty() {
        return raiz == null || elementos() == 0;
    }

    public T tope() {
        return raiz.get(0);
    }

    public int padre(int i) {
        if (i <= 0) {
            return -1;
        }
        return (i-1)/2;
    }
    public int hd(int i) {
        int hd = (2*i) + 2;
        if (hd >= elementos()) {
            return -1;
        }
        return hd;
    }
    public int hi(int i) {
        int hi = (2*i) + 1;
        if (hi >= elementos()) {
            return -1;
        }
        return hi;
    }

    private void swap(int i, int j) {
        T temp = raiz.get(i);
        raiz.set(i, raiz.get(j));
        raiz.set(j, temp);
    }

    public void insertar(T elem) {
        if (raiz == null) {
            raiz = new ArrayList<>();
        }
        if (!raiz.contains(elem)) {
            raiz.add(elem);
            int indexelem = elementos() - 1;
            while (indexelem > 0) {
                int indexpadre = padre(indexelem);
                int cmp = raiz.get(indexelem).compareTo(raiz.get(indexpadre));
                if (cmp > 0) {
                    swap(indexpadre, indexelem);
                    indexelem = indexpadre;
                }
                else {
                    break;
                }
            }
        }
    }

    private void intercambio(int i) {
        int padre = i;
        int hi = hi(padre);
        int hd = hd(padre);
        int cmphi = (hi != -1) ? (raiz.get(hi).compareTo(raiz.get(padre))) : 0;
        int cmphd = (hd != -1) ? (raiz.get(hd).compareTo(raiz.get(padre))) : 0;
        if ((hi != -1 && cmphi > 0) || (hd != -1 && cmphd > 0)) {
            if (hi != -1 && (hd == -1 || raiz.get(hi).compareTo(raiz.get(hd)) > 0)) {
                padre = hi;
            }
            else {
                if (hd != -1 && (hi == -1 || raiz.get(hd).compareTo(raiz.get(hi)) > 0)) {
                    padre = hd;
                }
            }
            swap(padre, i);
            intercambio(padre);
        }
    }

    public T remover() {
        if (isEmpty()) {
            throw new IllegalStateException("heap vacio");
        }
        if (elementos() == 1) {
            return raiz.remove(0);
        }
        T Maxelem = tope();
        T ultimoElem = raiz.remove(elementos()-1);
        raiz.set(0, ultimoElem);
        intercambio(0);
        return Maxelem;
    }

    public String toString(){
        if (isEmpty()) {
            return "[ ]";
        }
		String cadena = "[ ";
		for (int i = 0;i < raiz.size();i++ ) {
			cadena = cadena + raiz.get(i) + " , ";
		}
        cadena = cadena.substring(0, cadena.length() - 2);
		cadena = cadena + "]";
		return cadena;
	}


    public static void main(String[] args) {
        Maxheap<Integer> heap = new Maxheap<>();
        heap.insertar(5);
        heap.insertar(8);
        heap.insertar(9);
        heap.insertar(4);
        System.out.println(heap.elementos());
        heap.remover();
    }
}
