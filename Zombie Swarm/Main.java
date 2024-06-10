
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main implements ActionListener{
    

    
   
    public static void main(String[] args) {

        
        


        
       
        JPanel titlescreen = new JPanel();
        titlescreen.setBounds(100,100,500,200);
        titlescreen.setBackground(Color.CYAN);
        
        JFrame frame = new JFrame("Shooting Targets");
        frame.setBounds(50, 50, 500, 500);

        

        JButton playButton =  new JButton("Play");
        playButton.setLayout(null);
        playButton.setBounds(250,300,100,100);
        playButton.setLocation(200,200);
        playButton.setBackground(Color.RED);
        

        titlescreen.add(playButton);
        frame.add(titlescreen);


        // makes it impossible to resize the frame
        frame.setResizable(false);
       

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        

        


        frame.setVisible(true);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

 
    
}
