package entity;

public class Jogo {

    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;
    private Tabuleiro tabuleiro;
    private StatusPartida status;

    public Jogo(Jogador jogador1, Jogador jogador2) {

        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogadorAtual = jogador1;
        this.tabuleiro = new Tabuleiro();
        this.status = StatusPartida.EM_ANDAMENTO;
    }

    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public StatusPartida getStatus() {
        return status;
    }

    public void setStatus(StatusPartida status) {
        this.status = status;
    }

    public void alternarJogador() {

        jogadorAtual = (jogadorAtual == jogador1)
                ? jogador2
                : jogador1;
    }

    public void reiniciar() {

        tabuleiro.limparTabuleiro();
        status = StatusPartida.EM_ANDAMENTO;
        jogadorAtual = jogador1;
    }
}
