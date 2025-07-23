package com.bootcamp.forca.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa uma palavra no jogo da forca.
 * Contém a palavra secreta e controla as letras já descobertas.
 */
public class Palavra {
    private String palavraSecreta;
    private Set<Character> letrasDescobertas;
    private String categoria;

    public Palavra(String palavraSecreta, String categoria) {
        this.palavraSecreta = palavraSecreta.toUpperCase();
        this.categoria = categoria;
        this.letrasDescobertas = new HashSet<>();
    }

    /**
     * Adiciona uma letra como descoberta se ela existe na palavra.
     * @param letra A letra a ser verificada
     * @return true se a letra existe na palavra, false caso contrário
     */
    public boolean tentarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        if (palavraSecreta.indexOf(letra) >= 0) {
            letrasDescobertas.add(letra);
            return true;
        }
        return false;
    }

    /**
     * Retorna uma representação da palavra com as letras descobertas
     * e underscores para as letras ainda não descobertas.
     * @return String com a palavra parcialmente revelada
     */
    public String getPalavraParcial() {
        StringBuilder resultado = new StringBuilder();
        for (char c : palavraSecreta.toCharArray()) {
            if (letrasDescobertas.contains(c)) {
                resultado.append(c).append(" ");
            } else {
                resultado.append("_ ");
            }
        }
        return resultado.toString().trim();
    }

    /**
     * Verifica se a palavra foi completamente descoberta.
     * @return true se todas as letras foram descobertas
     */
    public boolean estahCompleta() {
        for (char c : palavraSecreta.toCharArray()) {
            if (!letrasDescobertas.contains(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se uma letra já foi tentada.
     * @param letra A letra a ser verificada
     * @return true se a letra já foi descoberta
     */
    public boolean letraJaTentada(char letra) {
        return letrasDescobertas.contains(Character.toUpperCase(letra));
    }

    // Getters
    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    public String getCategoria() {
        return categoria;
    }

    public Set<Character> getLetrasDescobertas() {
        return new HashSet<>(letrasDescobertas);
    }

    public int getTamanho() {
        return palavraSecreta.length();
    }
}