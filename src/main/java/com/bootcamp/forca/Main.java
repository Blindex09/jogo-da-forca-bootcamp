package com.bootcamp.forca;

import com.bootcamp.forca.view.Menu;

/**
 * Classe principal do Jogo da Forca.
 * Ponto de entrada da aplicação.
 * 
 * Este jogo foi desenvolvido como parte do bootcamp, focando em:
 * - Programação Orientada a Objetos (POO)
 * - Organização de código em packages
 * - Uso de padrões de design
 * - Interface console interativa
 * 
 * @author Bootcamp Student
 * @version 1.0.0
 */
public class Main {
    
    /**
     * Método principal que inicia a aplicação.
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        try {
            // Configurar encoding UTF-8 para suporte a caracteres especiais
            System.setProperty("file.encoding", "UTF-8");
            
            // Criar e iniciar o menu principal
            Menu menu = new Menu();
            menu.iniciar();
            
        } catch (Exception e) {
            System.err.println("❌ Erro crítico na aplicação:");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
