package com.bootcamp.forca.model;

import java.util.*;

/**
 * Classe responsável por gerenciar o banco de palavras do jogo.
 * Contém diferentes categorias de palavras para o jogo da forca.
 */
public class BancoPalavras {
    private Map<String, List<String>> categorias;
    private Random random;

    public BancoPalavras() {
        this.random = new Random();
        this.categorias = new HashMap<>();
        inicializarPalavras();
    }

    /**
     * Inicializa o banco de palavras com diferentes categorias.
     */
    private void inicializarPalavras() {
        // Categoria: Animais
        List<String> animais = Arrays.asList(
            "ELEFANTE", "GIRAFA", "LEAO", "TIGRE", "ZEBRA", 
            "MACACO", "PINGUIM", "TUBARAO", "BALEIA", "GOLFINHO",
            "BORBOLETA", "AGUIA", "PAPAGAIO", "COELHO", "CAVALO"
        );
        categorias.put("ANIMAIS", animais);

        // Categoria: Frutas
        List<String> frutas = Arrays.asList(
            "BANANA", "MACA", "LARANJA", "UVA", "MORANGO",
            "ABACAXI", "MANGA", "PERA", "PESSEGO", "MELANCIA",
            "KIWI", "CEREJA", "AMEIXA", "COCO", "LIMAO"
        );
        categorias.put("FRUTAS", frutas);

        // Categoria: Países
        List<String> paises = Arrays.asList(
            "BRASIL", "ARGENTINA", "CHILE", "PERU", "COLOMBIA",
            "FRANCA", "ITALIA", "ESPANHA", "ALEMANHA", "PORTUGAL",
            "JAPAO", "CHINA", "INDIA", "AUSTRALIA", "CANADA"
        );
        categorias.put("PAISES", paises);

        // Categoria: Profissões
        List<String> profissoes = Arrays.asList(
            "MEDICO", "ENGENHEIRO", "PROFESSOR", "ADVOGADO", "ENFERMEIRO",
            "DENTISTA", "ARQUITETO", "JORNALISTA", "PROGRAMADOR", "DESIGNER",
            "BOMBEIRO", "POLICIAL", "VETERINARIO", "PILOTO", "CHEF"
        );
        categorias.put("PROFISSOES", profissoes);

        // Categoria: Cores
        List<String> cores = Arrays.asList(
            "VERMELHO", "AZUL", "VERDE", "AMARELO", "ROXO",
            "LARANJA", "ROSA", "MARROM", "PRETO", "BRANCO",
            "CINZA", "VIOLETA", "TURQUESA", "DOURADO", "PRATEADO"
        );
        categorias.put("CORES", cores);
    }

    /**
     * Retorna uma palavra aleatória de uma categoria específica.
     * @param categoria A categoria desejada
     * @return Objeto Palavra com a palavra escolhida
     */
    public Palavra obterPalavraAleatoria(String categoria) {
        categoria = categoria.toUpperCase();
        if (!categorias.containsKey(categoria)) {
            throw new IllegalArgumentException("Categoria não encontrada: " + categoria);
        }
        
        List<String> palavrasDaCategoria = categorias.get(categoria);
        String palavraEscolhida = palavrasDaCategoria.get(random.nextInt(palavrasDaCategoria.size()));
        
        return new Palavra(palavraEscolhida, categoria);
    }

    /**
     * Retorna uma palavra aleatória de qualquer categoria.
     * @return Objeto Palavra com a palavra escolhida
     */
    public Palavra obterPalavraAleatoria() {
        List<String> todasCategorias = new ArrayList<>(categorias.keySet());
        String categoriaEscolhida = todasCategorias.get(random.nextInt(todasCategorias.size()));
        
        return obterPalavraAleatoria(categoriaEscolhida);
    }

    /**
     * Retorna a lista de todas as categorias disponíveis.
     * @return Set com os nomes das categorias
     */
    public Set<String> getCategorias() {
        return new HashSet<>(categorias.keySet());
    }

    /**
     * Verifica se uma categoria existe.
     * @param categoria A categoria a ser verificada
     * @return true se a categoria existe
     */
    public boolean categoriaExiste(String categoria) {
        return categorias.containsKey(categoria.toUpperCase());
    }
}