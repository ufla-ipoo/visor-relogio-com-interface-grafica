import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Uma interface gráfica de usuário (GUI) simples para o visor de relógio.
 * Nesta implementa, o tempo é acelerado a 3 minutos por segundo, aproximadamente,
 * de forma que o teste do display seja mais rápido.
 * 
 * Traduzido por Julio César Alves - 2023.09.15
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Relogio
{
    private JFrame quadro;
    private JLabel rotulo;
    private VisorDeRelogio visor;
    private boolean relogioFuncionando = false;
    private TimerThread temporizador;
    
    /**
     * Construtor para objetos da classe Relogio
     */
    public Relogio()
    {
        construirQuadro();
        visor = new VisorDeRelogio();
    }
    
    /**
     * 
     */
    private void iniciar()
    {
        relogioFuncionando = true;
        temporizador = new TimerThread();
        temporizador.start();
    }
    
    /**
     * 
     */
    private void parar()
    {
        relogioFuncionando = false;
    }
    
    /**
     * 
     */
    private void passo()
    {
        visor.tiqueTaque();
        rotulo.setText(visor.obterHora());
    }
    
    /**
     * Função 'Sobre': mostra a caixa 'sobre'.
     */
    private void mostrarSobre()
    {
        JOptionPane.showMessageDialog (quadro, 
                    "Relógio Versão 1.0\n" +
                    "Uma interface simples para o projeto visor de relógio do livro de Barnes e Kölling",
                    "Sobre o relógio", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Função sair: sai da aplicação
     */
    private void sair()
    {
        System.exit(0);
    }

    
    /**
     * Cria o quadro e seu conteúdo
     */
    private void construirQuadro()
    {
        quadro = new JFrame("Relogio");
        JPanel paneilConteudo = (JPanel)quadro.getContentPane();
        paneilConteudo.setBorder(new EmptyBorder(1, 60, 1, 60));

        makeMenuBar(quadro);
        
        // Specify the layout manager with nice spacing
        paneilConteudo.setLayout(new BorderLayout(12, 12));
        
        // Create the image pane in the center
        rotulo = new JLabel("00:00", SwingConstants.CENTER);
        Font displayFont = rotulo.getFont().deriveFont(96.0f);
        rotulo.setFont(displayFont);
        //imagePanel.setBorder(new EtchedBorder());
        paneilConteudo.add(rotulo, BorderLayout.CENTER);

        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(1, 0));
        
        JButton startButton = new JButton("Iniciar");
        startButton.addActionListener(e -> iniciar());
        toolbar.add(startButton);
        
        JButton stopButton = new JButton("Parar");
        stopButton.addActionListener(e -> parar());
        toolbar.add(stopButton);

        JButton stepButton = new JButton("Passo");
        stepButton.addActionListener(e -> passo());
        toolbar.add(stepButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);
        
        paneilConteudo.add(flow, BorderLayout.SOUTH);
        
        // building is done - arrange the components      
        quadro.pack();
        
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        quadro.setLocation(d.width/2 - quadro.getWidth()/2, d.height/2 - quadro.getHeight()/2);
        quadro.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     * 
     * @param frame   The frame that the menu bar should be added to.
     */
    private void makeMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("Arquivo");
        menubar.add(menu);
        
        item = new JMenuItem("Sobre o relógio...");
            item.addActionListener(e -> mostrarSobre());
        menu.add(item);

        menu.addSeparator();
        
        item = new JMenuItem("Sair");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(e -> sair());
        menu.add(item);
    }
    
    class TimerThread extends Thread
    {
        public void run()
        {
            while (relogioFuncionando) {
                passo();
                pause();
            }
        }
        
        private void pause()
        {
            try {
                Thread.sleep(300);   // pause for 300 milliseconds
            }
            catch (InterruptedException exc) {
            }
        }
    }
}