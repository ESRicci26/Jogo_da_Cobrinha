package javaricci.com.br;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class JogoCobrinha extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static final int LARGURA_TELA = 600;
    private static final int ALTURA_TELA = 650;
    private static final int TAMANHO_CELULA = 20;
    private static final int LARGURA_JOGO = 600;
    private static final int ALTURA_JOGO = 400;
    private static final int VELOCIDADE_INICIAL = 150;
    
    private String nomeJogador = "";
    private MenuPanel menuPanel;
    private JogoPanel jogoPanel;
    private CardLayout cardLayout;
    private JPanel containerPanel;
    
    public JogoCobrinha() {
        setTitle("Jogo da Cobrinha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(LARGURA_TELA, ALTURA_TELA);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        
        menuPanel = new MenuPanel();
        containerPanel.add(menuPanel, "MENU");
        
        add(containerPanel);
        
        cardLayout.show(containerPanel, "MENU");
    }
    
    private class MenuPanel extends JPanel {
        private int opcaoSelecionada = 0;
        private String[] opcoes = {"Jogar Cobrinha", "Ver Recordes", "Como Jogar?", "Sair do Jogo"};
        
        public MenuPanel() {
            setBackground(Color.BLACK);
            setFocusable(true);
            requestFocus();
            
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch(e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            opcaoSelecionada = Math.max(0, opcaoSelecionada - 1);
                            repaint();
                            break;
                        case KeyEvent.VK_DOWN:
                            opcaoSelecionada = Math.min(opcoes.length - 1, opcaoSelecionada + 1);
                            repaint();
                            break;
                        case KeyEvent.VK_ENTER:
                            executarOpcao();
                            break;
                        case KeyEvent.VK_ESCAPE:
                            System.exit(0);
                            break;
                    }
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            
            String titulo = "JOGO DA COBRINHA";
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(titulo)) / 2;
            g.drawString(titulo, x, 100);
            
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            for(int i = 0; i < opcoes.length; i++) {
                String prefixo = (i == opcaoSelecionada) ? "[*] " : "[ ] ";
                String texto = prefixo + opcoes[i];
                g.drawString(texto, 50, 200 + i * 40);
            }
            
            g.setFont(new Font("Arial", Font.ITALIC, 12));
            g.drawString("Use as setas para navegar, ENTER para selecionar", 50, 400);
        }
        
        private void executarOpcao() {
            switch(opcaoSelecionada) {
                case 0:
                    iniciarJogo();
                    break;
                case 1:
                    verRecordes();
                    break;
                case 2:
                    comoJogar();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
        
        private void iniciarJogo() {
            nomeJogador = JOptionPane.showInputDialog(this, "Nome do jogador:");
            if(nomeJogador == null || nomeJogador.trim().isEmpty()) {
                nomeJogador = "Jogador";
            }
            
            jogoPanel = new JogoPanel();
            containerPanel.add(jogoPanel, "JOGO");
            cardLayout.show(containerPanel, "JOGO");
            jogoPanel.requestFocus();
        }
        
        private void verRecordes() {
            StringBuilder recordes = new StringBuilder();
            recordes.append("=== RECORDES ===\n");
            
            try {
                File arquivo = new File("recordes.txt");
                if(arquivo.exists()) {
                    BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                    String linha;
                    while((linha = reader.readLine()) != null) {
                        recordes.append(linha).append("\n");
                    }
                    reader.close();
                } else {
                    recordes.append("Nenhum recorde registrado!");
                }
            } catch(IOException e) {
                recordes.append("Erro ao ler recordes!");
            }
            
            JOptionPane.showMessageDialog(this, recordes.toString(), "Recordes", JOptionPane.INFORMATION_MESSAGE);
        }
        
        private void comoJogar() {
            String instrucoes = "=== COMO JOGAR ===\n\n" +
                               "• Use as setas do teclado para mover a cobrinha\n" +
                               "• Coma os pontos vermelhos para crescer\n" +
                               "• Evite bater nas paredes ou em si mesma\n" +
                               "• Pressione ESC para sair do jogo\n\n" +
                               "Boa sorte!";
            
            JOptionPane.showMessageDialog(this, instrucoes, "Como Jogar", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private class JogoPanel extends JPanel {
        private ArrayList<Point> cobra;
        private Point comida;
        private int direcaoX = 1, direcaoY = 0;
        private int pontos = 0;
        private Timer gameTimer;
        private Random random;
        private boolean jogoAtivo = true;
        
        public JogoPanel() {
            setBackground(Color.BLACK);
            setFocusable(true);
            requestFocus();
            
            random = new Random();
            cobra = new ArrayList<>();
            
            // Posição inicial da cobra
            cobra.add(new Point(100, 100));
            
            // Gerar primeira comida
            gerarComida();
            
            // Configurar controles
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(!jogoAtivo) return;
                    
                    switch(e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if(direcaoY != 1) {
                                direcaoX = 0;
                                direcaoY = -1;
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if(direcaoY != -1) {
                                direcaoX = 0;
                                direcaoY = 1;
                            }
                            break;
                        case KeyEvent.VK_LEFT:
                            if(direcaoX != 1) {
                                direcaoX = -1;
                                direcaoY = 0;
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if(direcaoX != -1) {
                                direcaoX = 1;
                                direcaoY = 0;
                            }
                            break;
                        case KeyEvent.VK_ESCAPE:
                            voltarMenu();
                            break;
                    }
                }
            });
            
            // Iniciar o timer do jogo
            gameTimer = new Timer(VELOCIDADE_INICIAL, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(jogoAtivo) {
                        moverCobra();
                        verificarColisoes();
                        repaint();
                    }
                }
            });
            
            gameTimer.start();
        }
        
        private void gerarComida() {
            int x = random.nextInt(LARGURA_JOGO / TAMANHO_CELULA) * TAMANHO_CELULA;
            int y = random.nextInt(ALTURA_JOGO / TAMANHO_CELULA) * TAMANHO_CELULA;
            comida = new Point(x, y);
            
            // Verificar se a comida não está na cobra
            for(Point segmento : cobra) {
                if(segmento.equals(comida)) {
                    gerarComida();
                    return;
                }
            }
        }
        
        private void moverCobra() {
            Point cabeca = new Point(cobra.get(0));
            cabeca.x += direcaoX * TAMANHO_CELULA;
            cabeca.y += direcaoY * TAMANHO_CELULA;
            
            cobra.add(0, cabeca);
            
            // Verificar se comeu a comida
            if(cabeca.equals(comida)) {
                pontos++;
                gerarComida();
            } else {
                cobra.remove(cobra.size() - 1);
            }
        }
        
        private void verificarColisoes() {
            Point cabeca = cobra.get(0);
            
            // Verificar colisão com paredes
            if(cabeca.x < 0 || cabeca.x >= LARGURA_JOGO || 
               cabeca.y < 0 || cabeca.y >= ALTURA_JOGO) {
                gameOver();
                return;
            }
            
            // Verificar colisão com o próprio corpo
            for(int i = 1; i < cobra.size(); i++) {
                if(cabeca.equals(cobra.get(i))) {
                    gameOver();
                    return;
                }
            }
        }
        
        private void gameOver() {
            jogoAtivo = false;
            gameTimer.stop();
            
            // Salvar recorde
            salvarRecorde();
            
            String mensagem = "GAME OVER!\n\n" +
                             "Pontos: " + pontos + "\n" +
                             "Jogador: " + nomeJogador + "\n\n" +
                             "Pressione OK para voltar ao menu";
            
            JOptionPane.showMessageDialog(this, mensagem, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            voltarMenu();
        }
        
        private void salvarRecorde() {
            try {
                FileWriter writer = new FileWriter("recordes.txt", true);
                writer.write(String.format("%03dP - %s\n", pontos, nomeJogador));
                writer.close();
            } catch(IOException e) {
                System.err.println("Erro ao salvar recorde: " + e.getMessage());
            }
        }
        
        private void voltarMenu() {
            if(gameTimer != null) {
                gameTimer.stop();
            }
            cardLayout.show(containerPanel, "MENU");
            menuPanel.requestFocus();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            if(!jogoAtivo) return;
            
            // Desenhar bordas
            g.setColor(Color.WHITE);
            g.drawRect(0, 0, LARGURA_JOGO - 1, ALTURA_JOGO - 1);
            
            // Desenhar cobra
            g.setColor(Color.GREEN);
            for(int i = 0; i < cobra.size(); i++) {
                Point segmento = cobra.get(i);
                if(i == 0) {
                    // Cabeça da cobra
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(segmento.x, segmento.y, TAMANHO_CELULA, TAMANHO_CELULA);
            }
            
            // Desenhar comida
            g.setColor(Color.RED);
            g.fillRect(comida.x, comida.y, TAMANHO_CELULA, TAMANHO_CELULA);
            
            // Desenhar informações
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Pontos: " + pontos + " - " + nomeJogador, 10, ALTURA_JOGO + 30);
            
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.drawString("Use as setas para mover, ESC para sair", 10, ALTURA_JOGO + 50);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JogoCobrinha().setVisible(true);
        });
    }
}