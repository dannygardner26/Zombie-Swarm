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
    private GamePanel gp;
    
/**
 * 
 * @param x x-position
 * @param y y-position
 * @param hero hero object for collison 
 * @param gp gamepanel to make accesibile 
 * icon array is intilized through for loop, multiple imges for the animation, timer is for animation
 */
    public Coin(int x, int y, Hero hero, GamePanel gp) {
        super(x, y);
        this.setSize(16, 16);
        this.gp = gp;
        
        icons = new ImageIcon[6];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for(int j = 0; j<3; j++){
                icons[index] = new ImageIcon("./Zombie Swarm/images/coin_" + i +"_" + j +".png");
                index++;
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

   //Animate coin using same logic as hero to make coin spin using images
    public  void updateIcon() {
        phase = (phase + 1) % icons.length;
        this.setIcon(icons[phase]);
    }
    //For the loss() in gamePanel to stop animation after hero is dead
    public void coinTimer(){
        coinTimer.stop();
    } 
    //This is for the coin collison should disappear once coin is collected, timer also stops
    public void update() {
        boolean collision = hero.hasCollidedWith(this);
        if (collision) {
            coinCount++;
            this.setVisible(false);
            coinTimer.stop();
            done = true;
            gp.coinCollected();
        }
    }
    //Checks to see if coin is invisbile, then removed to prevent lag 
    public boolean isDone() {
        return done;
    }
    //Return coin amount
    public int getCoins(){
        return coinCount;
    }
}
