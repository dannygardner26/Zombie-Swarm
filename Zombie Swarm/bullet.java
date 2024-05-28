import javax.swing.ImageIcon;

public class Bullet extends GameObject{

    private int damage;
    private ImageIcon iconBullet;
    private int fireRate;
    private double bulletSpeed;


    public Bullet(int damage, int fireRate, double bulletSpeed, int x, int y)
    {
        this.damage = damage;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;

        iconBullet = new ImageIcon("./images/BulletCircle.png");

        super(x,y);
        this.setSize(4,4);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}