package main;

import java.util.ArrayList;
import java.util.Random;

import resources.*;

enum Nodos {
    MADARIAGA,
    PINAMAR,
    MAR_DE_AJO,
    CONESA,
    GUIDO,
    LAS_ARMAS,
    CORONEL_VIDAL,
    VILLA_GESELL;

    private static Nodos[] list = Nodos.values();

    public static Nodos getNodo(int i ) {
        return list[i];
    }
}

public class Prim {
    private static ArrayList<Integer> nodosConectados = new ArrayList();
    private static ArrayList<Integer> nodosNoConectados = new ArrayList();
    private static int cantNodos;
    private static int nodoAnterior;
    private static int nodoActual;
    private static boolean primeraVez = true;

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
        crearArista(grafo, 1, 7, 111);
        crearArista(grafo, 1, 8, 32);
        crearArista(grafo, 1, 3, 78);
        crearArista(grafo, 1, 5, 120);

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

    private static ArrayList prim(GrafoTDA grafo) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(cantNodos);
        for (int i = 0; i < cantNodos; i++) {
            nodosNoConectados.add(i + 1);
        }

        int nodo = nodosNoConectados.get(randomIndex);
        nodosConectados.add(nodo);
        nodoActual = nodo;
        ArrayList<Integer> menorCosto = armarArregloMenorCosto(grafo, nodoActual);
        ArrayList<Integer> masCercano = armarArregloMasCercano(nodoActual);

        System.out.println("Nodo inicial: " + Nodos.getNodo(nodoActual -  1));
        System.out.println("Nodos agregados en orden:");

        for (int i = 0; i < cantNodos - 1; i++) {
            int nodoMenorCosto = elegirMenorCosto(menorCosto);

            if (primeraVez) {
                System.out.println("Nodo: " + Nodos.getNodo(nodoActual - 1) + " conectado a " + Nodos.getNodo(nodoMenorCosto - 1));
                masCercano.set(nodoActual, nodoMenorCosto);
                primeraVez = false;
            } else {
                System.out.println("Nodo: " + Nodos.getNodo(masCercano.get(nodoMenorCosto) - 1) + " conectado a " + Nodos.getNodo(nodoMenorCosto - 1));
            }

            nodosConectados.add(nodoMenorCosto);
            nodoAnterior = nodoActual;
            nodoActual = nodoMenorCosto;
            actualizarArregloMenorCosto(grafo, menorCosto, masCercano);
        }

        return nodosConectados;
    }

    private static void actualizarArregloMenorCosto(GrafoTDA grafo, ArrayList<Integer> costos, ArrayList<Integer> masCercano) {
        for (int i = 1; i < costos.size(); i++) {
            int costo = costos.get(i);

            // Si no es un peso que ya se utilizó, tengo que chequear el menor peso entre el último nodo agregado
            // y el último nodo más cercano - Utilizo -2 para marcar los pesos ya utilizados y -1 para marcar los pesos infinitos
            if (costo != -2) {
                int nodoVerificar = i;
                int peso = grafo.pesoArista(nodoActual, nodoVerificar);

                if (peso > 0 && !(nodosConectados.contains(nodoVerificar))) {
                    if (costo == -1) {
                        costos.set(i, peso);
                        actualizarArregloMasCercano(masCercano, nodoActual, nodoVerificar);
                    }

                    if (peso < costo) {
                        costos.set(i, peso);
                        actualizarArregloMasCercano(masCercano, nodoActual, nodoVerificar);
                    }
                }
            }
        }
    }

    private static void actualizarArregloMasCercano(ArrayList<Integer> cercano, int nodoActual, int nodoVerificar) {
        cercano.set(nodoVerificar, nodoActual);
    }

    private static ArrayList armarArregloMenorCosto(GrafoTDA grafo, int nodoActual) {
        ArrayList<Integer> menorCosto = new ArrayList();

        for (int i = 1; i <= nodosNoConectados.size(); i++) {

            if (i - 1 == 0) {
                menorCosto.add(0);
            }

            int nodo = nodosNoConectados.get(i - 1);
            if (grafo.existeArista(nodoActual, nodo)) {
                menorCosto.add(grafo.pesoArista(nodoActual, nodo));
            } else {
                menorCosto.add(new Integer(-1));
            }
        }

        return menorCosto;
    }

    private static ArrayList armarArregloMasCercano(int nodoActual) {
        ArrayList<Integer> masCercano = new ArrayList();

        for (int i = 1; i <= cantNodos; i++) {
            if (i - 1 == 0) {
                masCercano.add(0);
            }

            masCercano.add(nodoActual);
        }

        return masCercano;
    }

    private static void crearVertice(GrafoTDA grafo, int nodo) {
        grafo.agregarVertice(nodo);
        cantNodos++;
    }

    private static void crearArista(GrafoTDA grafo, int nodo1, int nodo2, int peso) {
        grafo.agregarArista(nodo1, nodo2, peso);
        grafo.agregarArista(nodo2, nodo1, peso);
    }

    private static int elegirMenorCosto(ArrayList<Integer> menorCosto) {
        int elementoMenor = Integer.MAX_VALUE;

        for (int i = 1; i < menorCosto.size(); i++) {
            int costo = menorCosto.get(i);
            if (costo > 0) {
                if (costo < elementoMenor) {
                    elementoMenor = costo;
                }
            }
        }

        int indexMenorCosto = menorCosto.indexOf(elementoMenor);
        menorCosto.set(indexMenorCosto, -2);
        return indexMenorCosto;
    }
}
