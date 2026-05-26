package boundary;

import control.JogoUC;
import entity.*;

import javax.swing.*;
import java.awt.*;

public class TelaJogo extends JFrame {

    private JButton[][] botoes;
    private JButton botaoReiniciar;
    private JButton botaoStatus;
    private JLabel status;
    private JogoUC jogoUC;

    public TelaJogo(JogoUC jogoUC) {

        this.jogoUC = jogoUC;

        configurarTela();
        criarComponentes();
    }

    private void configurarTela() {

        setTitle("Jogo da Velha");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void criarComponentes() {

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelTabuleiro = new JPanel(new GridLayout(3, 3));

        botoes = new JButton[3][3];

        Font fonte = new Font("Arial", Font.BOLD, 50);

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                JButton botao = new JButton();

                botao.setFont(fonte);

                final int linha = i;
                final int coluna = j;

                botao.addActionListener(e -> realizarJogada(linha, coluna));

                botoes[i][j] = botao;

                painelTabuleiro.add(botao);
            }
        }

        status = new JLabel(
                "Vez do jogador: " +
                jogoUC.getJogo().getJogadorAtual().getNome(),
                SwingConstants.CENTER
        );

        status.setFont(new Font("Arial", Font.BOLD, 18));

        botaoReiniciar = new JButton("Reiniciar Partida");

        botaoReiniciar.setEnabled(false);

        botaoReiniciar.addActionListener(e -> reiniciarPartida());

        botaoStatus = new JButton("Verificar Status");

        botaoStatus.addActionListener(e -> verificarStatusPartida());

        JPanel painelBotoes = new JPanel();

        painelBotoes.add(botaoStatus);
        painelBotoes.add(botaoReiniciar);

        painelPrincipal.add(status, BorderLayout.NORTH);
        painelPrincipal.add(painelTabuleiro, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void realizarJogada(int linha, int coluna) {

        if (!botoes[linha][coluna].getText().equals("")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Posição já ocupada! Escolha outra posição."
            );

            return;
        }

        String retorno = jogoUC.executarJogada(linha, coluna);

        atualizarTela();

        if (jogoUC.getJogo().getStatus() == StatusPartida.VITORIA) {

            JOptionPane.showMessageDialog(this, retorno);

            desabilitarTabuleiro();

            botaoReiniciar.setEnabled(true);

            return;
        }

        if (jogoUC.getJogo().getStatus() == StatusPartida.EMPATE) {

            JOptionPane.showMessageDialog(
                    this,
                    "A partida empatou!"
            );

            botaoReiniciar.setEnabled(true);

            return;
        }

        status.setText(
                "Vez do jogador: " +
                jogoUC.getJogo().getJogadorAtual().getNome()
        );
    }

    private void atualizarTela() {

        Simbolo[][] matriz = jogoUC.getJogo()
                .getTabuleiro()
                .getMatriz();

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (matriz[i][j] != null) {

                    botoes[i][j].setText(matriz[i][j].toString());
                }
            }
        }
    }

    private void desabilitarTabuleiro() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                botoes[i][j].setEnabled(false);
            }
        }
    }

    private void reiniciarPartida() {

        jogoUC.reiniciarPartida();

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                botoes[i][j].setText("");
                botoes[i][j].setEnabled(true);
            }
        }

        status.setText(
                "Vez do jogador: " +
                jogoUC.getJogo().getJogadorAtual().getNome()
        );

        botaoReiniciar.setEnabled(false);
    }

    private void verificarStatusPartida() {

        StatusPartida statusAtual = jogoUC.getJogo().getStatus();

        String mensagem = "";

        switch (statusAtual) {

            case EM_ANDAMENTO:
                mensagem = "A partida está em andamento!";
                break;

            case EMPATE:
                mensagem = "A partida terminou em empate!";
                break;

            case VITORIA:
                mensagem = "A partida já possui um vencedor!";
                break;
        }

        JOptionPane.showMessageDialog(this, mensagem);
    }
}
