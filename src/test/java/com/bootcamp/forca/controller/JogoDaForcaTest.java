package com.bootcamp.forca.controller;

import com.bootcamp.forca.model.BancoPalavras;
import com.bootcamp.forca.model.Palavra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe JogoDaForca.
 * Testa a lógica principal do controlador do jogo.
 */
@DisplayName("Testes da classe JogoDaForca")
class JogoDaForcaTest {

    private JogoDaForca jogo;

    @BeforeEach
    void setUp() {
        jogo = new JogoDaForca();
    }

    @Test
    @DisplayName("Deve inicializar jogo corretamente")
    void deveInicializarJogoCorretamente() {
        assertNotNull(jogo.getBancoPalavras());
        assertFalse(jogo.isJogoAtivo());
        assertNull(jogo.getPalavraAtual());
    }

    @Test
    @DisplayName("Banco de palavras deve estar funcional")
    void bancoDepalavrasDeveEstarFuncional() {
        BancoPalavras banco = jogo.getBancoPalavras();
        
        assertNotNull(banco);
        assertEquals(5, banco.getCategorias().size());
        assertTrue(banco.categoriaExiste("ANIMAIS"));
        assertTrue(banco.categoriaExiste("FRUTAS"));
        assertTrue(banco.categoriaExiste("PAISES"));
        assertTrue(banco.categoriaExiste("PROFISSOES"));  
        assertTrue(banco.categoriaExiste("CORES"));
    }

    @Test
    @DisplayName("Deve conseguir obter palavra de categoria específica")
    void deveConseguirObterPalavraDeCategoriaEspecifica() {
        BancoPalavras banco = jogo.getBancoPalavras();
        
        for (String categoria : banco.getCategorias()) {
            assertDoesNotThrow(() -> {
                Palavra palavra = banco.obterPalavraAleatoria(categoria);
                assertNotNull(palavra);
                assertEquals(categoria, palavra.getCategoria());
                assertFalse(palavra.getPalavraSecreta().isEmpty());
            });
        }
    }

    @Test
    @DisplayName("Deve conseguir obter palavra aleatória")
    void deveConseguirObterPalavraAleatoria() {
        BancoPalavras banco = jogo.getBancoPalavras();
        
        assertDoesNotThrow(() -> {
            Palavra palavra = banco.obterPalavraAleatoria();
            assertNotNull(palavra);
            assertFalse(palavra.getPalavraSecreta().isEmpty());
            assertTrue(banco.categoriaExiste(palavra.getCategoria()));
        });
    }

    @Test
    @DisplayName("Estado inicial deve ser consistente")
    void estadoInicialDeveSerConsistente() {
        // Jogo não deve estar ativo inicialmente
        assertFalse(jogo.isJogoAtivo());
        
        // Palavra atual deve ser null inicialmente
        assertNull(jogo.getPalavraAtual());
        
        // Banco de palavras deve estar disponível
        assertNotNull(jogo.getBancoPalavras());
    }

    @Test
    @DisplayName("Deve ter banco de palavras com conteúdo válido")
    void deveTerBancoDePalavrasComConteudoValido() {
        BancoPalavras banco = jogo.getBancoPalavras();
        
        // Verifica se todas as categorias têm palavras válidas
        for (String categoria : banco.getCategorias()) {
            for (int i = 0; i < 3; i++) { // Testa 3 palavras por categoria
                Palavra palavra = banco.obterPalavraAleatoria(categoria);
                
                // Palavra não deve ser null ou vazia
                assertNotNull(palavra);
                assertNotNull(palavra.getPalavraSecreta());
                assertFalse(palavra.getPalavraSecreta().isEmpty());
                
                // Categoria deve estar correta
                assertEquals(categoria, palavra.getCategoria());
                
                // Palavra deve ter tamanho válido
                assertTrue(palavra.getTamanho() > 0);
                assertTrue(palavra.getTamanho() <= 20); // Razoável para jogo da forca
                
                // Palavra deve conter apenas letras maiúsculas
                assertTrue(palavra.getPalavraSecreta().matches("[A-Z]+"));
            }
        }
    }

    @Test
    @DisplayName("Múltiplas instâncias devem funcionar independentemente")
    void multiplasInstanciasDevemFuncionarIndependentemente() {
        JogoDaForca jogo2 = new JogoDaForca();
        
        // Ambos devem ter estado inicial correto
        assertFalse(jogo.isJogoAtivo());
        assertFalse(jogo2.isJogoAtivo());
        
        assertNull(jogo.getPalavraAtual());
        assertNull(jogo2.getPalavraAtual());
        
        // Bancos devem ter mesmo conteúdo mas serem independentes
        assertEquals(jogo.getBancoPalavras().getCategorias(), 
                    jogo2.getBancoPalavras().getCategorias());
    }

    @Test
    @DisplayName("Banco deve suportar categoria com case insensitive")
    void bancoDeveSuportarCategoriaComCaseInsensitive() {
        BancoPalavras banco = jogo.getBancoPalavras();
        
        // Todas essas variações devem funcionar
        assertTrue(banco.categoriaExiste("ANIMAIS"));
        assertTrue(banco.categoriaExiste("animais"));
        assertTrue(banco.categoriaExiste("Animais"));
        assertTrue(banco.categoriaExiste("ANIMAIS"));
        
        // Mas categoria inexistente deve retornar false
        assertFalse(banco.categoriaExiste("CATEGORIA_FALSA"));
        assertFalse(banco.categoriaExiste(""));
    }

    @Test
    @DisplayName("Deve conseguir gerar palavras diferentes")
    void deveConseguirGerarPalavrasDiferentes() {
        BancoPalavras banco = jogo.getBancoPalavras();
        java.util.Set<String> palavrasGeradas = new java.util.HashSet<>();
        
        // Gera 15 palavras aleatórias
        for (int i = 0; i < 15; i++) {
            Palavra palavra = banco.obterPalavraAleatoria();
            palavrasGeradas.add(palavra.getPalavraSecreta());
        }
        
        // Deve ter gerado pelo menos algumas palavras diferentes
        assertTrue(palavrasGeradas.size() > 1, 
            "Deveria gerar pelo menos algumas palavras diferentes");
    }

    @Test
    @DisplayName("Categorias devem ter quantidades adequadas de palavras")
    void categoriasDevemTerQuantidadesAdequadasDePalavras() {
        BancoPalavras banco = jogo.getBancoPalavras();
        
        for (String categoria : banco.getCategorias()) {
            java.util.Set<String> palavrasDaCategoria = new java.util.HashSet<>();
            
            // Tenta gerar 50 palavras da mesma categoria
            for (int i = 0; i < 50; i++) {
                Palavra palavra = banco.obterPalavraAleatoria(categoria);
                palavrasDaCategoria.add(palavra.getPalavraSecreta());
            }
            
            // Cada categoria deve ter pelo menos 5 palavras diferentes
            assertTrue(palavrasDaCategoria.size() >= 5, 
                "Categoria " + categoria + " deve ter pelo menos 5 palavras diferentes");
        }
    }

    @Test
    @DisplayName("Jogo deve funcionar após fechar scanner")
    void jogoDeveFuncionarAposFecharScanner() {
        // Fecha o scanner
        jogo.fecharScanner();
        
        // Banco de palavras ainda deve funcionar
        assertNotNull(jogo.getBancoPalavras());
        assertTrue(jogo.getBancoPalavras().getCategorias().size() > 0);
        
        // Deve conseguir obter palavras
        assertDoesNotThrow(() -> {
            Palavra palavra = jogo.getBancoPalavras().obterPalavraAleatoria();
            assertNotNull(palavra);
        });
    }
}
