import boundary.TelaJogo;
import control.JogoUC;
import entity.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        String[] opcoes = {"X", "O"};

        String simboloEscolhido = (String) JOptionPane.showInputDialog(
                null,
                "Escolha o símbolo do Player 1:",
                "Escolha de Símbolo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (simboloEscolhido == null) {
            System.exit(0);
        }

        Simbolo simboloJogador1 =
                simboloEscolhido.equals("X") ? Simbolo.X : Simbolo.O;

        Simbolo simboloJogador2 =
                simboloJogador1 == Simbolo.X ? Simbolo.O : Simbolo.X;

        String nomeJogador1 = JOptionPane.showInputDialog(
                null,
                "Digite o nome do Player 1:"
        );

        if (nomeJogador1 == null || nomeJogador1.isEmpty()) {
            nomeJogador1 = "Player 1";
        }

        String nomeJogador2 = JOptionPane.showInputDialog(
                null,
                "Digite o nome do Player 2:"
        );

        if (nomeJogador2 == null || nomeJogador2.isEmpty()) {
            nomeJogador2 = "Player 2";
        }

        Jogador jogador1 = new Jogador(nomeJogador1, simboloJogador1);
        Jogador jogador2 = new Jogador(nomeJogador2, simboloJogador2);

        Jogo jogo = new Jogo(jogador1, jogador2);

        JogoUC jogoUC = new JogoUC(jogo);

        SwingUtilities.invokeLater(() -> {

            TelaJogo tela = new TelaJogo(jogoUC);
            tela.setVisible(true);
        });
    }
}
