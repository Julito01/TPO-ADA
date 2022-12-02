package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import apis.ConjuntoTDA;
import resources.*;

public class Main {
    private static ArrayList<Integer> nodosConectados = new ArrayList();
    private static ArrayList<Integer> nodosNoConectados = new ArrayList();
    private static int cantNodos;

    public static void main(String[] args) {
        GrafoTDA grafo = new GrafoMA();
        grafo.inicializarGrafo();

        for (int i = 1; i <= 8; i++) {
            crearVertice(grafo, i);
        }

        // Aristas de Madariaga
        crearArista(grafo, 1, 2, 30);
        crearArista(grafo, 1, 4, 66);
        crearArista(grafo, 1, 6, 69);

        // Aristas de Pinamar
        crearArista(grafo, 2, 3, 53);
        crearArista(grafo, 2, 8, 25);

        // Aristas de Mar de Ajó
        crearArista(grafo, 3, 4, 103);

        // Aristas de Conesa
        crearArista(grafo, 4, 5, 100);

        // Aristas de Guido
        crearArista(grafo, 5, 6, 52);

        // Aristas de Las Armas
        crearArista(grafo, 6, 7, 43);

        // Aristas de Vidal
        crearArista(grafo, 7, 8, 109);

        prim(grafo);
    }

    public static void crearVertice(GrafoTDA grafo, int nodo) {
        grafo.agregarVertice(nodo);
        cantNodos++;
    }

    public static void crearArista(GrafoTDA grafo, int nodo1, int nodo2, int peso) {
        grafo.agregarArista(nodo1, nodo2, peso);
        grafo.agregarArista(nodo2, nodo1, peso);
    }

    private static ArrayList prim(GrafoTDA grafo) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(cantNodos);
        for (int i = 0; i < cantNodos; i++) {
            nodosNoConectados.add(i + 1);
        }

        int nodo = nodosNoConectados.get(randomIndex);

        ArrayList<Integer> menorCosto = armarArregloMenorCosto(grafo, nodo);
        ArrayList<Integer> masCercano = armarArregloMasCercano(grafo, nodo);

        int costo = elegirMenorCosto(menorCosto);

//        System.out.println(costo);
//        int nodoCercano = elegirMasCercano(masCercano);

        return nodosConectados;
    }

    public static int elegirMenorCosto(ArrayList<Integer> menorCosto) {
        int elementoMenor = Integer.MAX_VALUE;

        for (int costo : menorCosto) {
            if (costo != -1) {
                if (costo < elementoMenor) {
                    elementoMenor = costo;
                }
            }
        }
        return elementoMenor;
    }

    public static ArrayList armarArregloMenorCosto(GrafoTDA grafo, int nodoActual) {
        ArrayList<Integer> menorCosto = new ArrayList();

        System.out.println("Nodo elegido: " + nodoActual);


        for (int nodo : nodosNoConectados) {
            if (grafo.existeArista(nodoActual, nodo)) {
                System.out.println("Existe arista, agrego peso: " + grafo.pesoArista(nodoActual, nodo));
                menorCosto.add(grafo.pesoArista(nodoActual, nodo));
            } else {
                menorCosto.add(-1);
            }
        }

        return menorCosto;
    }

    public static ArrayList armarArregloMasCercano(GrafoTDA grafo, int nodoActual) {
        ArrayList<Integer> masCercano = new ArrayList();
        return masCercano;
    }

    public static void imprimirConjunto(ConjuntoTDA conjunto) {
        while (!conjunto.conjuntoVacio()) {
            int vertice = conjunto.elegir();
            System.out.println(vertice);
            conjunto.sacar(vertice);
        }
    }
}
