# 🐍 Jogo da Cobrinha - Java Swing

Uma versão moderna e gráfica do clássico jogo Snake, desenvolvida em Java com interface Swing.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Como Executar](#como-executar)
- [Como Jogar](#como-jogar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Capturas de Tela](#capturas-de-tela)
- [Histórico de Versões](#histórico-de-versões)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

## 🎯 Sobre o Projeto

O Jogo da Cobrinha é uma implementação moderna do clássico Snake Game, originalmente desenvolvido em C e convertido para Java com interface gráfica usando Swing.
O projeto oferece uma experiência nostálgica com melhorias visuais e de usabilidade.

### Objetivos do Projeto
- Demonstrar conceitos de programação orientada a objetos
- Implementar padrões de design (MVC, Observer)
- Criar uma interface gráfica responsiva e intuitiva
- Gerenciar estado do jogo e persistência de dados

## ✨ Funcionalidades

### 🎮 Gameplay
- **Movimento fluido**: Controle responsivo com as setas do teclado
- **Sistema de crescimento**: A cobra cresce ao comer comida
- **Detecção de colisões**: Com paredes e próprio corpo
- **Pontuação em tempo real**: Sistema de pontos baseado na comida consumida
- **Velocidade constante**: Gameplay equilibrado e desafiador

### 🏆 Sistema de Recordes
- **Persistência de dados**: Recordes salvos em arquivo local
- **Histórico completo**: Visualização de todos os recordes anteriores
- **Identificação do jogador**: Nome do jogador associado a cada recorde

### 🎨 Interface Gráfica
- **Menu interativo**: Navegação intuitiva com setas
- **Design moderno**: Cores vibrantes e elementos visuais atrativos
- **Feedback visual**: Indicadores claros do estado do jogo
- **Responsividade**: Interface adaptável e fluida

### 📚 Sistema de Ajuda
- **Instruções detalhadas**: Guia completo sobre como jogar
- **Controles explicados**: Mapeamento completo das teclas
- **Dicas estratégicas**: Sugestões para melhorar o desempenho

## 🛠️ Tecnologias Utilizadas

- **Java SE 8+**: Linguagem principal de desenvolvimento
- **Swing**: Framework para interface gráfica
- **AWT**: Componentes básicos de interface
- **I/O Streams**: Manipulação de arquivos para persistência
- **Collections Framework**: Estruturas de dados (ArrayList)
- **Timer**: Controle de tempo e animações

## 📋 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java Development Kit (JDK) 8 ou superior**
- **Sistema operacional**: Windows, macOS ou Linux
- **Memória RAM**: Mínimo 512MB disponível
- **Espaço em disco**: 50MB para arquivos temporários

### Verificar Instalação do Java
```bash
java -version
javac -version
```

## 🚀 Como Executar

### 1. Clonar o Repositório
```bash
cd jogo-cobrinha-java
```

### 2. Compilar o Código
```bash
javac JogoCobrinha.java
```

### 3. Executar o Jogo
```bash
java JogoCobrinha
```

### 4. Executar via IDE
- Abra o projeto em sua IDE favorita (Eclipse, IntelliJ, NetBeans)
- Execute a classe `JogoCobrinha.java`

## 🎲 Como Jogar

### Objetivo
Controle a cobrinha para comer o máximo de comida possível sem bater nas paredes ou no próprio corpo.

### Controles
- **Menu Principal**:
  - `↑/↓`: Navegar entre opções
  - `Enter`: Selecionar opção
  - `ESC`: Sair do jogo

- **Durante o Jogo**:
  - `↑`: Mover para cima
  - `↓`: Mover para baixo
  - `←`: Mover para esquerda
  - `→`: Mover para direita
  - `ESC`: Voltar ao menu

### Regras
1. A cobra move-se continuamente na direção selecionada
2. Não é possível mover-se diretamente na direção oposta
3. A cobra cresce ao comer comida (quadrado vermelho)
4. O jogo termina se a cobra:
   - Bater nas paredes
   - Colidir com o próprio corpo
5. Cada comida consumida adiciona 1 ponto à pontuação

### Estratégias
- Mantenha-se longe das paredes quando possível
- Evite criar "armadilhas" para si mesmo
- Planeje seus movimentos com antecedência
- Use as bordas estrategicamente para mudanças de direção

## 🏗️ Estrutura do Projeto

```
jogo-cobrinha-java/
│
├── JogoCobrinha.java           # Classe principal do jogo
├── recordes.txt                # Arquivo de recordes (criado automaticamente)
├── README.md                   # Documentação do projeto
├── LICENSE                     # Licença do projeto
└── screenshots/                # Capturas de tela
    ├── menu-principal.png
    ├── tela-jogo.png
    └── game-over.png
```

### Arquitetura do Código

#### Classe Principal: `JogoCobrinha`
- **Responsabilidade**: Gerenciar a janela principal e navegação entre telas
- **Componentes**: CardLayout para alternância entre panels
- **Configurações**: Tamanho da janela, título, comportamento de fechamento

#### Classe Interna: `MenuPanel`
- **Responsabilidade**: Interface do menu principal
- **Funcionalidades**: Navegação por teclado, renderização de opções, execução de comandos
- **Métodos principais**:
  - `paintComponent()`: Renderização visual
  - `executarOpcao()`: Processamento das seleções
  - `verRecordes()`: Exibição de recordes
  - `comoJogar()`: Instruções do jogo

#### Classe Interna: `JogoPanel`
- **Responsabilidade**: Lógica principal do jogo
- **Componentes**: Timer para controle de velocidade, ArrayList para segmentos da cobra
- **Métodos principais**:
  - `moverCobra()`: Atualização da posição
  - `verificarColisoes()`: Detecção de colisões
  - `gerarComida()`: Posicionamento aleatório da comida
  - `gameOver()`: Finalização do jogo
  - `salvarRecorde()`: Persistência dos dados

## 📸 Capturas de Tela

### Menu Principal
![Menu Principal](screenshots/menu-principal.png)
*Interface inicial com opções de navegação*

### Tela do Jogo
![Tela do Jogo](screenshots/tela-jogo.png)
*Gameplay com cobra, comida e placar*

### Game Over
![Game Over](screenshots/game-over.png)
*Tela de fim de jogo com pontuação*

## 🔧 Configurações Avançadas

### Personalização da Velocidade
```java
private static final int VELOCIDADE_INICIAL = 150; // Milissegundos
```

### Ajuste do Tamanho da Célula
```java
private static final int TAMANHO_CELULA = 20; // Pixels
```

### Modificação das Dimensões
```java
private static final int LARGURA_JOGO = 600; // Pixels
private static final int ALTURA_JOGO = 400;  // Pixels
```

## 📊 Métricas do Projeto

- **Linhas de código**: ~350 linhas
- **Classes**: 3 (1 principal + 2 internas)
- **Métodos**: 15+ métodos
- **Complexidade**: Intermediária
- **Tempo de desenvolvimento**: ~8 horas

## 🐛 Problemas Conhecidos

### Issues Abertas
- [ ] Implementar níveis de dificuldade
- [ ] Adicionar efeitos sonoros
- [ ] Criar sistema de conquistas
- [ ] Implementar modo multiplayer

### Limitações Atuais
- Sem suporte a redimensionamento de janela
- Recordes não são ordenados automaticamente
- Sem validação de entrada para nomes muito longos

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. **Fork** o projeto
2. **Crie** uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. **Commit** suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. **Push** para a branch (`git push origin feature/nova-funcionalidade`)
5. **Abra** um Pull Request

### Diretrizes de Contribuição
- Siga as convenções de código Java
- Adicione comentários para código complexo
- Teste suas mudanças antes de submeter
- Atualize a documentação conforme necessário

### Tipos de Contribuição
- 🐛 **Bug fixes**: Correções de problemas
- ✨ **Features**: Novas funcionalidades
- 📚 **Documentation**: Melhorias na documentação
- 🎨 **Design**: Melhorias visuais
- ⚡ **Performance**: Otimizações de código

## 📝 Histórico de Versões

### v1.0.0 (2025-01-03)
- ✅ Implementação inicial do jogo
- ✅ Menu principal funcional
- ✅ Sistema de recordes
- ✅ Interface gráfica completa
- ✅ Documentação básica

### Próximas Versões
- v1.1.0: Níveis de dificuldade
- v1.2.0: Efeitos sonoros
- v1.3.0: Sistema de conquistas

## 🎯 Roadmap

### Curto Prazo (1-2 meses)
- [ ] Implementar múltiplos níveis de dificuldade
- [ ] Adicionar efeitos sonoros e música
- [ ] Criar sistema de pausa
- [ ] Implementar validação de entrada

### Médio Prazo (3-6 meses)
- [ ] Desenvolver modo multiplayer local
- [ ] Criar sistema de conquistas
- [ ] Implementar temas visuais
- [ ] Adicionar opções de configuração

### Longo Prazo (6+ meses)
- [ ] Criar versão web (Java Applet/WebAssembly)
- [ ] Implementar multiplayer online
- [ ] Desenvolver modo torneio
- [ ] Criar aplicativo mobile

## 🏆 Reconhecimentos

- Inspirado no clássico Snake Game
- Baseado na versão original em C
- Comunidade Java pela documentação
- Contribuidores do projeto

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

### Resumo da Licença
- ✅ Uso comercial permitido
- ✅ Modificação permitida
- ✅ Distribuição permitida
- ✅ Uso privado permitido
- ❌ Sem garantia ou responsabilidade

---

## 📞 Contato

**Desenvolvedor**: EDILSON SALVADOR RICCI
**Email**: esricci26@gmail.com
**GitHub**: https://github.com/ESRicci26

---

**⭐ Se você gostou do projeto, considere dar uma estrela no repositório!**

*Desenvolvido com ❤️ em Java*
