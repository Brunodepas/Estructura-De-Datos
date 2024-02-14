package colecciones.unionfind;

public class UnionFind {
    private int[] padre;
    private int[] rango;

    public UnionFind(int tamaño) {
        padre = new int[tamaño];
        rango = new int[tamaño];

        for (int i = 0; i < tamaño; i++) {
            padre[i] = -1;
        }
    }

    public int find(int x) {
        if (padre[x] == -1) {
            return x;
        }
        if (padre[x] != x) {
            padre[x] = find(padre[x]);
        }
        return padre[x];
    }

    public void union(int x, int y) {
        int raizX = find(x);
        int raizY = find(y);
        if (raizX != raizY) {
            if (rango[raizX] < rango[raizY]) {
                padre[raizX] = raizY;
            }
            else {
                if (rango[raizX] > rango[raizY]) {
                    padre[raizY] = raizX;
                }
                else {
                    padre[raizY] = raizX;
                    rango[raizX]++;
                }
            }
        }
    }
}
