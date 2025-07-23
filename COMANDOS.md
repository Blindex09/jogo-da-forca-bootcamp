# 🚀 COMANDOS ÚTEIS - JOGO DA FORCA

## Compilação e Execução

### Compilar o projeto
```bash
gradle build
```

### Executar o jogo
```bash
gradle run
```

### Executar JAR diretamente
```bash
java -jar build/libs/jogo-da-forca-1.0-SNAPSHOT.jar
```

### Limpar build
```bash
gradle clean
```

### Compilar e executar
```bash
gradle build && gradle run
```

## Desenvolvimento

### Estrutura do projeto
```
src/main/java/com/bootcamp/forca/
├── Main.java                 # Ponto de entrada
├── controller/
│   └── JogoDaForca.java     # Lógica do jogo
├── model/
│   ├── Palavra.java         # Modelo da palavra
│   ├── Forca.java          # Modelo da forca
│   └── BancoPalavras.java  # Banco de palavras
└── view/
    └── Menu.java           # Interface do usuário
```

### Comandos no Windows
```cmd
# Navegar para o projeto
cd "C:\Users\olive\Documents\GitHub\jogo-da-forca"

# Compilar
gradle build

# Executar
gradle run

# Ou usar o arquivo batch
executar.bat
```

### Verificar se tudo está funcionando
```bash
# Compilar e testar
gradle build

# Verificar se o JAR foi gerado
dir build\libs\

# Executar para testar
java -jar build\libs\jogo-da-forca-1.0-SNAPSHOT.jar
```

## Características do Jogo

### Categorias Disponíveis
- 🐾 ANIMAIS (15 palavras)
- 🍎 FRUTAS (15 palavras)  
- 🌍 PAÍSES (15 palavras)
- 👨‍⚕️ PROFISSÕES (15 palavras)
- 🎨 CORES (15 palavras)

### Funcionalidades
- ✅ Menu interativo
- ✅ Seleção de categoria
- ✅ Desenho ASCII da forca (6 estágios)
- ✅ Controle de tentativas
- ✅ Feedback visual
- ✅ Telas de vitória/derrota

### Regras
- 📝 6 tentativas máximas
- 🔤 Uma letra por vez
- 🙅 Não permite repetição de letras
- 🎯 Objetivo: descobrir a palavra completa

## Tecnologias
- **Java**: 11+
- **Build**: Gradle
- **Paradigma**: POO (Programação Orientada a Objetos)
- **Interface**: Console/Terminal
