package gol;

/**
 * Esta classe contém métodos para renderizar o jogo.
 */
public class Render {
    /**
     * Construtor da classe Render.
     *
     * @param args Um array de strings contendo os argumentos para o jogo.
     * @throws Exception Se ocorrer um erro durante o início do jogo.
     */
    public Render(String[] args) throws Exception {
        gameStart(args);
    }

    /**
     * Método responsável por iniciar o jogo.
     *
     * @param args Um array de strings contendo os argumentos para o jogo.
     * @throws Exception Se ocorrer um erro durante o início do jogo.
     */
    public void gameStart(String[] args) throws Exception {
        // Instancia as configurações do jogo
        Settings settings = new Settings(args);

        // Se o jogo não estiver parado por falta de argumentos ou argumentos inválidos
        if (!settings.isStopped(args)) {
            // Inicia as gerações do jogo
            Generations gen = new Generations(args);

            // Cria um gerador de gol
            final GolGenerator generator = new GolGenerator() {
                /**
                 * Obtém a próxima geração como uma string.
                 *
                 * @param generation O número da geração atual.
                 * @return A próxima geração como uma string.
                 */
                @Override
                public String getNextGenerationAsString(long generation) {
                    if (generation == 0 && settings.getP().equalsIgnoreCase("rnd")) {
                        return gen.createRandomGrid(settings.getW(), settings.getH());
                    } else {
                        if (generation == 0) {
                            return gen.population(settings.getP());
                        }
                        return gen.generatenextGen();
                    }
                }
            };

            // Renderiza o jogo usando uma interface gráfica Swing
            SwingRenderer.render(generator, new GolSettings(settings.getH(), settings.getW(), settings.getS(), settings.getG()));
        } else {
            System.out.println("JOGO PARADO POR FALTA DE ARGUMENTOS OU ARGUMENTOS INVÁLIDOS");
        }
    }
}
