package com.bootcamp.forca.controller;

import com.bootcamp.forca.model.BancoPalavras;
import com.bootcamp.forca.model.Forca;
import com.bootcamp.forca.model.Palavra;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Classe principal que controla a lógica do jogo da forca.
 * Gerencia o fluxo do jogo, interações com o usuário e estado do jogo.
 */
public class JogoDaForca {
    private Palavra palavraAtual;
    private Forca forca;
    private BancoPalavras bancoPalavras;
    private Set<Character> letrasErradas;
    private Scanner scanner;
    private boolean jogoAtivo;

    public JogoDaForca() {
        this.forca = new Forca();
        this.bancoPalavras = new BancoPalavras();
        this.letrasErradas = new HashSet<>();
        this.scanner = new Scanner(System.in);
        this.jogoAtivo = false;
    }

    /**
     * Inicia um novo jogo da forca.
     * @param categoria Categoria da palavra (opcional)
     */
    public void iniciarJogo(String categoria) {
        // Resetar estado do jogo
        forca.reiniciar();
        letrasErradas.clear();
        
        // Escolher palavra
        if (categoria != null && !categoria.isEmpty()) {
            try {
                palavraAtual = bancoPalavras.obterPalavraAleatoria(categoria);
            } catch (IllegalArgumentException e) {
                System.out.println("Categoria inválida! Escolhendo palavra aleatória...");
                palavraAtual = bancoPalavras.obterPalavraAleatoria();
            }
        } else {
            palavraAtual = bancoPalavras.obterPalavraAleatoria();
        }
        
        jogoAtivo = true;
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎮 NOVO JOGO DA FORCA INICIADO! 🎮");
        System.out.println("=".repeat(50));
        System.out.println("Categoria: " + palavraAtual.getCategoria());
        System.out.println("Palavra com " + palavraAtual.getTamanho() + " letras");
        System.out.println();
        
        // Loop principal do jogo
        while (jogoAtivo) {
            exibirEstadoJogo();
            
            if (verificarFimDeJogo()) {
                break;
            }
            
            processarTentativa();
        }
    }

    /**
     * Exibe o estado atual do jogo (forca, palavra, letras erradas, etc.).
     */
    private void exibirEstadoJogo() {
        limparTela();
        
        System.out.println("=".repeat(60));
        System.out.println("🎯 JOGO DA FORCA - Categoria: " + palavraAtual.getCategoria());
        System.out.println("=".repeat(60));
        System.out.println();
        
        // Exibir forca
        System.out.println(forca.getDesenho());
        System.out.println();
        
        // Exibir palavra parcial
        System.out.println("Palavra: " + palavraAtual.getPalavraParcial());
        System.out.println();
        
        // Exibir informações do jogo
        System.out.println("Tentativas restantes: " + forca.getTentativasRestantes());
        
        if (!letrasErradas.isEmpty()) {
            System.out.println("Letras erradas: " + letrasErradas.toString().replaceAll("[\\[\\]]", ""));
        }
        
        if (!palavraAtual.getLetrasDescobertas().isEmpty()) {
            System.out.println("Letras corretas: " + palavraAtual.getLetrasDescobertas().toString().replaceAll("[\\[\\]]", ""));
        }
        
        System.out.println();
    }

    /**
     * Processa uma tentativa do jogador.
     */
    private void processarTentativa() {
        System.out.print("Digite uma letra: ");
        String entrada = scanner.nextLine().trim().toUpperCase();
        
        if (entrada.length() != 1) {
            System.out.println("❌ Digite apenas uma letra!");
            aguardarEnter();
            return;
        }
        
        char letra = entrada.charAt(0);
        
        if (!Character.isLetter(letra)) {
            System.out.println("❌ Digite apenas letras!");
            aguardarEnter();
            return;
        }
        
        if (palavraAtual.letraJaTentada(letra) || letrasErradas.contains(letra)) {
            System.out.println("❌ Você já tentou essa letra!");
            aguardarEnter();
            return;
        }
        
        // Processar a tentativa
        if (palavraAtual.tentarLetra(letra)) {
            System.out.println("✅ Letra correta!");
        } else {
            letrasErradas.add(letra);
            forca.adicionarErro();
            System.out.println("❌ Letra incorreta!");
        }
        
        aguardarEnter();
    }

    /**
     * Verifica se o jogo chegou ao fim (vitória ou derrota).
     * @return true se o jogo terminou
     */
    private boolean verificarFimDeJogo() {
        if (palavraAtual.estahCompleta()) {
            exibirVitoria();
            jogoAtivo = false;
            return true;
        }
        
        if (forca.jogadorPerdeu()) {
            exibirDerrota();
            jogoAtivo = false;
            return true;
        }
        
        return false;
    }

    /**
     * Exibe a tela de vitória.
     */
    private void exibirVitoria() {
        limparTela();
        System.out.println();
        System.out.println("🎉".repeat(20));
        System.out.println("🎉" + " ".repeat(36) + "🎉");
        System.out.println("🎉     PARABÉNS! VOCÊ GANHOU!     🎉");
        System.out.println("🎉" + " ".repeat(36) + "🎉");
        System.out.println("🎉".repeat(20));
        System.out.println();
        System.out.println("A palavra era: " + palavraAtual.getPalavraSecreta());
        System.out.println("Categoria: " + palavraAtual.getCategoria());
        System.out.println("Você acertou com " + forca.getTentativasRestantes() + " tentativas restantes!");
        System.out.println();
    }

    /**
     * Exibe a tela de derrota.
     */
    private void exibirDerrota() {
        limparTela();
        System.out.println(forca.getDesenho());
        System.out.println();
        System.out.println("💀".repeat(20));
        System.out.println("💀" + " ".repeat(36) + "💀");
        System.out.println("💀      GAME OVER! VOCÊ PERDEU!    💀");
        System.out.println("💀" + " ".repeat(36) + "💀");
        System.out.println("💀".repeat(20));
        System.out.println();
        System.out.println("A palavra era: " + palavraAtual.getPalavraSecreta());
        System.out.println("Categoria: " + palavraAtual.getCategoria());
        System.out.println();
    }

    /**
     * Limpa a tela do console (simulação).
     */
    private void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Aguarda o usuário pressionar Enter.
     */
    private void aguardarEnter() {
        System.out.print("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    // Getters para acessar informações do jogo
    public BancoPalavras getBancoPalavras() {
        return bancoPalavras;
    }

    public boolean isJogoAtivo() {
        return jogoAtivo;
    }

    public Palavra getPalavraAtual() {
        return palavraAtual;
    }

    public void fecharScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}