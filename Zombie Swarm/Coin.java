import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Coin extends GameObject {

    private ImageIcon[] icons;
    private Hero hero;
    private int coinCount;
    private boolean done;
    private int phase;
    private Timer coinTimer;
    

    public Coin(int x, int y, Hero hero) {
        super(x, y);
        this.setSize(16, 16);

        
        icons = new ImageIcon[6];
        
        for (int i = 0; i < 2; i++) {
            for(int j = 0; j<3; j++){
                icons[i] = new ImageIcon("./Zombie Swarm/images/coin_" + i +"_" + j +".png");
            } 
            
        }

        
        phase = 0;
        this.setIcon(icons[phase]);

        this.hero = hero;
        this.done = false;

        
        coinTimer = new Timer(100, new ActionListener() { 
            
            public void actionPerformed(ActionEvent e) {
                updateIcon();
            }
        });
        coinTimer.start();
    }

   
    private void updateIcon() {
        phase = (phase + 1) % icons.length;
        this.setIcon(icons[phase]);
    }

    public void update() {
        boolean collision = hero.hasCollidedWith(this);
        if (collision) {
            coinCount++;
            this.setVisible(false);
            coinTimer.stop();
        }
    }

    public boolean isDone() {
        return done;
    }
}
