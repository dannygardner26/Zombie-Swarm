import javax.swing.ImageIcon;

public class Bullet extends GameObject{

    private int damage;
    private ImageIcon iconBullet;
    private int fireRate;
    private int bulletSpeed;
    private int dx;
    private int dy;


    public Bullet(int x, int y, int damage, int fireRate, int bulletSpeed)
    {
        super(x,y);

        this.damage = damage;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;

        iconBullet = new ImageIcon("./images/BulletCircle.png");

        this.setSize(4,4);
    }

    @Override
    public void update() {
        
    }

}
