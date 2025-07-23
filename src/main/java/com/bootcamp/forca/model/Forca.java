package com.bootcamp.forca.model;

/**
 * Classe que representa a forca no jogo.
 * Controla o desenho da forca baseado no número de erros.
 */
public class Forca {
    private int numeroErros;
    private static final int MAX_ERROS = 6;
    
    private static final String[] DESENHOS_FORCA = {
        // 0 erros
        "   +---+\n" +
        "   |   |\n" +
        "       |\n" +
        "       |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        // 1 erro - cabeça
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "       |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        // 2 erros - corpo
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "   |   |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        // 3 erros - braço esquerdo
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "  /|   |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        // 4 erros - braço direito
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "  /|\\  |\n" +
        "       |\n" +
        "       |\n" +
        "=========",
        
        // 5 erros - perna esquerda
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "  /|\\  |\n" +
        "  /    |\n" +
        "       |\n" +
        "=========",
        
        // 6 erros - perna direita (morte)
        "   +---+\n" +
        "   |   |\n" +
        "   O   |\n" +
        "  /|\\  |\n" +
        "  / \\  |\n" +
        "       |\n" +
        "========="
    };

    public Forca() {
        this.numeroErros = 0;
    }

    /**
     * Adiciona um erro à forca.
     */
    public void adicionarErro() {
        if (numeroErros < MAX_ERROS) {
            numeroErros++;
        }
    }

    /**
     * Retorna o desenho atual da forca baseado no número de erros.
     * @return String com o desenho da forca
     */
    public String getDesenho() {
        return DESENHOS_FORCA[numeroErros];
    }

    /**
     * Verifica se o jogador perdeu (máximo de erros atingido).
     * @return true se o jogador perdeu
     */
    public boolean jogadorPerdeu() {
        return numeroErros >= MAX_ERROS;
    }

    /**
     * Retorna o número atual de erros.
     * @return número de erros
     */
    public int getNumeroErros() {
        return numeroErros;
    }

    /**
     * Retorna o número máximo de erros permitidos.
     * @return número máximo de erros
     */
    public int getMaxErros() {
        return MAX_ERROS;
    }

    /**
     * Retorna o número de tentativas restantes.
     * @return tentativas restantes
     */
    public int getTentativasRestantes() {
        return MAX_ERROS - numeroErros;
    }

    /**
     * Reinicia a forca zerando os erros.
     */
    public void reiniciar() {
        this.numeroErros = 0;
    }
}