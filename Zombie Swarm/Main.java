
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    

    
   
    public static void main(String[] args) {

        JPanel menu = new JPanel();
        menu.setBackground(Color.DARK_GRAY);
        


        
       
        JPanel titlescreen = new JPanel();
        titlescreen.setBounds(100,100,500,200);
        titlescreen.setBackground(Color.CYAN);
        
        JFrame frame = new JFrame("Shooting Targets");
        frame.setBounds(50, 50, 500, 500);
        

        // makes it impossible to resize the frame
        frame.setResizable(false);
        frame.add(menu);

        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        

        


        frame.setVisible(true);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

 
    
}
