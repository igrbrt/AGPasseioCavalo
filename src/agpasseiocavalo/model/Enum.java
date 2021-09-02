/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agpasseiocavalo.model;

/**
 * Enum com os 8 movimentos possiveis de um cavalo no xadrez.
 * @author Igor
 * @since 1.0
 */
public enum Enum {

    ZERO(-1, -2), // decrementa a posicao X em -1 e Y em -2 ...
    UM(1, -2),
    DOIS(-2, 1),
    TRES(2, -1),
    QUATRO(-2, 1),
    CINTO(-1, 2),
    SEIS(1, 2),
    SETE(2, 1);
    
    // coordenadas X e Y no tabuleiro
    private int x;
    private int y;

    private Enum(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
