package com.bootcamp.forca;

import com.bootcamp.forca.controller.JogoDaForca;
import com.bootcamp.forca.model.BancoPalavras;
import com.bootcamp.forca.model.Forca;
import com.bootcamp.forca.model.Palavra;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de integração para validar o funcionamento geral do sistema.
 * Simula cenários completos de uso do jogo da forca.
 */
@DisplayName("Testes de Integração - Jogo da Forca")
class IntegrationTest {

    @Test
    @DisplayName("Deve simular um jogo completo com vitória")
    void deveSimularJogoCompletoComVitoria() {
        // Cenário: Simulando jogo completo onde o jogador ganha
        
        // 1. Criando componentes do jogo
        BancoPalavras banco = new BancoPalavras();
        Palavra palavra = new Palavra("JAVA", "TECNOLOGIA");
        Forca forca = new Forca();
        
        // 2. Estado inicial
        assertFalse(palavra.estahCompleta());
        assertFalse(forca.jogadorPerdeu());
        assertEquals(6, forca.getTentativasRestantes());
        
        // 3. Tentativas corretas - descobrindo JAVA
        assertTrue(palavra.tentarLetra('J'));
        assertEquals("J _ _ _", palavra.getPalavraParcial());
        assertFalse(palavra.estahCompleta());
        
        assertTrue(palavra.tentarLetra('A'));
        assertEquals("J A _ A", palavra.getPalavraParcial());
        assertFalse(palavra.estahCompleta());
        
        assertTrue(palavra.tentarLetra('V'));
        assertEquals("J A V A", palavra.getPalavraParcial());
        assertTrue(palavra.estahCompleta()); // GANHOU!
        
        // 4. Verificações finais
        assertFalse(forca.jogadorPerdeu());
        assertEquals(6, forca.getTentativasRestantes()); // Não perdeu tentativas
        assertEquals(3, palavra.getLetrasDescobertas().size()); // J, A, V
    }

    @Test
    @DisplayName("Deve simular um jogo completo com derrota")
    void deveSimularJogoCompletoComDerrota() {
        // Cenário: Simulando jogo onde o jogador perde
        
        Palavra palavra = new Palavra("PYTHON", "LINGUAGEM");
        Forca forca = new Forca();
        
        // Estado inicial
        assertFalse(palavra.estahCompleta());
        assertFalse(forca.jogadorPerdeu());
        
        // Fazendo 6 tentativas erradas consecutivas
        String[] tentativasErradas = {"Z", "Q", "W", "X", "K", "J"};
        
        for (int i = 0; i < tentativasErradas.length; i++) {
            // Letra incorreta
            assertFalse(palavra.tentarLetra(tentativasErradas[i].charAt(0)));
            forca.adicionarErro();
            
            // Verificar estado durante o jogo
            assertEquals(i + 1, forca.getNumeroErros());
            assertEquals(6 - (i + 1), forca.getTentativasRestantes());
            assertFalse(palavra.estahCompleta()); // Palavra ainda não foi descoberta
        }
        
        // Verificações finais - PERDEU
        assertTrue(forca.jogadorPerdeu());
        assertEquals(0, forca.getTentativasRestantes());
        assertFalse(palavra.estahCompleta());
    }

    @Test
    @DisplayName("Deve simular jogo misto com vitória no último momento")
    void deveSimularJogoMistoComVitoriaNoUltimoMomento() {
        // Cenário: Jogador erra 5 vezes mas ganha na última tentativa
        
        Palavra palavra = new Palavra("SOL", "NATUREZA");
        Forca forca = new Forca();
        
        // 5 erros consecutivos
        String[] erros = {"A", "E", "I", "U", "B"};
        for (String erro : erros) {
            assertFalse(palavra.tentarLetra(erro.charAt(0)));
            forca.adicionarErro();
        }
        
        // Estado crítico: 1 tentativa restante
        assertEquals(5, forca.getNumeroErros());
        assertEquals(1, forca.getTentativasRestantes());
        assertFalse(forca.jogadorPerdeu());
        assertFalse(palavra.estahCompleta());
        
        // Descobrindo a palavra na última tentativa
        assertTrue(palavra.tentarLetra('S'));
        assertEquals("S _ _", palavra.getPalavraParcial());
        
        assertTrue(palavra.tentarLetra('O'));
        assertEquals("S O _", palavra.getPalavraParcial());
        
        assertTrue(palavra.tentarLetra('L'));
        assertEquals("S O L", palavra.getPalavraParcial());
        assertTrue(palavra.estahCompleta()); // GANHOU!
        
        // Verificações finais
        assertFalse(forca.jogadorPerdeu()); // Não perdeu
        assertEquals(1, forca.getTentativasRestantes()); // Sobrou 1 tentativa
        assertTrue(palavra.estahCompleta()); // Palavra completa
    }

    @Test
    @DisplayName("Deve validar integração completa dos componentes")
    void deveValidarIntegracaoCompletaDosComponentes() {
        // Teste de integração validando todos os componentes juntos
        
        JogoDaForca jogo = new JogoDaForca();
        BancoPalavras banco = jogo.getBancoPalavras();
        
        // 1. Banco de palavras deve estar funcional
        assertNotNull(banco);
        assertEquals(5, banco.getCategorias().size());
        
        // 2. Deve conseguir obter palavras de todas as categorias
        for (String categoria : banco.getCategorias()) {
            Palavra palavra = banco.obterPalavraAleatoria(categoria);
            
            assertNotNull(palavra);
            assertEquals(categoria, palavra.getCategoria());
            assertTrue(palavra.getTamanho() > 0);
            assertFalse(palavra.estahCompleta());
            
            // 3. Testar integração palavra + forca
            Forca forca = new Forca();
            assertFalse(forca.jogadorPerdeu());
            
            // Simular algumas tentativas
            palavra.tentarLetra('A'); // Pode ou não estar na palavra
            palavra.tentarLetra('E'); // Pode ou não estar na palavra
            
            // Forca ainda deve estar funcionando
            assertTrue(forca.getTentativasRestantes() >= 0);
            assertTrue(forca.getTentativasRestantes() <= 6);
        }
    }

    @Test
    @DisplayName("Deve validar comportamento com palavras de tamanhos diferentes")
    void deveValidarComportamentoComPalavrasDeTamanhosDiferentes() {
        // Testando com palavra muito pequena
        Palavra palavraPequena = new Palavra("A", "VOGAL");
        assertEquals(1, palavraPequena.getTamanho());
        assertEquals("_", palavraPequena.getPalavraParcial());
        
        palavraPequena.tentarLetra('A');
        assertTrue(palavraPequena.estahCompleta());
        assertEquals("A", palavraPequena.getPalavraParcial());
        
        // Testando com palavra média
        Palavra palavraMedia = new Palavra("CASA", "OBJETO");
        assertEquals(4, palavraMedia.getTamanho());
        assertEquals("_ _ _ _", palavraMedia.getPalavraParcial());
        
        // Testando com palavra grande
        Palavra palavraGrande = new Palavra("PROGRAMACAO", "TECNOLOGIA");
        assertEquals(11, palavraGrande.getTamanho());
        assertEquals("_ _ _ _ _ _ _ _ _ _ _", palavraGrande.getPalavraParcial());
        
        // Todas devem funcionar com a forca
        Forca forca = new Forca();
        assertFalse(forca.jogadorPerdeu());
        assertEquals(6, forca.getTentativasRestantes());
    }

    @Test
    @DisplayName("Deve validar reinicialização do sistema")
    void deveValidarReinicializacaoDoSistema() {
        // Simula reinicialização de jogo
        
        Palavra palavra1 = new Palavra("TESTE", "CATEGORIA");
        Forca forca = new Forca();
        
        // Simula uso do sistema
        palavra1.tentarLetra('T');
        palavra1.tentarLetra('E');
        forca.adicionarErro();
        forca.adicionarErro();
        
        // Estado modificado
        assertEquals(2, palavra1.getLetrasDescobertas().size());
        assertEquals(2, forca.getNumeroErros());
        
        // Reinicialização
        forca.reiniciar();
        Palavra palavra2 = new Palavra("NOVO", "CATEGORIA");
        
        // Verifica se reinicializou corretamente
        assertEquals(0, forca.getNumeroErros());
        assertEquals(6, forca.getTentativasRestantes());
        assertFalse(forca.jogadorPerdeu());
        
        assertEquals(0, palavra2.getLetrasDescobertas().size());
        assertFalse(palavra2.estahCompleta());
        assertEquals("_ _ _ _", palavra2.getPalavraParcial());
    }

    @Test
    @DisplayName("Deve validar robustez do sistema")
    void deveValidarRobustezDoSistema() {
        BancoPalavras banco = new BancoPalavras();
        
        // Testa múltiplas operações seguidas
        for (int i = 0; i < 100; i++) {
            Palavra palavra = banco.obterPalavraAleatoria();
            Forca forca = new Forca();
            
            // Validações básicas devem sempre passar
            assertNotNull(palavra);
            assertNotNull(palavra.getPalavraSecreta());
            assertTrue(palavra.getTamanho() > 0);
            assertFalse(palavra.estahCompleta());
            
            assertEquals(0, forca.getNumeroErros());
            assertEquals(6, forca.getTentativasRestantes());
            assertFalse(forca.jogadorPerdeu());
        }
    }
}