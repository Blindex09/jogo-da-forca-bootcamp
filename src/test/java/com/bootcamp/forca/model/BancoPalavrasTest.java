package com.bootcamp.forca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe BancoPalavras.
 * Valida o gerenciamento de palavras e categorias.
 */
@DisplayName("Testes da classe BancoPalavras")
class BancoPalavrasTest {

    private BancoPalavras bancoPalavras;

    @BeforeEach
    void setUp() {
        bancoPalavras = new BancoPalavras();
    }

    @Test
    @DisplayName("Deve inicializar com categorias corretas")
    void deveInicializarComCategoriasCorretas() {
        Set<String> categorias = bancoPalavras.getCategorias();
        
        assertEquals(5, categorias.size());
        assertTrue(categorias.contains("ANIMAIS"));
        assertTrue(categorias.contains("FRUTAS"));
        assertTrue(categorias.contains("PAISES"));
        assertTrue(categorias.contains("PROFISSOES"));
        assertTrue(categorias.contains("CORES"));
    }

    @Test
    @DisplayName("Deve verificar se categoria existe corretamente")
    void deveVerificarSeCategoriaExisteCorretamente() {
        assertTrue(bancoPalavras.categoriaExiste("ANIMAIS"));
        assertTrue(bancoPalavras.categoriaExiste("animais")); // minúscula
        assertTrue(bancoPalavras.categoriaExiste("Frutas")); // mista
        
        assertFalse(bancoPalavras.categoriaExiste("CATEGORIA_INEXISTENTE"));
        assertFalse(bancoPalavras.categoriaExiste(""));
    }

    @Test
    @DisplayName("Deve obter palavra aleatória de categoria específica")
    void deveObterPalavraAleatoriaDeCategoriaEspecifica() {
        Palavra palavra = bancoPalavras.obterPalavraAleatoria("ANIMAIS");
        
        assertNotNull(palavra);
        assertEquals("ANIMAIS", palavra.getCategoria());
        assertFalse(palavra.getPalavraSecreta().isEmpty());
        assertTrue(palavra.getTamanho() > 0);
    }

    @Test
    @DisplayName("Deve aceitar categoria em minúscula")
    void deveAceitarCategoriaEmMinuscula() {
        Palavra palavra = bancoPalavras.obterPalavraAleatoria("frutas");
        
        assertNotNull(palavra);
        assertEquals("FRUTAS", palavra.getCategoria());
    }

    @Test
    @DisplayName("Deve lançar exceção para categoria inexistente")
    void deveLancarExcecaoParaCategoriaInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            bancoPalavras.obterPalavraAleatoria("CATEGORIA_FALSA");
        });
    }

    @Test
    @DisplayName("Deve obter palavra aleatória de qualquer categoria")
    void deveObterPalavraAleatoriaDeQualquerCategoria() {
        Palavra palavra = bancoPalavras.obterPalavraAleatoria();
        
        assertNotNull(palavra);
        assertFalse(palavra.getPalavraSecreta().isEmpty());
        assertTrue(palavra.getTamanho() > 0);
        assertTrue(bancoPalavras.categoriaExiste(palavra.getCategoria()));
    }

    @Test
    @DisplayName("Palavras devem estar em maiúscula")
    void palavrasDevemEstarEmMaiuscula() {
        for (String categoria : bancoPalavras.getCategorias()) {
            Palavra palavra = bancoPalavras.obterPalavraAleatoria(categoria);
            String palavraSecreta = palavra.getPalavraSecreta();
            
            // Verifica se está em maiúscula
            assertEquals(palavraSecreta.toUpperCase(), palavraSecreta);
            
            // Verifica se não tem espaços no início/fim
            assertEquals(palavraSecreta.trim(), palavraSecreta);
        }
    }

    @Test
    @DisplayName("Deve gerar palavras diferentes em múltiplas chamadas")
    void deveGerarPalavrasDiferentesEmMultiplasChamadas() {
        Set<String> palavrasGeradas = new java.util.HashSet<>();
        
        // Gera 20 palavras da categoria ANIMAIS
        for (int i = 0; i < 20; i++) {
            Palavra palavra = bancoPalavras.obterPalavraAleatoria("ANIMAIS");
            palavrasGeradas.add(palavra.getPalavraSecreta());
        }
        
        // Deve ter pelo menos algumas palavras diferentes
        // (não pode ser sempre a mesma palavra)
        assertTrue(palavrasGeradas.size() > 1);
    }

    @Test
    @DisplayName("Categorias específicas devem conter palavras válidas")
    void categoriasEspecificasDevemConterPalavrasValidas() {
        // Testando categoria CORES
        Palavra palavraCor = bancoPalavras.obterPalavraAleatoria("CORES");
        String[] coresEsperadas = {"VERMELHO", "AZUL", "VERDE", "AMARELO", "ROXO", 
                                  "LARANJA", "ROSA", "MARROM", "PRETO", "BRANCO",
                                  "CINZA", "VIOLETA", "TURQUESA", "DOURADO", "PRATEADO"};
        
        boolean corValida = false;
        for (String cor : coresEsperadas) {
            if (cor.equals(palavraCor.getPalavraSecreta())) {
                corValida = true;
                break;
            }
        }
        assertTrue(corValida, "Palavra gerada não está na lista esperada de cores");
    }

    @Test
    @DisplayName("Deve manter consistência entre chamadas")
    void deveManterConsistenciaEntreChamadas() {
        Set<String> categorias1 = bancoPalavras.getCategorias();
        Set<String> categorias2 = bancoPalavras.getCategorias();
        
        assertEquals(categorias1, categorias2);
        assertEquals(5, categorias1.size());
        assertEquals(5, categorias2.size());
    }

    @Test
    @DisplayName("Cada categoria deve ter pelo menos uma palavra")
    void cadaCategoriaDeveTerPeloMenosUmaPalavra() {
        for (String categoria : bancoPalavras.getCategorias()) {
            // Deve conseguir obter pelo menos uma palavra de cada categoria
            assertDoesNotThrow(() -> {
                Palavra palavra = bancoPalavras.obterPalavraAleatoria(categoria);
                assertNotNull(palavra);
                assertFalse(palavra.getPalavraSecreta().isEmpty());
            });
        }
    }

    @Test
    @DisplayName("Palavras não devem conter caracteres especiais")
    void palavrasNaoDevemConterCaracteresEspeciais() {
        for (String categoria : bancoPalavras.getCategorias()) {
            for (int i = 0; i < 5; i++) { // Testa 5 palavras de cada categoria
                Palavra palavra = bancoPalavras.obterPalavraAleatoria(categoria);
                String palavraSecreta = palavra.getPalavraSecreta();
                
                // Deve conter apenas letras maiúsculas
                assertTrue(palavraSecreta.matches("[A-Z]+"), 
                    "Palavra '" + palavraSecreta + "' contém caracteres inválidos");
            }
        }
    }

    @Test
    @DisplayName("Deve funcionar com múltiplas instâncias")
    void deveFuncionarComMultiplasInstancias() {
        BancoPalavras banco1 = new BancoPalavras();
        BancoPalavras banco2 = new BancoPalavras();
        
        assertEquals(banco1.getCategorias(), banco2.getCategorias());
        
        // Ambos devem fornecer palavras válidas
        Palavra palavra1 = banco1.obterPalavraAleatoria("ANIMAIS");
        Palavra palavra2 = banco2.obterPalavraAleatoria("ANIMAIS");
        
        assertNotNull(palavra1);
        assertNotNull(palavra2);
        assertEquals("ANIMAIS", palavra1.getCategoria());
        assertEquals("ANIMAIS", palavra2.getCategoria());
    }
}
