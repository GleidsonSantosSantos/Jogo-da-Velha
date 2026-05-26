package entity;

public class Tabuleiro {

    private Simbolo[][] matriz;

    public Tabuleiro() {
        matriz = new Simbolo[3][3];
    }

    public Simbolo[][] getMatriz() {
        return matriz;
    }

    public boolean posicaoDisponivel(int linha, int coluna) {
        return matriz[linha][coluna] == null;
    }

    public void registrarJogada(int linha, int coluna, Simbolo simbolo) {
        matriz[linha][coluna] = simbolo;
    }

    public boolean verificarVitoria(Simbolo simbolo) {

        for (int i = 0; i < 3; i++) {

            if (matriz[i][0] == simbolo &&
                matriz[i][1] == simbolo &&
                matriz[i][2] == simbolo) {
                return true;
            }

            if (matriz[0][i] == simbolo &&
                matriz[1][i] == simbolo &&
                matriz[2][i] == simbolo) {
                return true;
            }
        }

        return (matriz[0][0] == simbolo &&
                matriz[1][1] == simbolo &&
                matriz[2][2] == simbolo)
                ||
               (matriz[0][2] == simbolo &&
                matriz[1][1] == simbolo &&
                matriz[2][0] == simbolo);
    }

    public boolean verificarEmpate() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (matriz[i][j] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    public void limparTabuleiro() {
        matriz = new Simbolo[3][3];
    }
}
