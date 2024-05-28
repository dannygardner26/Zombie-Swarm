import javax.swing.ImageIcon;

public class Bullet extends GameObject{

    private int damage;
    private ImageIcon iconBullet;
    private int fireRate;
    private double bulletSpeed;

    public Bullet(int damageL, int fireRate, double bulletSpeed)
    {
        this.damage = damageL;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;

        iconBullet = new ImageIcon("./images/hero_" + i + "_" + j + ".png");

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}