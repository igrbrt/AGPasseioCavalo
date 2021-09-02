/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agpasseiocavalo.model;

// LOW BALANCE NETWORK
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Igor
 */
public class Populacao {

    Cromossomo[] populacao;
    Cromossomo[] filhos;
    int tamanho, tamanhoFilhos, taxaMutacao, numCruzamentos;

    public Populacao(int tam, int cruzamentos) {
        this.tamanho = tam;
        this.populacao = new Cromossomo[tam];
        this.tamanhoFilhos = cruzamentos * 2;
        this.numCruzamentos = cruzamentos;
        this.filhos = new Cromossomo[this.tamanhoFilhos];
        //inicializarPopulacao();
    }

    public Populacao() {
    }

    public void inicializarPopulacao() {
        for (int i = 0; i < this.populacao.length; i++) {
            this.populacao[i] = new Cromossomo();
            //this.filhos[i] = new Cromossomo();
            this.populacao[i].geraGenesAleatorio();
        }
        //System.out.println(this.populacao[0].aptidao);
//        for (int i = 0; i < this.filhos.length; i++) {
//            this.filhos[i] = new Cromossomo();
//            //this.filhos[i] = new Cromossomo();
//            //this.filhos[i].geraGenesAleatorio();
//        }
        //System.out.println(this.filhos[0].aptidao);
    }

    public void calculaAptidao(Cromossomo[] umaPopulacao, int posicaoX, int posicaoY) {
        for (int i = 0; i < umaPopulacao.length; i++) {
            umaPopulacao[i].calculaAptidaoCromossomo(posicaoX, posicaoY);
        }
    }

    public Cromossomo[] ordena(Cromossomo[] umaPopulacao) {
        List<Cromossomo> aux;
        aux = new ArrayList<>();
        aux = Arrays.asList(umaPopulacao);
        Collections.sort(aux);
        umaPopulacao = (Cromossomo[]) aux.toArray();
        return umaPopulacao;
    }

    public int roleta() {
        double aptidoesTotal = 0.0;
        HashMap<Integer, Cromossomo> roleta = new HashMap<Integer, Cromossomo>();
        //atribuindo fitness(64, maximo) aleatorio para teste
        Random rand = new Random();
        for (int i = 0; i < tamanho; i++) {
            //populacao[i].setAptidao(rand.nextInt(64));
            roleta.put(i, populacao[i]);   // adicionando os elementos na roleta
            aptidoesTotal += populacao[i].getAptidao();
            //      System.out.println("aptidao de " + i + ":" +  populacao.get(i).getAptidao());
        }
        //System.out.println("soma total de aptidoes: " + aptidoesTotal);
        //calculando porcentagem de cada aptidao, armazena num vetor de aptidoes
        //onde cada posicao é respectiva à posicao do cromosso no vetor populacao
        int porcentagemApt[] = new int[tamanho];

        for (int i = 0; i < porcentagemApt.length; i++) {
            int porcentagem = (int) ((100 * populacao[i].getAptidao()) / aptidoesTotal);
            porcentagemApt[i] = porcentagem;
        }
        //adicionando fatias na roleta, aumentando a probabilidade de certos elementos no sorteio
        int j = 0;
        while (j < porcentagemApt.length) {
            //calculo no numero de fatias a adicionar na roleta
            int fatias = ((tamanho * porcentagemApt[j]) / 100);
            //adicionando as fatias (replicação de cromossomos de acordo com numero de fatias)
            for (int i = 0; i < fatias; i++) {
                Cromossomo umCromossomo = new Cromossomo();
                roleta.put(j, umCromossomo);
            }
            j++;
        }
        //sorteio do cromossomo na roleta
        int indexSorteado = rand.nextInt(roleta.size());
        return indexSorteado;
    }

    public void mutacao() {
        int qtd = this.taxaMutacao;
        int cromossomoSortado, indiceSorteado, valor;
        boolean flag;
        while (qtd > 0) {
            cromossomoSortado = (int) (Math.random() * this.tamanhoFilhos - 1); // tamanho da populacao e eh 0 tam-1
            indiceSorteado = (int) (Math.random() * 63); // tamanho do cromossomo eh de 0 a 63
            valor = (int) (Math.random() * 7); // opcoes de movimento eh de 0 a 7

            flag = true;
            do {
                // pra ter certeza que o valor sorteado nao vai ser IGUAL ao ja existente
                if (this.filhos[cromossomoSortado].getGenes()[indiceSorteado] == valor) {
                    valor = (int) (Math.random() * 7); // opcoes de movimento eh de 0 a 7
                } else {
                    this.filhos[cromossomoSortado].getGenes()[indiceSorteado] = valor; // alterando um gene do array de genes do cromossomo
                    flag = false;
                }
            } while (flag);
            --qtd;
        }
    }

    public void cruzamento() {
        int nCruzamentos = this.numCruzamentos;
        int posicao = 0;

        //loop dos cruzamentos
        for (int i = 0; i < nCruzamentos; i++) {
            int rand1, rand2;
            // sorteia dois numeros aleatorios correspondentes aos pais a serem escolhidos
            do {
                rand1 = roleta(); //precisa disso para poder pegar apenas os melhores pais
                rand2 = roleta();
            } while (rand1 == rand2);

            Cromossomo pai1 = this.populacao[rand1];
            Cromossomo pai2 = this.populacao[rand2];

            Cromossomo filho1 = new Cromossomo();
            Cromossomo filho2 = new Cromossomo();

            int meio = pai1.genes.length / 2;
            int fim = pai1.genes.length;

            //teste:
//            for (int j = 0; j < fim; j++) {
//                System.out.print(pai1.getGenes()[j] + " ");
//            }
//            System.out.print("\n");
//            for (int j = 0; j < fim; j++) {
//                System.out.print(pai2.getGenes()[j] + " ");
//            }
//            System.out.print("\n");
            //um ponto de cruzamento
            for (int j = 0; j < meio; j++) {
                filho1.getGenes()[j] = pai1.getGenes()[j];
                filho2.getGenes()[j] = pai2.getGenes()[j];
            }
            //System.out.print("\n");
            for (int k = meio; k < fim; k++) {
                filho1.getGenes()[k] = pai2.getGenes()[k];
                filho2.getGenes()[k] = pai1.getGenes()[k];
            }
//            System.out.print("\n");
//            //teste
//            for (int j = 0; j < fim; j++) {
//                System.out.print(filho1.getGenes()[j] + " ");
//            }
//            System.out.print("\n");
//            for (int j = 0; j < fim; j++) {
//                System.out.print(filho2.getGenes()[j] + " ");
//            }
//            System.out.print("\n");

            //adiciona filhos
            filho1.setAptidao(-1);
            filho2.setAptidao(-1);
            //if(filhos[posicao] == null){

            this.filhos[posicao] = new Cromossomo();
            //}
            this.filhos[posicao] = filho1;
            ++posicao;
            //if(filhos[posicao] == null){
            this.filhos[posicao] = new Cromossomo();
            //}
            this.filhos[posicao] = filho2;
            ++posicao;
        }
    }

    public void selecao() {
        int tam = 0;
        for (int i = 0; i < this.tamanhoFilhos; i++) {
            if (filhos[i] != null) {
                ++tam;
            } else {
                break;
            }
        }
        Cromossomo[] selecao = new Cromossomo[this.tamanho + tam];


        this.populacao = ordena(this.populacao);
        this.filhos = ordena(this.filhos);

        //System.out.print("POP ");
//        for (int i = 0; i < this.populacao.length; i++) {
//            System.out.print(populacao[i].aptidao + " ");
//        }

       // System.out.println("");
       // System.out.print("FIL ");
//        for (int i = 0; i < tam; i++) {
//            System.out.print(filhos[i].aptidao + " ");
//        }
       // System.out.println("");
        // joga a populacao e os filhos gerados num array so
        System.arraycopy(this.populacao, 0, selecao, 0, this.tamanho);

//        for (int i = 0; i < this.populacao.length; i++) {
//            for (int j = 0; j < tam; j++) {
//                if(this.populacao[i].aptidao > this.filhos[j]);
//            }
//        }


        System.arraycopy(this.filhos, 0, selecao, this.tamanho, tam);
        // ordena eles
        selecao = ordena(selecao);

        //pega os 100 primeiros
        System.arraycopy(selecao, 0, this.populacao, 0, this.tamanho);

    }

    public Cromossomo[] getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Cromossomo[] populacao) {
        this.populacao = populacao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getTaxaMutacao() {
        return taxaMutacao;
    }

    public void setTaxaMutacao(int taxaMutacao) {
        this.taxaMutacao = taxaMutacao;
    }

    public Cromossomo[] getFilhos() {
        return filhos;
    }

    public void setFilhos(Cromossomo[] filhos) {
        this.filhos = filhos;
    }

    public int getNumCruzamentos() {
        return numCruzamentos;
    }

    public void setNumCruzamentos(int numCruzamentos) {
        this.numCruzamentos = numCruzamentos;
    }
}
