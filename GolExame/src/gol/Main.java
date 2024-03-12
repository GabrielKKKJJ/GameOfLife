package gol;

/**
 * Esta classe contem o metodo principal do jogo
 */
public class Main {
    /*
     * Este método inicia o jogo, instanciando o renderizador.
     *
     * @param args Os argumentos passados via linha de comando.
     * @throws Exception Se ocorrer um erro durante a execução do jogo.
     */
    public static void main(String[] args) throws Exception {

        Render render = new Render(args);

    }
}