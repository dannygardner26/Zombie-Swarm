import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import java.awt.Font;

public class Main implements ActionListener {

    private JFrame frame;
    private JPanel titlescreen;
    private JButton playButton;

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu() {
        
        titlescreen = new JPanel();
        titlescreen.setBounds(100, 100, 500, 200);
        titlescreen.setBackground(Color.CYAN);

       
        frame = new JFrame("Shooting Targets");
        frame.setBounds(50, 50, 500, 500);

        JLabel titleLabel = new JLabel("Zombie Swarm");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        titleLabel.setBounds(170, 100, 200, 100);
        titlescreen.add(titleLabel);

        playButton = new JButton("Play");
        titlescreen.setLayout(null);
        playButton.setBounds(200, 200, 100, 50);
        playButton.setBackground(Color.RED);
        playButton.addActionListener(this);  

        
        titlescreen.add(playButton);
        frame.add(titlescreen); 
        frame.setResizable(false); 
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            GamePanel gamePanel = new GamePanel();
            frame.getContentPane().removeAll();
            frame.add(gamePanel);
            frame.revalidate();
            frame.repaint();
            gamePanel.requestFocusInWindow();
           }
    }
  }