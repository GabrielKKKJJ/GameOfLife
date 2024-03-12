
package gol;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta classe é responsável por validar os argumentos passados para o jogo.
 */
public class ValidarArgs {
    private String[] args;

    /**
     * Construtor da classe ValidarArgs.
     *
     * @param args Um array de strings contendo os argumentos para o jogo.
     */
    public ValidarArgs(String[] args) {
        this.args = args;
    }

    /**
     * Valida os argumentos passados para o jogo.
     *
     * @return Uma lista contendo os argumentos validados, ou null se os argumentos forem inválidos.
     */
    public ArrayList<Object> validate() {
        if (args.length == 0) { // Se não houver nenhum parâmetro no CLI
            System.out.println("Insira argumentos !");
        } else {
            boolean[] temItem = validarSeTemItems(args);
            String[] numeros = separarNumeros(args, temItem);
            String p = separarString(args);
            ArrayList<Object> validarItem = validarItem(numeros, p, temItem); // Valida os itens dos parâmetros
            return validarSettings(validarItem);
        }
        return null;
    }

    // Valida se as variáveis estão vazias ou não
    private boolean[] validarSeTemItems(String[] args) {
        boolean[] temItem = new boolean[]{true, true, true, true, true};
        int pos = -1;
        for (String arg : args) {
            pos++; // posição do char
            for (int i = 0; i < arg.length(); i++) {
                char charAtual = arg.charAt(i);
                if (charAtual == 'w' || charAtual == 'h' || charAtual == 'g' || charAtual == 's' || charAtual == 'p') {
                    if (arg.length() <= 2) {
                        temItem[pos] = false;
                    }
                }
            }
        }
        return temItem;
    }

    // Separa as variáveis do tipo INT
    private String[] separarNumeros(String[] args, boolean[] temItens) {
        String numW = "";
        String numH = "";
        String numG = "";
        String numS = "";
        String[] valores = new String[]{"W", "H", "G", "S"};
        for (String arg : args) {
            for (int i = 0; i < arg.length(); i++) {
                char charAtual = arg.charAt(i);
                if (charAtual == 'w' && temItens[0]) {
                    for (int j = i + 2; j < arg.length(); j++) {
                        numW += Character.toString(arg.charAt(j));
                    }
                    valores[0] = numW;
                } else if (charAtual == 'h' && temItens[1]) {
                    for (int j = i + 2; j < arg.length(); j++) {
                        numH += Character.toString(arg.charAt(j));
                    }
                    valores[1] = numH;
                } else if (charAtual == 'g' && temItens[2]) {
                    for (int j = i + 2; j < arg.length(); j++) {
                        numG += Character.toString(arg.charAt(j));
                    }
                    valores[2] = numG;
                } else if (charAtual == 's' && temItens[3]) {
                    for (int j = i + 2; j < arg.length(); j++) {
                        numS += Character.toString(arg.charAt(j));
                    }
                    valores[3] = numS;
                }
            }
        }
        return valores;
    }

    // Valida a variável P
    private String validarP(String p) {
        boolean validChar = false;
        int numeros = 0;
        int matrixTotalLines = 1;
        String pIs = "Invalido";
        System.out.println(p);
        if (p.equalsIgnoreCase("rnd")) {
            validChar = true;
            pIs = "Valido";
            System.out.println("a");
            return pIs;
        }
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '0' || p.charAt(i) == '1' || p.charAt(i) == '#') {
                validChar = true;
                pIs = "Valido";
                return pIs;
            } else {
                pIs = "Invalido";
                return pIs;
            }
        }
        System.out.println(pIs);
        return pIs;
    }

    // Separa a variável P
    private String separarString(String[] args) {
        String P = "";

        for (String arg : args) {
            for (int i = 0; i < arg.length(); i++) {
                char charAtual = arg.charAt(i);
                if (charAtual == 'p') {
                    for (int j = i + 2; j < arg.length(); j++) {
                        P += Character.toString(arg.charAt(j));
                    }
                    return P;
                }
            }
        }
        return "ERRO";
    }

    // Faz a validação final de cada item para ver se é VALIDO, INVALIDO OU NÃO PRESENTE
    private ArrayList<Object> validarItem(String[] numeros, String p, boolean[] temEm) {
        ArrayList<Object> toValidarSetting = new ArrayList<>();
        if (temEm[0]) {
            int w = Integer.parseInt(numeros[0]);
            if (w == 10 || w == 20 || w == 40 || w == 80) {
                System.out.println("width = " + "[" + w + "]");
                toValidarSetting.add(w);
            } else {
                System.out.println("width = [invalido]");
                toValidarSetting.clear();
                return null;
            }
        } else {
            System.out.println("width = [Não presente]");
            toValidarSetting.clear();
            return null;
        }

        if (temEm[1]) {
            int h = Integer.parseInt(numeros[1]);
            if (h == 10 || h == 20 || h == 40) {
                System.out.println("height = " + "[" + h + "]");
                toValidarSetting.add(h);
            } else {
                System.out.println("height = [invalido]");
                toValidarSetting.clear();
                return null;
            }
        } else {
            System.out.println("height = [Não presente]");
            toValidarSetting.clear();
            return null;
        }

        if (temEm[2]) {
            int g = Integer.parseInt(numeros[2]);
            if (g >= 0) {
                System.out.println("gerações = " + "[" + g + "]");
                toValidarSetting.add(g);
            } else {
                System.out.println("gerações = [invalido]");
                toValidarSetting.clear();
                return null;
            }
        } else {
            System.out.println("gerações = [Não presente]");
            toValidarSetting.clear();
            return null;
        }

        if (temEm[3]) {
            int s = Integer.parseInt(numeros[3]);


            if (s >= 250 && s <= 1000) {
                System.out.println("speed = " + "[" + s + "]");
                toValidarSetting.add(s);
            } else {
                System.out.println("speed = [invalido]");
                toValidarSetting.clear();
                return null;
            }
        } else {
            System.out.println("speed = [Não presente]");
            toValidarSetting.clear();
        }

        if (temEm[4]) {
            if (Objects.equals(validarP(p), "Valido")) {
                System.out.println("population = " + "['" + p + "']");
                toValidarSetting.add(p);
            } else {
                System.out.println("population = [invalido]");
                return null;
            }
        } else {
            System.out.println("population = [Não presente]");
            return null;
        }
        return toValidarSetting;
    }

    // Valida as configurações do jogo
    private ArrayList<Object> validarSettings(ArrayList<Object> settings) {
        if (settings == null) {
            System.out.println("Jogo impossibilitado de rodar pois falta argumentos na CLI");
            return settings;
        }
        if (settings.size() < 4) {
            System.out.println("Jogo impossibilitado de rodar pois falta argumentos na CLI");
            settings.clear();
            return settings;
        } else {
            return settings;
        }

    }
}
