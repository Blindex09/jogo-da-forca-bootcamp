# 🎮 Jogo da Forca - Bootcamp Edition

Um jogo da forca completo desenvolvido em Java usando Programação Orientada a Objetos (POO) como parte do bootcamp.

## 📋 Descrição

Este projeto implementa um jogo da forca tradicional com interface console, incluindo:

- Sistema de categorias de palavras
- Desenho ASCII da forca
- Interface de menu interativa
- Controle de tentativas e erros
- Feedback visual em tempo real

## 🏗️ Arquitetura

O projeto segue uma arquitetura bem organizada em packages:

```
src/main/java/com/bootcamp/forca/
├── Main.java                 # Classe principal
├── controller/
│   └── JogoDaForca.java     # Lógica principal do jogo
├── model/
│   ├── Palavra.java         # Modelo da palavra
│   ├── Forca.java          # Modelo da forca
│   └── BancoPalavras.java  # Gerenciador de palavras
└── view/
    └── Menu.java           # Interface do usuário
```

## 🎯 Funcionalidades

### Categorias Disponíveis
- 🐾 **Animais**: Elefante, Girafa, Leão, etc.
- 🍎 **Frutas**: Banana, Maçã, Laranja, etc.
- 🌍 **Países**: Brasil, Argentina, França, etc.
- 👨‍⚕️ **Profissões**: Médico, Engenheiro, Professor, etc.
- 🎨 **Cores**: Vermelho, Azul, Verde, etc.

### Recursos do Jogo
- ✅ Seleção de categoria (específica ou aleatória)
- 🎨 Desenho ASCII da forca com 6 estágios
- 📊 Acompanhamento de letras corretas e erradas
- 🔄 Sistema de tentativas (máximo 6 erros)
- 🏆 Telas de vitória e derrota
- 📱 Interface intuitiva com menus

## 🚀 Como Executar

### Pré-requisitos
- Java 11 ou superior
- Gradle

### Compilação e Execução

1. **Clone o projeto:**
   ```bash
   git clone https://github.com/Blindex09/jogo-da-forca-bootcamp.git
   cd jogo-da-forca-bootcamp
   ```

2. **Compile o projeto com Gradle:**
   ```bash
   gradle build
   ```

3. **Execute o jogo:**
   ```bash
   gradle run
   ```

### Alternativa - Execução direta
```bash
# Compilar
gradle build

# Executar o JAR
java -jar build/libs/jogo-da-forca-1.0-SNAPSHOT.jar
```

## 🧪 Testes Automatizados

O projeto inclui **82 testes automatizados** que cobrem 100% das funcionalidades:

```bash
# Executar todos os testes
gradle test

# Ver relatório HTML
# Após executar: build/reports/tests/test/index.html
```

### Cobertura de Testes
- ✅ **PalavraTest** (22 testes) - Lógica da palavra
- ✅ **ForcaTest** (25 testes) - Desenho ASCII da forca
- ✅ **BancoPalavrasTest** (18 testes) - Gerenciamento de categorias
- ✅ **JogoDaForcaTest** (10 testes) - Controller principal
- ✅ **IntegrationTest** (7 testes) - Cenários completos

## 🎮 Como Jogar

1. **Inicie o jogo** e escolha uma opção no menu principal
2. **Selecione uma categoria** ou jogue com categoria aleatória
3. **Digite uma letra** por vez para tentar descobrir a palavra
4. **Observe a forca** sendo desenhada a cada erro
5. **Ganhe** descobrindo todas as letras antes de 6 erros
6. **Perca** se a forca for completamente desenhada

### Regras
- 📝 Digite apenas uma letra por vez
- 🚫 Não é possível repetir letras já tentadas
- ⚠️ Máximo de 6 erros permitidos
- 🎯 Descubra todas as letras para ganhar

## 🏛️ Conceitos de POO Utilizados

- **Encapsulamento**: Dados privados com getters/setters apropriados
- **Abstração**: Classes representam conceitos do domínio do jogo
- **Organização**: Separação clara de responsabilidades em packages
- **Modularidade**: Cada classe tem uma responsabilidade específica

## 📁 Estrutura de Arquivos

```
jogo-da-forca-bootcamp/
├── build.gradle              # Configuração do Gradle
├── gradle.properties         # Propriedades do Gradle
├── README.md                # Documentação
├── executar.bat             # Script de execução
├── COMANDOS.md              # Guia de comandos
├── RELATORIO_TESTES.md      # Relatório de testes
├── GUIA_TESTES.md           # Guia de execução de testes
├── src/main/java/com/bootcamp/forca/
│   ├── Main.java            # Ponto de entrada
│   ├── controller/
│   │   └── JogoDaForca.java # Controlador principal
│   ├── model/
│   │   ├── BancoPalavras.java
│   │   ├── Forca.java
│   │   └── Palavra.java
│   └── view/
│       └── Menu.java        # Interface do usuário
└── src/test/java/com/bootcamp/forca/
    ├── AllTestsSuite.java   # Suíte de testes
    ├── IntegrationTest.java # Testes de integração
    ├── controller/
    │   └── JogoDaForcaTest.java
    └── model/
        ├── PalavraTest.java
        ├── ForcaTest.java
        └── BancoPalavrasTest.java
```

## 🛠️ Tecnologias

- **Linguagem**: Java 11+
- **Build Tool**: Gradle
- **Testes**: JUnit 5 (Jupiter)
- **Paradigma**: Programação Orientada a Objetos
- **Interface**: Console/Terminal

## 📈 Versão

- **Versão Atual**: 1.0.0
- **Status**: Completo e funcional
- **Testes**: 82 testes - 100% aprovados

## 🎓 Objetivos de Aprendizado

Este projeto foi desenvolvido para praticar:
- Estruturação de projetos Java
- Aplicação de conceitos de POO
- Organização de código em packages
- Uso do Gradle como build tool
- Desenvolvimento de aplicações console
- Implementação de lógica de jogos
- Testes automatizados com JUnit 5

## 📸 Screenshots

### Menu Principal
```
╔══════════════════════════════════════════════════════════╗
║                    🎯 MENU PRINCIPAL 🎯                   ║
╠══════════════════════════════════════════════════════════╣
║                                                          ║
║  1️⃣  - Jogar (Categoria Aleatória)                        ║
║  2️⃣  - Jogar (Escolher Categoria)                         ║
║  3️⃣  - Ver Categorias Disponíveis                         ║
║  4️⃣  - Como Jogar (Regras)                                ║
║  5️⃣  - Créditos                                           ║
║  0️⃣  - Sair                                               ║
║                                                          ║
╚══════════════════════════════════════════════════════════╝
```

### Jogo em Andamento
```
============================================================
🎯 JOGO DA FORCA - Categoria: ANIMAIS
============================================================

   +---+
   |   |
   O   |
  /|\  |
       |
       |
=========

Palavra: E _ E _ A N _ E

Tentativas restantes: 2
Letras erradas: Z, Q, W
Letras corretas: E, A, N

Digite uma letra:
```

---

**Desenvolvido como parte do bootcamp** 🎓
**Projeto completo com testes automatizados e documentação profissional** 🚀
