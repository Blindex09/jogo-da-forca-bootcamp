package com.bootcamp.forca;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Suíte de testes completa para o Jogo da Forca.
 * Executa todos os testes do sistema de forma organizada.
 */
@Suite
@SuiteDisplayName("🎮 Jogo da Forca - Suíte Completa de Testes")
@SelectPackages({
    "com.bootcamp.forca.model",
    "com.bootcamp.forca.controller", 
    "com.bootcamp.forca"
})
public class AllTestsSuite {
    
    // Esta classe serve apenas para agrupar todos os testes
    // A execução é feita pela anotação @Suite
    
    /*
     * COBERTURA DE TESTES:
     * 
     * 📦 MODEL PACKAGE:
     * ✅ PalavraTest (12 testes)
     *    - Criação e inicialização
     *    - Tentativas de letras corretas/incorretas  
     *    - Conversão maiúscula/minúscula
     *    - Palavra parcial e completa
     *    - Controle de letras já tentadas
     *    - Casos especiais (palavra única, letras repetidas)
     * 
     * ✅ ForcaTest (10 testes)
     *    - Inicialização sem erros
     *    - Adição de erros e controle de limite
     *    - Detecção de derrota
     *    - Desenhos ASCII dinâmicos
     *    - Reinicialização
     *    - Cálculo de tentativas restantes
     * 
     * ✅ BancoPalavrasTest (13 testes)
     *    - Inicialização com categorias corretas
     *    - Verificação de existência de categorias
     *    - Obtenção de palavras aleatórias
     *    - Validação de palavras (maiúsculas, sem caracteres especiais)
     *    - Case insensitive para categorias
     *    - Múltiplas instâncias independentes
     * 
     * 📦 CONTROLLER PACKAGE:
     * ✅ JogoDaForcaTest (10 testes)
     *    - Inicialização correta do jogo
     *    - Integração com banco de palavras
     *    - Estado inicial consistente
     *    - Funcionalidades do banco de palavras
     *    - Múltiplas instâncias independentes
     * 
     * 📦 INTEGRATION TESTS:
     * ✅ IntegrationTest (7 testes)
     *    - Simulação de jogo completo com vitória
     *    - Simulação de jogo completo com derrota
     *    - Jogo misto com vitória de última hora
     *    - Validação de integração completa
     *    - Comportamento com palavras de tamanhos diferentes
     *    - Sistema de reinicialização
     *    - Robustez geral do sistema
     * 
     * TOTAL: 62 TESTES AUTOMATIZADOS
     * 
     * EXECUTAR TESTES:
     * - gradle test
     * - gradle test --info (com detalhes)
     * - gradle test --continue (continua mesmo com falhas)
     */
}