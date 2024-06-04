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

        double angle = Math.atan2(mouseY - y, mouseX - x);

        this.dx = (int)(5 * Math.cos(angle));
        this.dy = (int)(5 * Math.sin(angle));


    }

    @Override
    public void update() {
        this.setLocation(this.getX() + dx, this.getY() + dy);
    }

}
