package control;

public class ValidarJogada {

    public boolean validarPosicao(int linha, int coluna) {

        return linha >= 0 &&
               linha < 3 &&
               coluna >= 0 &&
               coluna < 3;
    }
}
