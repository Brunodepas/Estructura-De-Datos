package colecciones.grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class GrafoDirigido<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Arista<T>> aristas;

    public GrafoDirigido() {
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
                v1.agregarAdyacente(v2);
                Arista<T> arista = new Arista<>(v1, v2, costo);
                aristas.add(arista);
            }
            else {
                if (buscarArista(v1, v2) == null) {
                    v1.agregarAdyacente(v2);
                    Arista<T> arista = new Arista<>(v1, v2, costo);
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
                if (arista.getV1().equals(v1) && arista.getV2().equals(v2)) {
                    aristas.remove(arista);
                    v1.eliminarAdyacente(v2);
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
        ArrayList<Vertice<T>> visitados = new ArrayList<>();
        for (Vertice<T> origen : vertices()) {
            dfsconexo(origen, visitados);
            if (visitados.size() != cantVertices()) {
                return false;
            }
            visitados.clear();
        }
        return true;
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
        System.out.println("Cantidad de caminos simples entre " + origen.getValor() + " y " + destino.getValor() + " son : " + contador[0]);
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

    public int[][] obtenerMatrizAdyacencia() {
        int cantVertices = cantVertices();
        int inf = Integer.MAX_VALUE;
        int[][] matriz = new int[cantVertices][cantVertices];
    
        for (int i = 0;i < cantVertices;i++ ) {
            for (int j = 0;j < cantVertices;j++ ) {
                if (i == j) {
                   matriz[i][j] = 0; 
                }
                else {
                    matriz[i][j] = inf;
                }
            }
        }
    
        for (Arista<T> arista : aristas()) {
            int indexV1 = vertices.indexOf(arista.getV1());
            int indexV2 = vertices.indexOf(arista.getV2());
            matriz[indexV1][indexV2] = arista.getCosto();
        }
    
        return matriz;
    }

    public void mostrarMatrizAdyacencia() {
        int[][] matrizAdyacencia = obtenerMatrizAdyacencia();
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia[i].length; j++) {
                if (matrizAdyacencia[i][j] == Integer.MAX_VALUE) {
                    System.out.print(" |I| ");
                }
                else{
                    System.out.print(" |" + matrizAdyacencia[i][j] + "| ");
                }
            }
            System.out.println();
        }
    }

    public void warshall() {
        int cantVertices = cantVertices();
        int[][] d = new int[cantVertices][cantVertices];
        int[][] path = new int[cantVertices][cantVertices];
        int[][] grafo = obtenerMatrizAdyacencia();
        warshall(grafo, d, path);
        System.out.println("path:");
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("costo:");
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                if (d[i][j] == Integer.MAX_VALUE) {
                    System.out.print("I" + " ");
                }
                else {
                    System.out.print(d[i][j] + " ");
                }
            }
            System.out.println();
        }
    
    }
    
    private void warshall(int[][] grafo, int[][] d, int[][] path) {
        int n = grafo.length;
        int inf = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = grafo[i][j];
                if (i != j && grafo[i][j] != inf) {
                    path[i][j] = i + 1;
                } else {
                    path[i][j] = -1;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][k] != inf && d[k][j] != inf && d[i][k] + d[k][j] < d[i][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
    }

    public void dijkstra(Vertice<T> origen) {
        Map<T, Vertice<T>> prev = new HashMap<>();
        Map<T, Integer> dist = new HashMap<>();
        dijkstra(origen,prev,dist);
        System.out.println("Distancias más cortas desde el vértice "+ origen.getValor() + ":");
        for (Map.Entry<T, Integer> entry : dist.entrySet()) {
            T verticeId = entry.getKey();
            int distancia = entry.getValue();
            System.out.println("Hasta vértice " + verticeId + ": " + (distancia == Integer.MAX_VALUE ? "Infinito" : distancia));
        }
        System.out.println("\nVértices previos en el camino más corto:");
        for (Map.Entry<T, Vertice<T>> entry : prev.entrySet()) {
            T verticeId = entry.getKey();
            Vertice<T> verticePrevio = entry.getValue();
            System.out.println("Vértice " + verticeId + ": " + (verticePrevio == null ? "Ninguno" : verticePrevio.getValor()));
        }
    }
    private void dijkstra(Vertice<T> origen, Map<T, Vertice<T>> prev, Map<T, Integer> dist) {
        int inf = Integer.MAX_VALUE;

        for (Vertice<T> w : vertices()) {
            prev.put(w.getValor(), null);
            dist.put(w.getValor(), inf);
        }

        dist.put(origen.getValor(), 0);

        Set<Vertice<T>> s = new HashSet<>();
        PriorityQueue<Vertice<T>> q = new PriorityQueue<>(Comparator.comparingInt(vertice -> dist.get(vertice.getValor())));
        q.add(origen);

        while (!q.isEmpty()) {
            Vertice<T> u = q.poll();
            s.add(u);

            for (Arista<T> arista : aristas()) {
                if (arista.getV1().equals(u)) {
                    Vertice<T> y = arista.getV2();
                    int nuevadist = dist.get(u.getValor()) + arista.getCosto();

                    if (!s.contains(y) && nuevadist < dist.get(y.getValor())) {
                        dist.put(y.getValor(), nuevadist);
                        prev.put(y.getValor(), u);
                        q.offer(y);
                    }
                }
            }
        }
    }
    

    public boolean esAciclico() {
        Set<Vertice<T>> visitados = new HashSet<>();
        Set<Vertice<T>> enProceso = new HashSet<>();

        for (Vertice<T> v : vertices) {
            if (!visitados.contains(v)) {
                if (tieneCicloDFS(v, visitados, enProceso)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean tieneCicloDFS(Vertice<T> origen, Set<Vertice<T>> visitados, Set<Vertice<T>> enProceso) {
        visitados.add(origen);
        enProceso.add(origen);

        if (origen.getAdyacentes() != null) {
            for (Vertice<T> adyacente : origen.getAdyacentes()) {
                if (!visitados.contains(adyacente)) {
                    if (tieneCicloDFS(adyacente, visitados, enProceso)) {
                        return true;
                    }
                } 
                else if (enProceso.contains(adyacente)) {
                    return true;
                }
            }
        }

        enProceso.remove(origen);
        return false;
    }
    
    public List<Vertice<T>> ordenamientoTopologicoDFS() {
        if (!esAciclico()) {
            throw new IllegalStateException("El grafo tiene ciclos");
        }
        List<Vertice<T>> ordenTopologico = new ArrayList<>();
        Set<Vertice<T>> visitados = new HashSet<>();

        for (Vertice<T> v : vertices) {
            if (!visitados.contains(v)) {
                dfsTopologico(v, visitados, ordenTopologico);
            }
        }

        Collections.reverse(ordenTopologico);
        return ordenTopologico;
    }

    private void dfsTopologico(Vertice<T> origen, Set<Vertice<T>> visitados, List<Vertice<T>> ordenTopologico) {
        visitados.add(origen);

        if (origen.getAdyacentes() != null) {
            for (Vertice<T> adyacente : origen.getAdyacentes()) {
                if (!visitados.contains(adyacente)) {
                    dfsTopologico(adyacente, visitados, ordenTopologico);
                }
            }
    
        }
        ordenTopologico.add(origen);
    }

    public boolean cicloNegativo() {
        Set<Vertice<T>> visitados = new HashSet<>();
        
        for (Vertice<T> v : vertices) {
            if (!visitados.contains(v)) {
                if (tieneCicloNegDFS(v, visitados)) {
                    return true;
                }
            }
        }
    
        return false;
    }
    
    private boolean tieneCicloNegDFS(Vertice<T> origen, Set<Vertice<T>> visitados) {
        visitados.add(origen);
    
        if (origen.getAdyacentes() != null) {
            for (Vertice<T> adyacente : origen.getAdyacentes()) {
                int costoArista = pesoArista(origen, adyacente);
                if (!visitados.contains(adyacente)) {
                    if (tieneCicloNegDFS(adyacente, visitados)) {
                        return true;
                    }
                } else if (costoArista < 0) { // Verifica si la arista tiene costo negativo
                    return true;
                }
            }
        }
    
        return false;
    }
    

    public static void main(String[] args) {
        /*Vertice<Integer> v1 = new Vertice<>(1);
        Vertice<Integer> v2 = new Vertice<>(2);
        Vertice<Integer> v3 = new Vertice<>(3);
        Vertice<Integer> v4 = new Vertice<>(4);
        Vertice<Integer> v5 = new Vertice<>(5);
        Vertice<Integer> v6 = new Vertice<>(6);
        Vertice<Integer> v7 = new Vertice<>(7);
        GrafoDirigido<Integer> grafo = new GrafoDirigido<>();
    
    
        grafo.agregarVertice(v1);
        grafo.agregarVertice(v2);
        grafo.agregarVertice(v3);
        grafo.agregarVertice(v4);
        grafo.agregarVertice(v5);
        grafo.agregarVertice(v6);
        grafo.agregarVertice(v7);
    
    
        grafo.agregarArista(v1, v2, 2);
        grafo.agregarArista(v1, v4, 1);
        grafo.agregarArista(v2, v4, 3);
        grafo.agregarArista(v2, v5, 10);
        grafo.agregarArista(v3, v1, 4);
        grafo.agregarArista(v3, v6, 5);
        grafo.agregarArista(v4, v3, 2);
        grafo.agregarArista(v4, v5, 2);
        grafo.agregarArista(v4, v6, 8);
        grafo.agregarArista(v4, v7, 4);
        grafo.agregarArista(v5, v7, 6);
        grafo.agregarArista(v7, v6, 1);*/

        /*Vertice<Integer> v1 = new Vertice<>(1);
        Vertice<Integer> v2 = new Vertice<>(2);
        Vertice<Integer> v3 = new Vertice<>(3);
        Vertice<Integer> v4 = new Vertice<>(4);
        Vertice<Integer> v5 = new Vertice<>(5);
        GrafoDirigido<Integer> grafo = new GrafoDirigido<>();

        grafo.agregarVertice(v1);
        grafo.agregarVertice(v2);
        grafo.agregarVertice(v3);
        grafo.agregarVertice(v4);
        grafo.agregarVertice(v5);

        grafo.agregarArista(v1, v2, 2);
        grafo.agregarArista(v1, v4, 1);
        grafo.agregarArista(v2, v4, 3);
        grafo.agregarArista(v3, v1, 4);
        grafo.agregarArista(v3, v5, 5);
        grafo.agregarArista(v4, v5, 2);
    
        System.out.println(grafo.toString());
        grafo.mostrarMatrizAdyacencia();
        grafo.warshall();
        grafo.dijkstra(grafo.vertices().get(0));
        System.out.println(grafo.esAciclico());
        List<Vertice<Integer>> grafoOrdenado = grafo.ordenamientoTopologicoDFS();*/

        Vertice<Integer> v1 = new Vertice<>(1);
        Vertice<Integer> v2 = new Vertice<>(2);
        Vertice<Integer> v3 = new Vertice<>(3);
        Vertice<Integer> v4 = new Vertice<>(4);
        Vertice<Integer> v5 = new Vertice<>(5);
        GrafoDirigido<Integer> grafo = new GrafoDirigido<>();
    
        grafo.agregarVertice(v1);
        grafo.agregarVertice(v2);
        grafo.agregarVertice(v3);
        grafo.agregarVertice(v4);
        grafo.agregarVertice(v5);
    
        grafo.agregarArista(v1, v2, 2);
        grafo.agregarArista(v1, v4, 1);
        grafo.agregarArista(v2, v4, 3);
        grafo.agregarArista(v3, v1, -4); // Arista con peso negativo para crear un ciclo negativo
        grafo.agregarArista(v3, v5, 5);
        grafo.agregarArista(v4, v5, 2);
        grafo.agregarArista(v5, v3, 1); // Arista que cierra el ciclo negativo
    

        System.out.println(grafo.toString());
        System.out.println(grafo.esAciclico());
        grafo.warshall();
        System.out.println(grafo.cicloNegativo());
    }
}
