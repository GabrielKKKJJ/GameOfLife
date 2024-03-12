package gol;

import java.util.ArrayList;

/**
 * Esta classe representa as configurações do jogo.
 */
public class Settings {
    private int w, h, g, s;
    private String p;

    /**
     * Obtém a largura do jogo.
     *
     * @return A largura do jogo.
     */
    public int getW() {
        return w;
    }

    /**
     * Obtém a altura do jogo.
     *
     * @return A altura do jogo.
     */
    public int getH() {
        return h;
    }

    /**
     * Obtém o número de gerações do jogo.
     *
     * @return O número de gerações do jogo.
     */
    public int getG() {
        return g;
    }

    /**
     * Obtém a velocidade do jogo.
     *
     * @return A velocidade do jogo.
     */
    public int getS() {
        return s;
    }

    /**
     * Obtém a população inicial do jogo.
     *
     * @return A população inicial do jogo.
     */
    public String getP() {
        return p;
    }

    /**
     * Construtor da classe Settings.
     *
     * @param args Um array de strings contendo os argumentos para o jogo.
     */
    public Settings(String[] args) {
        // Verifica se o jogo foi interrompido devido a falta de argumentos
        isStopped(args);
    }

    /**
     * Verifica se o jogo foi interrompido devido a falta de argumentos.
     *
     * @param args Um array de strings contendo os argumentos para o jogo.
     * @return true se o jogo foi interrompido, false caso contrário.
     */
    public boolean isStopped(String[] args) {
        // Valida os argumentos do jogo
        ValidarArgs validar = new ValidarArgs(args);
        ArrayList<Object> GameArgs = validar.validate();
        // Se os argumentos forem inválidos, o jogo é interrompido
        if (GameArgs == null) {
            return true;
        } else {
            // Configura as definições do jogo
            settings(args);
            return false;
        }
    }

    /**
     * Configura as definições do jogo.
     *
     * @param args Um array de strings contendo os argumentos para o jogo.
     */
    private void settings(String[] args) {
        // Valida os argumentos do jogo
        ValidarArgs validar = new ValidarArgs(args);
        ArrayList<Object> GameArgs = validar.validate();
        // Define as configurações do jogo com base nos argumentos validados
        this.w = (int) GameArgs.get(0);
        this.h = (int) GameArgs.get(1);
        this.g = (int) GameArgs.get(2);
        this.s = (int) GameArgs.get(3);
        this.p = (String) GameArgs.get(4);
    }
}
