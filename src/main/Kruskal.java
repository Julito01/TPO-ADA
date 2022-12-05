package main;

import apis.PilaTDA;
import impl.Nodo;
import impl.PilaTF;
import resources.*;

import java.util.ArrayList;

public class Kruskal {
    private static ArrayList<ConjuntoTDA> nodos = new ArrayList();
    private static int cantNodos;
    private static int componentes;
    private static int ultimoIndiceNodo;

    public Kruskal(GrafoTDA grafo, int cantNodos) {
        this.cantNodos = cantNodos;
        kruskal(grafo);
    }

    public static void kruskal(GrafoTDA grafo) {
        for (int i = 0; i < cantNodos; i++) {
            ConjuntoTDA conjunto = new ConjuntoTA();
            conjunto.inicializarConjunto();
            conjunto.agregar(i + 1);
            nodos.add(conjunto);
        }

        componentes = nodos.size();
        ColaPrioridadTDA colaAristas = grafo.aristas();

        boolean buscoConjunto = true;

        while (componentes > 1) {
            int nodoInicio = colaAristas.nodoInicio();
            int nodoDestino = colaAristas.nodoDestino();
            int peso = colaAristas.peso();
            colaAristas.desacolar();

            int indiceNodoInicio = nodoInicio - 1;
            int indiceNodoDestino = nodoDestino - 1;

            ConjuntoTDA conjuntoNodoInicio = nodos.get(indiceNodoInicio);
            ConjuntoTDA conjuntoNodoDestino = nodos.get(indiceNodoDestino);

            int nodoConjunto = conjuntoNodoDestino.elegir();

            if (conjuntoNodoDestino.conjuntoVacio() && !conjuntoNodoInicio.conjuntoVacio() && !conjuntoNodoInicio.pertenece(nodoDestino)) {
                for (int i = 0; i < nodos.size(); i++) {
                    ConjuntoTDA conjunto = nodos.get(i);
                    if (conjunto.pertenece(nodoDestino)) {
                        conjunto.agregar(nodoInicio);
                        mostrarNodos(nodoDestino, nodoInicio, conjuntoNodoInicio);
                        conjuntoNodoInicio.sacar(nodoInicio);
                        buscoConjunto = true;
                        componentes--;
                    }
                }
            } else if (conjuntoNodoInicio.conjuntoVacio() && conjuntoNodoDestino.conjuntoVacio() && !conjuntoNodoInicio.pertenece(nodoDestino)) {
                ConjuntoTDA conjuntoInicio = null;
                ConjuntoTDA conjuntoDestino = getConjunto(nodoDestino);
                boolean encontreConjunto = false;

                for (int i = 0; i < nodos.size(); i++) {
                    ConjuntoTDA conjuntoActual = nodos.get(i);

                    if (!conjuntoActual.conjuntoVacio() && !conjuntoActual.conjuntoVacio() && !encontreConjunto) {
                        if (conjuntoActual.pertenece(nodoInicio) && !conjuntoActual.pertenece(nodoDestino)) {
                            conjuntoInicio = conjuntoActual;
                            encontreConjunto = true;
                        }
                    }
                }

                if (conjuntoInicio != null && conjuntoDestino != null) {
                    mostrarNodos(nodoInicio, nodoDestino, conjuntoDestino);
                    juntarConjuntos(conjuntoDestino, conjuntoInicio);
                    componentes--;
                }
            }

            if (conjuntoNodoInicio.conjuntoVacio() && !buscoConjunto) {
                ConjuntoTDA ultimoConjunto = nodos.get(ultimoIndiceNodo);
                if (!ultimoConjunto.pertenece(nodoConjunto)) {
                    mostrarNodos(nodoDestino, nodoInicio, ultimoConjunto);
                    juntarConjuntos(ultimoConjunto, conjuntoNodoDestino);
//                    conjuntoNodoDestino.sacarNodo(nodoConjunto);
//                    ultimoConjunto.agregar(nodoConjunto);
                    buscoConjunto = false;
                    componentes--;
                }
            }

            if (!(conjuntoNodoInicio.conjuntoVacio()) && !(conjuntoNodoDestino.conjuntoVacio())) {
                if (!conjuntoNodoInicio.pertenece(nodoConjunto)) {
                    mostrarNodos(nodoDestino, nodoInicio, conjuntoNodoInicio);
                    juntarConjuntos(conjuntoNodoInicio, conjuntoNodoDestino);
//                    conjuntoNodoDestino.sacarNodo(nodoConjunto);
//                    conjuntoNodoInicio.agregar(nodoConjunto);
                    buscoConjunto = false;
                    componentes--;
                }
                ultimoIndiceNodo = indiceNodoInicio;
            }

        }

    }

    public static ConjuntoTDA getConjunto(int nodo) {
        ConjuntoTDA conjuntoRetornar = null;

        for (int i = 0; i < nodos.size(); i++) {
            ConjuntoTDA conjunto = nodos.get(i);
            if (!conjunto.conjuntoVacio() &&  conjunto.pertenece(nodo)) {
                conjuntoRetornar = conjunto;
            }
        }

        return conjuntoRetornar;
    }

    public static void juntarConjuntos(ConjuntoTDA primerConjunto, ConjuntoTDA segundoConjunto) {
        while (!segundoConjunto.conjuntoVacio()) {
            int valor = segundoConjunto.elegir();
            primerConjunto.agregar(valor);
            segundoConjunto.sacar(valor);
        }
    }

    public static void mostrarNodos(int nodo1, int nodo2, ConjuntoTDA conjunto) {
        PilaTDA pila = new PilaTF();
        pila.inicializarPila();

        System.out.println();
        System.out.print("Nodo: " + Nodos.getNodo(nodo1 - 1) + " conectado a " + Nodos.getNodo(nodo2 - 1));

        while (!conjunto.conjuntoVacio()) {
            int valor = conjunto.elegirUltimo();

            if (valor != nodo2) {
                System.out.print(" - " + Nodos.getNodo(valor - 1));
            }

            conjunto.sacar(valor);
            pila.apilar(valor);
        }

        while (!pila.pilaVacia()) {
            conjunto.agregar(pila.tope());
            pila.desapilar();
        }
    }
}
