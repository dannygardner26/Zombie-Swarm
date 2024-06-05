import javax.swing.ImageIcon;

public class Coin extends GameObject {

    private ImageIcon icon;
    private Hero hero;
    private int coinCount;
    private boolean done;

    public Coin(int x, int y, Hero hero) {
        super(x, y);
        //this.setLocation(x,y);
        this.setSize(16, 16);
        icon = new ImageIcon("./Zombie Swarm/images/Coinreal.png");
        this.setIcon(icon);
        this.hero = hero;
        this.done = false;
    }



    public void update(){
        boolean collision = hero.hasCollidedWith(this);
        if(collision){
            coinCount++;
            this.setVisible(false);
        }

        
    }

    public boolean isDone(){
        return done;
    }

}