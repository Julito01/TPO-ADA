package main;

import java.util.Set;

import apis.ConjuntoTDA;
import resources.*;

public class Prim {
    public static void main(String[] args) {
        GrafoTDA grafo = new GrafoMA();
        grafo.inicializarGrafo();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarArista(1, 2, 30);
        grafo.agregarArista(1, 3, 69);

        ConjuntoTDA conjuntoAdyacentes = grafo.adyacentes(1);
        imprimirConjunto(conjuntoAdyacentes);

        // TODO: Arreglo de menor costo - Low value array
        // TODO: Arreglo de mas cercano - Closest node array
    }

//    private static Set prim() {
//        GrafoTDA
//        return Set.of();
//    }

    public static void imprimirConjunto(ConjuntoTDA conjunto) {
        while (!conjunto.conjuntoVacio()) {
            int vertice = conjunto.elegir();
            System.out.println(vertice);
            conjunto.sacar(vertice);
        }
    }
}
