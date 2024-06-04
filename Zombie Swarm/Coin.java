import javax.swing.ImageIcon;

public class Coin extends GameObject {

    private ImageIcon icon;
    private Hero hero;

    public Coin(int x, int y, Hero hero) {
        super(x, y);
        this.setSize(16, 16);
        icon = new ImageIcon("Zombie Swarm/images/Designer (2).png");
        this.setIcon(icon);
        this.hero = hero;
    }

    public void update(){
        
    }

}