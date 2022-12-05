package resources;

public class ColaPrioridad implements ColaPrioridadTDA {
    Elemento[] elementos;
    int indice;
    int indiceAristas = 0;

    public ColaPrioridad() {
    }

    public void inicializarCola() {
        this.indice = 0;
        this.elementos = new Elemento[100];
    }

    public void acolarPrioridad(int x, int y, int peso) {
        int j;
        for(j = this.indice; j > 0 && this.elementos[j - 1].peso >= peso; --j) {
            this.elementos[j] = this.elementos[j - 1];
        }

        this.elementos[j] = new Elemento();
        this.elementos[j].nodoInicio = x;
        this.elementos[j].nodoDestino = y;
        this.elementos[j].peso = peso;
        ++this.indice;
    }

    public void desacolar() {
        this.elementos[this.indiceAristas] = null;
        this.elementos[this.indiceAristas + 1] = null;
        this.indiceAristas = this.indiceAristas + 2;
        --this.indice;
    }

    public boolean colaVacia() {
        return this.indice == 0;
    }

    public int nodoInicio() {
        return this.elementos[this.indiceAristas].nodoInicio;
    }

    public int nodoDestino() {
        return this.elementos[this.indiceAristas].nodoDestino;
    }

    public int peso() {
        int peso = this.elementos[this.indiceAristas].peso;
        return peso;
    }

    class Elemento {
        int nodoInicio;
        int nodoDestino;
        int peso;

        Elemento() {
        }
    }
}
