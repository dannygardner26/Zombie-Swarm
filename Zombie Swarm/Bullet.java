import javax.swing.ImageIcon;

public class Bullet extends GameObject{

    private int damage;
    private ImageIcon iconBullet;
    private int fireRate;
    private int bulletSpeed;
    private int dx;
    private int dy;


    public Bullet(int x, int y, int damage, int fireRate, int mouseX, int mouseY)
    {
        super(x,y);

        this.damage = damage;
        this.fireRate = fireRate;
        

        iconBullet = new ImageIcon("./images/BulletCircle.png");

        this.setIcon(iconBullet);
        this.setSize(4,4);
    }

    @Override
    public void update() {
         
    }

}
