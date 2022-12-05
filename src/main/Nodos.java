package main;

public enum Nodos {
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