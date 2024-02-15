package colecciones.grafos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import colecciones.unionfind.UnionFind;

public class GrafoNoDirigido<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Arista<T>> aristas;

    public GrafoNoDirigido() {
        vertices = null;
        aristas = null;
    }

    public ArrayList<Vertice<T>> vertices() {
        return vertices;
    }
    public int cantVertices() {
        return vertices.size();
    }
    public ArrayList<Arista<T>> aristas() {
        return aristas;
    }
    public int cantAristas() {
        return aristas.size();
    }

    public Vertice<T> buscarVertice(T valor) {
        if (vertices() != null) {
            for (Vertice<T> vertice : vertices()) {
                if (vertice.getValor().equals(valor)) {
                    return vertice;
                }
            }
        }
        return null;
    }

    public void agregarVertice(Vertice<T> vertice) {
        if (vertices() == null) {
            vertices = new ArrayList<>();
            vertices.add(vertice);
        }
        else {
            Vertice<T> v = buscarVertice(vertice.getValor());
            if (v == null) {
                vertices.add(vertice);
            }
        }
    }

    public void eliminarVertice(Vertice<T> vertice) {
        if (vertices() == null || cantVertices() == 0) {
            throw new IllegalStateException("El grafo no tiene vertices");
        }
        Vertice<T> v = buscarVertice(vertice.getValor());
        if (v != null) {
            if (aristas() != null) {
                Iterator<Arista<T>> iterador = aristas.iterator();
                while (iterador.hasNext()) {
                    Arista<T> arista = iterador.next();
                    if ((arista.getV1().equals(v)) || (arista.getV2().equals(v))) {
                        iterador.remove();
                    }

                }
            }
            vertices.remove(v);
            for (Vertice<T> ver : vertices()) {
                ver.eliminarAdyacente(v);
            }
        }
    }

    public Arista<T> buscarArista(Vertice<T> ver1, Vertice<T> ver2) {
        if (aristas() != null) {
            Vertice<T> v1 = buscarVertice(ver1.getValor());
            Vertice<T> v2 = buscarVertice(ver2.getValor());
            for (Arista<T> arista : aristas()) {
                if (arista.getV1().equals(v1) && arista.getV2().equals(v2)) {
                    return arista;
                }
            }
        }
        return null;
    }

    public void agregarArista(Vertice<T> ver1, Vertice<T> ver2, int costo) {
        Vertice<T> v1 = buscarVertice(ver1.getValor());
        Vertice<T> v2 = buscarVertice(ver2.getValor());
        if (v1 != null && v2 != null) {
            if (aristas() == null) {
                aristas = new ArrayList<>();
                Arista<T> arista = new Arista<>(v1, v2, costo);
                v1.agregarAdyacente(v2);
                v2.agregarAdyacente(v1);
                aristas.add(arista);
            }
            else {
                if (buscarArista(v1, v2) == null && buscarArista(v2, v1) == null) {
                    Arista<T> arista = new Arista<>(v1, v2, costo);
                    v1.agregarAdyacente(v2);
                    v2.agregarAdyacente(v1);
                    aristas.add(arista);
                }
            }
        }
    }

    public void eliminarArista(Vertice<T> ver1, Vertice<T> ver2) {
        if (aristas() == null || cantAristas() == 0) {
            throw new IllegalStateException("El grafo no tiene aristas");
        }
        Vertice<T> v1 = buscarVertice(ver1.getValor());
        Vertice<T> v2 = buscarVertice(ver2.getValor());
        if (v1 != null && v2 != null) {
            for (Arista<T> arista : aristas()) {
                if ((arista.getV1().equals(v1) && arista.getV2().equals(v2)) || (arista.getV1().equals(v2) && arista.getV2().equals(v1))) {
                    aristas.remove(arista);
                    v1.eliminarAdyacente(v2);
                    v2.eliminarAdyacente(v1);
                }
            }
        }
    }

    public int pesoArista(Vertice<T> v1, Vertice<T> v2) {
        Arista<T> arista = buscarArista(v1, v2);
        if (arista != null) {
            return arista.getCosto();
        }
        return -1;
    }

    public String toString() {
        String cadena = "Vertices:\n";
        for (Vertice<T> vertice : vertices()) {
            cadena += vertice.toString() + "\n";
        }
        cadena += "Aristas: {";
        for (Arista<T> arista : aristas()) {
            cadena += arista.toString() + ",";
        }
        cadena = cadena.substring(0, cadena.length()-1);
        return cadena + "}";
    }
    
    public void dfs(Vertice<T> origen, ArrayList<Vertice<T>> visitados) {
        if (!visitados.contains(origen)) {
            visitados.add(origen);
            System.out.println(origen.getValor());
            if (origen.getAdyacentes() != null) {
                for (Vertice<T> adyacente : origen.getAdyacentes()) {
                    dfs(adyacente, visitados);
                }
            }
        }
    }

    public void bfs(Vertice<T> origen, ArrayList<Vertice<T>> visitados) {
        if (!visitados.contains(origen)) {
            Queue<Vertice<T>> q = new LinkedList<>();
            q.add(origen);
            visitados.add(origen);
            while (!q.isEmpty()) {
                Vertice<T> w = q.poll();
                System.out.println(w.getValor());
                if (w.getAdyacentes() != null) {
                    for (Vertice<T> adyacente : w.getAdyacentes()) {
                        if (!visitados.contains(adyacente)) {
                            visitados.add(adyacente);
                            q.add(adyacente);
                        }
                    }
                }
            }
        }
    }

    public boolean conexo() {
        Vertice<T> origen = vertices().get(0);
        ArrayList<Vertice<T>> visitados = new ArrayList<>();
        dfsconexo(origen, visitados);
        return visitados.size() == cantVertices();
    }
    private void dfsconexo(Vertice<T> origen, ArrayList<Vertice<T>> visitados) {
        if (!visitados.contains(origen)) {
            visitados.add(origen);
            if (origen.getAdyacentes() != null) {
                for (Vertice<T> adyacente : origen.getAdyacentes()) {
                    dfsconexo(adyacente, visitados);
                }
            }
        }
    }

    public void caminosSimples(Vertice<T> origen, Vertice<T> destino) {
        ArrayList<Vertice<T>> visitados = new ArrayList<>();
        int[] contador = {0};
        dfscaminosSimples(origen, destino, contador, visitados);
        System.out.println("Cantidad de caminos simples entre " + origen.getValor() + " y " + destino.getValor() + ": " + contador[0]);
    }
    private void dfscaminosSimples(Vertice<T> origen, Vertice<T> destino, int[] contador, ArrayList<Vertice<T>> visitados) {
        if (!visitados.contains(origen)) {
            visitados.add(origen);
            if (origen.equals(destino)) {
                contador[0]++;
            }
            if (origen.getAdyacentes() != null) {
                for (Vertice<T> adyacente : origen.getAdyacentes()) {
                    dfscaminosSimples(adyacente, destino, contador, visitados);
                }
            }
            visitados.remove(visitados.size() -1);
        }
    }

    public void aamPrims() {
        List<Arista<T>> arbolMin = prims();
        int costoTotal = 0;
        System.out.println("Árbol de Expansión Mínima (Prim):");
        for (Arista<T> arista : arbolMin) {
            costoTotal += arista.getCosto();
            System.out.println(arista);
        }
        System.out.println("Costo Total: " + costoTotal);
    }
    private List<Arista<T>> prims() {
        List<Arista<T>> arbolMin = new ArrayList<>();
        Set<Vertice<T>> visitados = new HashSet<>();
        PriorityQueue<Arista<T>> cola = new PriorityQueue<>(Comparator.comparing(Arista<T>::getCosto));
        Vertice<T> origen = vertices().get(0);
        visitados.add(origen);

        for (Arista<T> arista : aristas()) {
            if (arista.getV1().equals(origen)) {
                cola.add(arista);
            }
        }

        while (!cola.isEmpty()) {
            Arista<T> aristaActual = cola.poll();
            Vertice<T> verticeActual = aristaActual.getV2();
            if (!visitados.contains(verticeActual)) {
                arbolMin.add(aristaActual);
                visitados.add(verticeActual);
                for (Vertice<T> adyacente : verticeActual.getAdyacentes()) {
                    Arista<T> aristaNueva = buscarArista(verticeActual, adyacente);
                    if (aristaNueva != null) {
                        if (!visitados.contains(adyacente) && aristaNueva.getCosto() > -1) {
                            cola.add(aristaNueva);
                        }
                    }
                }
            }
        }
        arbolMin.sort(Comparator.comparingInt(Arista::getCosto));
        return arbolMin;
    }

    public void aamKruscal() {
        List<Arista<T>> arbolMin = kruscal();
        int costoTotal = 0;
        System.out.println("Árbol de Expansión Mínima (Kruskal):");
        for (Arista<T> arista : arbolMin) {
            costoTotal += arista.getCosto();
            System.out.println(arista);
        }
        System.out.println("Costo Total: " + costoTotal);
    }
    private List<Arista<T>> kruscal() {
        List<Arista<T>> arbolMin = new ArrayList<>();
        List<Arista<T>> aristasOrd = new ArrayList<>(aristas());
        aristasOrd.sort(Comparator.comparing(Arista::getCosto));
        UnionFind conjuntos = new UnionFind(cantVertices());

        for (Arista<T> arista : aristasOrd) {
            Vertice<T> v1 = arista.getV1();
            Vertice<T> v2 = arista.getV2();
            int indexv1 = vertices().indexOf(v1);
            int indexv2 = vertices().indexOf(v2);
            if (conjuntos.find(indexv1) != conjuntos.find(indexv2)) {
                arbolMin.add(arista);
                conjuntos.union(indexv1, indexv2);
            }
        }
        return arbolMin;
    }

    public static void main(String[] args) {
        Vertice<Integer> v1 = new Vertice<Integer>(1);
        Vertice<Integer> v2 = new Vertice<Integer>(2);
        Vertice<Integer> v3 = new Vertice<Integer>(3);
        GrafoNoDirigido<Integer> grafo = new GrafoNoDirigido<>();
        grafo.agregarVertice(v1);
        grafo.agregarVertice(v2);
        grafo.agregarVertice(v3);
        grafo.agregarArista(v1, v2, 4);
        grafo.agregarArista(v1, v3, 7);
        grafo.agregarArista(v2, v3, 1);
        grafo.dfs(grafo.buscarVertice(1), new ArrayList<Vertice<Integer>>());
        grafo.bfs(grafo.buscarVertice(1), new ArrayList<Vertice<Integer>>());
        System.out.println(grafo.conexo());
        grafo.caminosSimples(v1, v2);
        grafo.aamPrims();
        grafo.aamKruscal();
    }
}
