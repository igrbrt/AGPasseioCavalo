/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agpasseiocavalo.model;

/**
 *
 * @author Igor
 */
public class Cromossomo implements Comparable {

    int[] genes = new int[64]; // quantidade de casas tabuleiro de xadrez
    int aptidao = 0; // fitness

    public Cromossomo() {
    }

    // Gera numero repetido, pesquisar se pode ou nao
    public void geraGenesAleatorio() {
        for (int i = 0; i < 64; i++) {
            this.genes[i] = (int) (Math.random() * 7);
        }
    }

    public void calculaAptidaoCromossomo(int posicaoXAux, int posicaoYAux) {
        Tabuleiro tabuleiro = new Tabuleiro();
        int posicaoX, posicaoY;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro.tabuleiro[i][j] = false;
            }
        }

        tabuleiro.posicaoXCavalo = posicaoXAux;
        tabuleiro.posicaoYCavalo = posicaoYAux;
        // posicao inicial do cavalo
        posicaoX = posicaoXAux;
        posicaoY = posicaoYAux;
        
        tabuleiro.tabuleiro[posicaoX][posicaoY] = true;

        int i = 0;
        do {
            try {
                //TODOS OS MOVIMENTOS POSSIVEIS DO CAVALO
                if (this.genes[i] == 0) {
                    posicaoX += Enum.ZERO.getX();
                    posicaoY += Enum.ZERO.getY();
                } else if (this.genes[i] == 1) {
                    posicaoX += Enum.UM.getX();
                    posicaoY += Enum.UM.getY();
                } else if (this.genes[i] == 2) {
                    posicaoX += Enum.DOIS.getX();
                    posicaoY += Enum.DOIS.getY();
                } else if (this.genes[i] == 3) {
                    posicaoX += Enum.TRES.getX();
                    posicaoY += Enum.TRES.getY();
                } else if (this.genes[i] == 4) {
                    posicaoX += Enum.QUATRO.getX();
                    posicaoY += Enum.QUATRO.getY();
                } else if (this.genes[i] == 5) {
                    posicaoX += Enum.CINTO.getX();
                    posicaoY += Enum.CINTO.getY();
                } else if (this.genes[i] == 6) {
                    posicaoX += Enum.SEIS.getX();
                    posicaoY += Enum.SEIS.getY();
                } else if (this.genes[i] == 7) {
                    posicaoX += Enum.SETE.getX();
                    posicaoY += Enum.SETE.getY();
                }

                // verifica se com o movimento nao vai sair do tabuleiro
                if ((((posicaoX) >= 0 && (posicaoX) < 8)) && (((posicaoY) >= 0) && (posicaoY) < 8)) {

                    // se ainda nao tiver percorrido essa casa no tabuleiro
                    if (tabuleiro.tabuleiro[(posicaoX)][(posicaoY)] == false) {
                        //System.out.println("AFF "+ posicaoX);
                        //System.out.println("AFF "+ posicaoY);
                        tabuleiro.posicaoXCavalo =  posicaoX;
                        tabuleiro.posicaoYCavalo =  posicaoY;
                        this.aptidao++;
                        tabuleiro.tabuleiro[tabuleiro.posicaoXCavalo][tabuleiro.posicaoYCavalo] = true;
                    } else {
                        return;
                    }

                } else {
                    return;
                }

            } catch (Exception e) {
                System.out.println(e);
                System.out.println("ERRO--> "+posicaoX);
                System.out.println("ERRO--> "+posicaoY);
            }
            ++i;
        } while (i < 64);

    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getAptidao() {
        return aptidao;
    }

    public void setAptidao(int aptidao) {
        this.aptidao = aptidao;
    }

    @Override
    public int compareTo(Object o) {
        Cromossomo aux = (Cromossomo) o;
        if (this.aptidao < aux.aptidao) {
            return 1;
        }
        else if (this.aptidao > aux.aptidao) {
                return -1;
        } else {
            return 0;
        }

    }
}
