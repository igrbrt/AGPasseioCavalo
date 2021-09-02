/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agpasseiocavalo.model;

import java.util.Arrays;

/**
 * Classe representando um tabuleiro de Xadrez, é boleano pra controlar por onde
 * o Cavalo ja andou
 *
 * @author Igor
 * @since 1.0
 */
public class Tabuleiro {

    boolean[][] tabuleiro = new boolean[8][8];
    int posicaoXCavalo;
    int posicaoYCavalo;
    int posicaoXInicial;
    int posicaoYInicial;

    public Tabuleiro(int posicaoXCavalo, int posicaoYCavalo) {
        this.posicaoXCavalo = posicaoXCavalo;
        this.posicaoYCavalo = posicaoYCavalo;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.tabuleiro[i][j] = false;
            }
        }
    }

    public Tabuleiro() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.tabuleiro[i][j] = false;
            }
        }
    }

    public boolean[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(boolean[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int getPosicaoXCavalo() {
        return posicaoXCavalo;
    }

    public void setPosicaoXCavalo(int posicaoXCavalo) {
        this.posicaoXCavalo = posicaoXCavalo;
    }

    public int getPosicaoYCavalo() {
        return posicaoYCavalo;
    }

    public void setPosicaoYCavalo(int posicaoYCavalo) {
        this.posicaoYCavalo = posicaoYCavalo;
    }

    public int getPosicaoXInicial() {
        return posicaoXInicial;
    }

    public void setPosicaoXInicial(int posicaoXInicial) {
        this.posicaoXInicial = posicaoXInicial;
    }

    public int getPosicaoYInicial() {
        return posicaoYInicial;
    }

    public void setPosicaoYInicial(int posicaoYInicial) {
        this.posicaoYInicial = posicaoYInicial;
    }
}
