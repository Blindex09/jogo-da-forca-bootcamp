package com.bootcamp.forca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Forca.
 * Valida o controle dos erros e desenho da forca.
 */
@DisplayName("Testes da classe Forca")
class ForcaTest {

    private Forca forca;

    @BeforeEach
    void setUp() {
        forca = new Forca();
    }

    @Test
    @DisplayName("Deve inicializar forca sem erros")
    void deveInicializarForcaSemErros() {
        assertEquals(0, forca.getNumeroErros());
        assertEquals(6, forca.getMaxErros());
        assertEquals(6, forca.getTentativasRestantes());
        assertFalse(forca.jogadorPerdeu());
    }

    @Test
    @DisplayName("Deve adicionar erro corretamente")
    void deveAdicionarErroCorretamente() {
        forca.adicionarErro();
        assertEquals(1, forca.getNumeroErros());
        assertEquals(5, forca.getTentativasRestantes());
        assertFalse(forca.jogadorPerdeu());
    }

    @Test
    @DisplayName("Deve detectar derrota após 6 erros")
    void deveDetectarDerrotaApos6Erros() {
        // Adicionando 5 erros
        for (int i = 0; i < 5; i++) {
            forca.adicionarErro();
            assertFalse(forca.jogadorPerdeu());
        }
        
        assertEquals(5, forca.getNumeroErros());
        assertEquals(1, forca.getTentativasRestantes());
        
        // 6º erro - jogador deve perder
        forca.adicionarErro();
        assertEquals(6, forca.getNumeroErros());
        assertEquals(0, forca.getTentativasRestantes());
        assertTrue(forca.jogadorPerdeu());
    }

    @Test
    @DisplayName("Não deve adicionar mais de 6 erros")
    void naoDeveAdicionarMaisDe6Erros() {
        // Adicionando 10 erros (mas deve parar em 6)
        for (int i = 0; i < 10; i++) {
            forca.adicionarErro();
        }
        
        assertEquals(6, forca.getNumeroErros());
        assertEquals(0, forca.getTentativasRestantes());
        assertTrue(forca.jogadorPerdeu());
    }

    @Test
    @DisplayName("Deve ter desenho diferente para cada nível de erro")
    void deveTerDesenhosDiferentes() {
        String desenhoInicial = forca.getDesenho();
        
        // Cada erro deve produzir desenho diferente
        for (int i = 1; i <= 6; i++) {
            forca.adicionarErro();
            String novoDesenho = forca.getDesenho();
            assertNotEquals(desenhoInicial, novoDesenho);
            desenhoInicial = novoDesenho;
        }
    }

    @Test
    @DisplayName("Desenho deve conter elementos básicos da forca")
    void desenhoDeveConterElementosBasicos() {
        String desenho = forca.getDesenho();
        
        // Deve conter a base da forca
        assertTrue(desenho.contains("+---+"));
        assertTrue(desenho.contains("|"));
        assertTrue(desenho.contains("========="));
    }

    @Test
    @DisplayName("Desenho final deve conter boneco completo")
    void desenhoFinalDeveConterBonecoCompleto() {
        // Adicionando todos os 6 erros
        for (int i = 0; i < 6; i++) {
            forca.adicionarErro();
        }
        
        String desenhoFinal = forca.getDesenho();
        
        // Deve conter todas as partes do boneco
        assertTrue(desenhoFinal.contains("O"));   // cabeça
        assertTrue(desenhoFinal.contains("|"));   // corpo
        assertTrue(desenhoFinal.contains("/"));   // braços e pernas
        assertTrue(desenhoFinal.contains("\\"));  // braços e pernas
    }

    @Test
    @DisplayName("Deve reiniciar forca corretamente")
    void deveReiniciarForcaCorretamente() {
        // Adicionando alguns erros
        forca.adicionarErro();
        forca.adicionarErro();
        forca.adicionarErro();
        
        assertEquals(3, forca.getNumeroErros());
        
        // Reiniciando
        forca.reiniciar();
        
        assertEquals(0, forca.getNumeroErros());
        assertEquals(6, forca.getTentativasRestantes());
        assertFalse(forca.jogadorPerdeu());
    }

    @Test
    @DisplayName("Deve calcular tentativas restantes corretamente")
    void deveCalcularTentativasRestantesCorretamente() {
        assertEquals(6, forca.getTentativasRestantes());
        
        forca.adicionarErro();
        assertEquals(5, forca.getTentativasRestantes());
        
        forca.adicionarErro();
        assertEquals(4, forca.getTentativasRestantes());
        
        forca.adicionarErro();
        assertEquals(3, forca.getTentativasRestantes());
    }

    @Test
    @DisplayName("Constante MAX_ERROS deve ser 6")
    void constanteMaxErrosDeveSer6() {
        assertEquals(6, forca.getMaxErros());
        
        // Mesmo após adicionar erros, MAX_ERROS não deve mudar
        forca.adicionarErro();
        assertEquals(6, forca.getMaxErros());
    }

    @Test
    @DisplayName("Desenhos devem ter formato ASCII válido")
    void desenhosDevemTerFormatoASCIIValido() {
        for (int i = 0; i <= 6; i++) {
            String desenho = forca.getDesenho();
            
            // Deve ter múltiplas linhas
            assertTrue(desenho.contains("\n"));
            
            // Não deve estar vazio
            assertFalse(desenho.trim().isEmpty());
            
            // Deve ter tamanho razoável
            assertTrue(desenho.length() > 50); // Desenho básico tem pelo menos 50 caracteres
            
            if (i < 6) {
                forca.adicionarErro();
            }
        }
    }
}
