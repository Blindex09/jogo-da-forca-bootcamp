package com.bootcamp.forca.view;

import com.bootcamp.forca.controller.JogoDaForca;

import java.util.Scanner;
import java.util.Set;

/**
 * Classe responsável pela interface do usuário.
 * Gerencia menus, entradas e exibição de informações.
 */
public class Menu {
    private Scanner scanner;
    private JogoDaForca jogo;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.jogo = new JogoDaForca();
    }

    /**
     * Inicia o menu principal do jogo.
     */
    public void iniciar() {
        exibirBoasVindas();
        
        boolean continuar = true;
        while (continuar) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    jogarComCategoriaAleatoria();
                    break;
                case 2:
                    jogarComCategoriaEspecifica();
                    break;
                case 3:
                    exibirCategorias();
                    break;
                case 4:
                    exibirRegras();
                    break;
                case 5:
                    exibirCreditos();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    aguardarEnter();
            }
        }
        
        encerrarPrograma();
    }

    /**
     * Exibe a mensagem de boas-vindas.
     */
    private void exibirBoasVindas() {
        limparTela();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                          ║");
        System.out.println("║    🎮 BEM-VINDO AO JOGO DA FORCA - BOOTCAMP EDITION! 🎮   ║");
        System.out.println("║                                                          ║");
        System.out.println("║         Desenvolva suas habilidades de adivinhação!     ║");
        System.out.println("║                                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        aguardarEnter();
    }

    /**
     * Exibe o menu principal.
     */
    private void exibirMenuPrincipal() {
        limparTela();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    🎯 MENU PRINCIPAL 🎯                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║                                                          ║");
        System.out.println("║  1️⃣  - Jogar (Categoria Aleatória)                        ║");
        System.out.println("║  2️⃣  - Jogar (Escolher Categoria)                         ║");
        System.out.println("║  3️⃣  - Ver Categorias Disponíveis                         ║");
        System.out.println("║  4️⃣  - Como Jogar (Regras)                                ║");
        System.out.println("║  5️⃣  - Créditos                                           ║");
        System.out.println("║  0️⃣  - Sair                                               ║");
        System.out.println("║                                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Lê uma opção do menu do usuário.
     * @return A opção escolhida
     */
    private int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            return opcao;
        } catch (NumberFormatException e) {
            return -1; // Opção inválida
        }
    }

    /**
     * Inicia um jogo com categoria aleatória.
     */
    private void jogarComCategoriaAleatoria() {
        limparTela();
        System.out.println("🎲 Iniciando jogo com categoria aleatória...");
        System.out.println();
        jogo.iniciarJogo(null);
        
        // Após o jogo terminar, perguntar se quer jogar novamente
        perguntarJogarNovamente();
    }

    /**
     * Inicia um jogo com categoria específica escolhida pelo usuário.
     */
    private void jogarComCategoriaEspecifica() {
        limparTela();
        System.out.println("📂 Escolha uma categoria:");
        System.out.println();
        
        Set<String> categorias = jogo.getBancoPalavras().getCategorias();
        int contador = 1;
        
        for (String categoria : categorias) {
            System.out.println(contador + " - " + categoria);
            contador++;
        }
        
        System.out.println("0 - Categoria Aleatória");
        System.out.println();
        System.out.print("Digite o número da categoria desejada: ");
        
        int opcaoCategoria = lerOpcao();
        
        if (opcaoCategoria == 0) {
            jogo.iniciarJogo(null);
        } else if (opcaoCategoria > 0 && opcaoCategoria <= categorias.size()) {
            String[] categoriasArray = categorias.toArray(new String[0]);
            String categoriaEscolhida = categoriasArray[opcaoCategoria - 1];
            jogo.iniciarJogo(categoriaEscolhida);
        } else {
            System.out.println("❌ Opção inválida! Iniciando com categoria aleatória...");
            aguardarEnter();
            jogo.iniciarJogo(null);
        }
        
        perguntarJogarNovamente();
    }

    /**
     * Exibe todas as categorias disponíveis.
     */
    private void exibirCategorias() {
        limparTela();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                📂 CATEGORIAS DISPONÍVEIS 📂               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        Set<String> categorias = jogo.getBancoPalavras().getCategorias();
        int contador = 1;
        
        for (String categoria : categorias) {
            System.out.println("🏷️  " + contador + ". " + categoria);
            contador++;
        }
        
        System.out.println();
        System.out.println("Total de categorias: " + categorias.size());
        System.out.println();
        aguardarEnter();
    }

    /**
     * Exibe as regras do jogo.
     */
    private void exibirRegras() {
        limparTela();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                     📋 COMO JOGAR 📋                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🎯 OBJETIVO:");
        System.out.println("   Descobrir a palavra secreta antes que o boneco seja enforcado!");
        System.out.println();
        System.out.println("🎮 COMO JOGAR:");
        System.out.println("   • Uma palavra será escolhida aleatoriamente");
        System.out.println("   • Você verá apenas os espaços das letras: _ _ _ _");
        System.out.println("   • Digite uma letra por vez para tentar descobrir a palavra");
        System.out.println("   • Se a letra estiver na palavra, ela será revelada");
        System.out.println("   • Se a letra NÃO estiver na palavra, um pedaço do boneco será desenhado");
        System.out.println();
        System.out.println("⚠️  ATENÇÃO:");
        System.out.println("   • Você tem apenas 6 tentativas erradas!");
        System.out.println("   • Cada erro adiciona uma parte do boneco na forca");
        System.out.println("   • Não é possível repetir letras já tentadas");
        System.out.println();
        System.out.println("🏆 VITÓRIA: Descubra todas as letras antes de 6 erros");
        System.out.println("💀 DERROTA: Complete o desenho do boneco (6 erros)");
        System.out.println();
        aguardarEnter();
    }

    /**
     * Exibe os créditos do jogo.
     */
    private void exibirCreditos() {
        limparTela();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                      👨‍💻 CRÉDITOS 👨‍💻                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🎮 JOGO DA FORCA - BOOTCAMP EDITION");
        System.out.println();
        System.out.println("💻 Desenvolvido em Java");
        System.out.println("🏗️  Build System: Gradle");
        System.out.println("🎯 Paradigma: Programação Orientada a Objetos (POO)");
        System.out.println();
        System.out.println("📚 Projeto desenvolvido como parte do bootcamp");
        System.out.println("🚀 Focado em aprendizado e prática de POO");
        System.out.println();
        System.out.println("🎨 Interface ASCII Art");
        System.out.println("📱 Aplicação Console");
        System.out.println();
        System.out.println("Versão: 1.0.0");
        System.out.println("Data: " + java.time.LocalDate.now().toString());
        System.out.println();
        aguardarEnter();
    }

    /**
     * Pergunta se o usuário quer jogar novamente.
     */
    private void perguntarJogarNovamente() {
        System.out.println();
        System.out.print("Deseja jogar novamente? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();
        
        if (resposta.equals("S") || resposta.equals("SIM")) {
            // Volta ao menu principal ao invés de iniciar outro jogo imediatamente
            return;
        }
    }

    /**
     * Encerra o programa.
     */
    private void encerrarPrograma() {
        limparTela();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                          ║");
        System.out.println("║            🎮 OBRIGADO POR JOGAR! 🎮                      ║");
        System.out.println("║                                                          ║");
        System.out.println("║         Volte sempre para mais diversão!                ║");
        System.out.println("║                                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        jogo.fecharScanner();
        scanner.close();
        System.exit(0);
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
}