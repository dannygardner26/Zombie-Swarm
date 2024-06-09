
import javax.swing.JFrame;

public class Main {
    

    String sound;
   
    public static void main(String[] args) {

        
        
       

        JFrame frame = new JFrame("Shooting Targets");
        frame.setBounds(50, 50, 500, 500);

        // makes it impossible to resize the frame
        frame.setResizable(false);

        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);

        


        frame.setVisible(true);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

 
    
}
