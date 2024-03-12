package gol;

import java.util.Arrays;
import java.util.Random;

/**
 * Esta classe representa as gerações do jogo.
 */
public class Generations {
    private int[][] grid;
    private final int rows;
    private final int cols;
    private String population;

    /**
     * Construtor da classe Generations.
     *
     * @param args Um array de strings contendo os argumentos para o jogo.
     */
    public Generations(String[] args) {
        // Instancia as configurações do jogo
        Settings settings = new Settings(args);
        this.rows = settings.getW();
        this.cols = settings.getH();
        this.grid = new int[rows][cols];
        // Inicializa a grade com base nas configurações
        grid = parseStringToMatrix(settings.getP());
    }

    /**
     * Converte a representação de string da população em uma matriz de inteiros.
     *
     * @param population A representação de string da população.
     * @return Uma matriz de inteiros representando a população.
     */
    public int[][] parseStringToMatrix(String population) {
        String[] rows = population.split("#");
        int[][] matrix = new int[this.rows][this.cols];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rows.length > i && rows[i].length() > j) {
                    matrix[i][j] = (rows[i].charAt(j) == '1') ? 1 : 0;
                }
            }
        }

        return matrix;
    }

    /**
     * Converte a representação de string da população para uma representação mais legível.
     *
     * @param population A representação de string da população.
     * @return A representação mais legível da população.
     */
    public String population(String population) {
        System.out.println(population.replace('1', 'X').replace('0', '.').replace('#', '\n'));
        return population.replace('1', 'X').replace('0', '.').replace('#', '\n');
    }

    /**
     * Cria uma grade aleatória.
     *
     * @param rows O número de linhas da grade.
     * @param cols O número de colunas da grade.
     * @return A representação de string da grade aleatória.
     */
    public String createRandomGrid(int rows, int cols) {
        var random = new Random();
        StringBuilder grid = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid.append(random.nextBoolean() ? "X" : ".");
            }
            grid.append("\n");
        }

        return grid.toString();
    }

    /**
     * Converte a matriz de inteiros em uma representação de string.
     *
     * @param matrix A matriz de inteiros.
     * @return A representação de string da matriz.
     */
    public static String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (int[] row : matrix) {
            for (int cell : row) {
                sb.append(cell == 1 ? 'X' : '.');
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * Gera a próxima geração do jogo.
     *
     * @return A representação de string da próxima geração.
     */
    public String generatenextGen() {
        int[][] newGrid = new int[rows][cols];

        for (int linha = 0; linha < rows; linha++) {
            for (int coluna = 0; coluna < cols; coluna++) {
                int neighbors = countNeighbors(linha, coluna);

                if (grid[linha][coluna] == 1) {
                    if (neighbors < 2 || neighbors > 3) {
                        newGrid[linha][coluna] = 0;
                    } else {
                        newGrid[linha][coluna] = 1;
                    }
                } else {
                    if (neighbors == 3) {
                        newGrid[linha][coluna] = 1;
                    }
                }
            }
        }
        grid = newGrid;
        for (int i = 0; i < grid.length; i++) {
            System.out.print(Arrays.toString(grid[i]) +"\n");

        }
        System.out.println("geração gerada" + Arrays.deepToString(grid));
        return matrixToString(grid);
    }

    /**
     * Conta o número de vizinhos vivos de uma célula na posição (row, col), considerando uma grade infinitamente espelhada.
     *
     * @param row A posição da linha da célula.
     * @param col A posição da coluna da célula.
     * @return O número de vizinhos vivos.
     */
    public int countNeighbors(int row, int col) {
        int count = 0;
        // Loop pelos vizinhos
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // Calcula o índice real considerando a grade espelhada
                int realRow = (i + rows) % rows;
                int realCol = (j + cols) % cols;
                // Ignora a própria célula
                if (!(realRow == row && realCol == col)) {
                    // Incrementa o contador se a célula vizinha estiver viva
                    if (grid[realRow][realCol] == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
