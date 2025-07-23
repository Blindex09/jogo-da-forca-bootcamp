package com.bootcamp.forca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Palavra.
 * Valida todas as funcionalidades relacionadas ao controle da palavra no jogo.
 */
@DisplayName("Testes da classe Palavra")
class PalavraTest {

    private Palavra palavra;
    private static final String PALAVRA_TESTE = "JAVA";
    private static final String CATEGORIA_TESTE = "TECNOLOGIA";

    @BeforeEach
    void setUp() {
        palavra = new Palavra(PALAVRA_TESTE, CATEGORIA_TESTE);
    }

    @Test
    @DisplayName("Deve criar palavra corretamente")
    void deveCriarPalavraCorretamente() {
        assertEquals(PALAVRA_TESTE, palavra.getPalavraSecreta());
        assertEquals(CATEGORIA_TESTE, palavra.getCategoria());
        assertEquals(4, palavra.getTamanho());
        assertFalse(palavra.estahCompleta());
    }

    @Test
    @DisplayName("Deve aceitar palavra em minúscula e converter para maiúscula")
    void deveConverterParaMaiuscula() {
        Palavra palavraMinuscula = new Palavra("python", "linguagem");
        assertEquals("PYTHON", palavraMinuscula.getPalavraSecreta());
    }

    @Test
    @DisplayName("Deve aceitar letra correta")
    void deveAceitarLetraCorreta() {
        assertTrue(palavra.tentarLetra('J'));
        assertTrue(palavra.tentarLetra('A'));
        assertTrue(palavra.tentarLetra('V'));
        assertTrue(palavra.letraJaTentada('J'));
        assertTrue(palavra.letraJaTentada('A'));
        assertTrue(palavra.letraJaTentada('V'));
    }

    @Test
    @DisplayName("Deve rejeitar letra incorreta")
    void deveRejeitarLetraIncorreta() {
        assertFalse(palavra.tentarLetra('X'));
        assertFalse(palavra.tentarLetra('Z'));
        assertFalse(palavra.letraJaTentada('X'));
        assertFalse(palavra.letraJaTentada('Z'));
    }

    @Test
    @DisplayName("Deve converter letra para maiúscula ao tentar")
    void deveConverterLetraParaMaiuscula() {
        assertTrue(palavra.tentarLetra('j')); // minúscula
        assertTrue(palavra.letraJaTentada('J')); // verifica se converteu
        assertTrue(palavra.letraJaTentada('j')); // deve funcionar com minúscula também
    }

    @Test
    @DisplayName("Deve mostrar palavra parcial corretamente")
    void deveMostrarPalavraParcial() {
        // No início, todas as letras devem estar ocultas
        assertEquals("_ _ _ _", palavra.getPalavraParcial());
        
        // Tentando a letra 'J'
        palavra.tentarLetra('J');
        assertEquals("J _ _ _", palavra.getPalavraParcial());
        
        // Tentando a letra 'A' (aparece duas vezes)
        palavra.tentarLetra('A');
        assertEquals("J A _ A", palavra.getPalavraParcial());
        
        // Tentando a letra 'V'
        palavra.tentarLetra('V');
        assertEquals("J A V A", palavra.getPalavraParcial());
    }

    @Test
    @DisplayName("Deve detectar palavra completa")
    void deveDetectarPalavraCompleta() {
        assertFalse(palavra.estahCompleta());
        
        palavra.tentarLetra('J');
        assertFalse(palavra.estahCompleta());
        
        palavra.tentarLetra('A');
        assertFalse(palavra.estahCompleta());
        
        palavra.tentarLetra('V');
        assertTrue(palavra.estahCompleta()); // JAVA está completa
    }

    @Test
    @DisplayName("Deve controlar letras já tentadas")
    void deveControlarLetrasJaTentadas() {
        assertFalse(palavra.letraJaTentada('J'));
        
        palavra.tentarLetra('J');
        assertTrue(palavra.letraJaTentada('J'));
        
        // Testando com letra que não existe na palavra
        palavra.tentarLetra('X');
        assertFalse(palavra.letraJaTentada('X')); // X não foi adicionada pois não existe na palavra
    }

    @Test
    @DisplayName("Deve retornar conjunto de letras descobertas")
    void deveRetornarLetrasDescobertas() {
        assertTrue(palavra.getLetrasDescobertas().isEmpty());
        
        palavra.tentarLetra('J');
        assertEquals(1, palavra.getLetrasDescobertas().size());
        assertTrue(palavra.getLetrasDescobertas().contains('J'));
        
        palavra.tentarLetra('A');
        assertEquals(2, palavra.getLetrasDescobertas().size());
        assertTrue(palavra.getLetrasDescobertas().contains('A'));
    }

    @Test
    @DisplayName("Deve funcionar com palavra de uma letra")
    void deveFuncionarComPalavraDeUmaLetra() {
        Palavra palavraUnica = new Palavra("A", "VOGAL");
        assertEquals(1, palavraUnica.getTamanho());
        assertEquals("_", palavraUnica.getPalavraParcial());
        
        palavraUnica.tentarLetra('A');
        assertEquals("A", palavraUnica.getPalavraParcial());
        assertTrue(palavraUnica.estahCompleta());
    }

    @Test
    @DisplayName("Deve funcionar com palavra com letras repetidas")
    void deveFuncionarComLetrasRepetidas() {
        Palavra palavraRepetida = new Palavra("BANANA", "FRUTA");
        assertEquals("_ _ _ _ _ _", palavraRepetida.getPalavraParcial());
        
        // Tentando 'A' que aparece 3 vezes
        palavraRepetida.tentarLetra('A');
        assertEquals("_ A _ A _ A", palavraRepetida.getPalavraParcial());
        
        // Tentando 'N' que aparece 2 vezes  
        palavraRepetida.tentarLetra('N');
        assertEquals("_ A N A N A", palavraRepetida.getPalavraParcial());
        
        // Tentando 'B'
        palavraRepetida.tentarLetra('B');
        assertEquals("B A N A N A", palavraRepetida.getPalavraParcial());
        assertTrue(palavraRepetida.estahCompleta());
    }
}
