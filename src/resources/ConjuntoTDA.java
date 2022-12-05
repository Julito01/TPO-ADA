package resources;

public interface ConjuntoTDA {
    void inicializarConjunto();

    void agregar(int var1);

    int size();

    void sacar(int var1);

    boolean conjuntoVacio();

    int elegir();

    int elegirUltimo();

    int elegirRandom();

    boolean pertenece(int var1);
}
