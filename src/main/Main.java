package main;

import resources.GrafoMA;
import resources.GrafoTDA;

public class Main {
    public static void main(String[] args) {
        GrafoTDA grafo = new GrafoMA();
        grafo.inicializarGrafo();

        int cantidadNodos = 8;

        for (int i = 1; i <= cantidadNodos; i++) {
            grafo.crearVertice(grafo, i);
        }

        // Aristas de Madariaga
        grafo.crearArista(grafo, 1, 2, 30);
        grafo.crearArista(grafo, 1, 4, 66);
        grafo.crearArista(grafo, 1, 6, 69);
        grafo.crearArista(grafo, 1, 7, 111);
        grafo.crearArista(grafo, 1, 8, 32);
        grafo.crearArista(grafo, 1, 3, 78);
        grafo.crearArista(grafo, 1, 5, 120);

        // Aristas de Pinamar
        grafo.crearArista(grafo, 2, 3, 53);
        grafo.crearArista(grafo, 2, 8, 25);

        // Aristas de Mar de Ajó
        grafo.crearArista(grafo, 3, 4, 103);

        // Aristas de Conesa
        grafo.crearArista(grafo, 4, 5, 100);

        // Aristas de Guido
        grafo.crearArista(grafo, 5, 6, 52);

        // Aristas de Las Armas
        grafo.crearArista(grafo, 6, 7, 43);

        // Aristas de Vidal
        grafo.crearArista(grafo, 7, 8, 109);

        new Prim(grafo, cantidadNodos);
    }
}
