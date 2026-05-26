package control;

import entity.*;

public class JogoUC {

    private Jogo jogo;
    private ValidarJogada validarJogada;

    public JogoUC(Jogo jogo) {
        this.jogo = jogo;
        this.validarJogada = new ValidarJogada();
    }

    public String executarJogada(int linha, int coluna) {

        if (!validarJogada.validarPosicao(linha, coluna)) {
            return "Posição inválida";
        }

        if (!jogo.getTabuleiro().posicaoDisponivel(linha, coluna)) {
            return "Posição já ocupada";
        }

        Jogador jogador = jogo.getJogadorAtual();

        jogo.getTabuleiro().registrarJogada(
                linha,
                coluna,
                jogador.getSimbolo()
        );

        if (jogo.getTabuleiro().verificarVitoria(jogador.getSimbolo())) {

            jogo.setStatus(StatusPartida.VITORIA);

            return "Jogador " + jogador.getNome() + " venceu!";
        }

        if (jogo.getTabuleiro().verificarEmpate()) {

            jogo.setStatus(StatusPartida.EMPATE);

            return "A partida empatou!";
        }

        jogo.alternarJogador();

        return "Jogada realizada";
    }

    public void reiniciarPartida() {
        jogo.reiniciar();
    }

    public Jogo getJogo() {
        return jogo;
    }
}
