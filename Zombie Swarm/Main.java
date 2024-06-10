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

    private JFrame frame; //frame needed for bath menu screen and game panel 
    private JPanel titlescreen; // the panel of the menu screen, similair to Gamepanel 
    private JButton playButton; //button to press play that takes actionperformed to get game panel 

    
  //essential method that creates both the gamepanel and menu screen to show up on the frame, and makes button work as intended 
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

    //Makes gamepanel show up once button is pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            GamePanel gamePanel = new GamePanel();
            frame.getContentPane().removeAll();
            frame.add(gamePanel);
            frame.revalidate();//Source ryiSnow youtube tutor: recalculate layout and often called once new components are added or removed
            frame.repaint();
            gamePanel.requestFocusInWindow();
           }
    }
    public static void main(String[] args) {
      //intilizes new Main() class and calls menu() method, creating the frame with play button and title. After actionPerformed content from frame is removed and GamePanel is shown
      Main main = new Main();
      main.menu();
  }
  }