import javax.swing.ImageIcon;

public class Bullet extends GameObject{

    private int damage;
    private ImageIcon iconBullet;
    private int fireRate;
    private int bulletSpeed;
    private int dx;
    private int dy;
    private boolean done;
    private GamePanel gp;

    public Bullet(int x, int y, int damage, int fireRate, int mouseX, int mouseY, GamePanel gp)
    {
        super(x,y);
        this.setSize(4,4);
        
        iconBullet = new ImageIcon("./Zombie Swarm/images/BulletCircle.png");
        this.setIcon(iconBullet);
        
        this.damage = damage;
        this.fireRate = fireRate;
        this.done = false;
        

        double angle = Math.atan2(mouseY - y, mouseX - x);

        this.dx = (int)(5 * Math.cos(angle));
        this.dy = (int)(5 * Math.sin(angle));
        this.gp = gp;

    }

    @Override
    public void update() {
        this.setLocation(this.getX() + dx, this.getY() + dy);
        if(this.getY()<=0 && dy<0){
            done = true;
        }
        if(this.getX()<=0 && dx<0){
            done = true;
        }
        if(this.getY() + this.getHeight()>=gp.getHeight() && dy>0){
            done = true;
        }
        if(this.getX() + this.getWidth()>=gp.getWidth() && dx>0){
            done = true;
        }




    }

    public boolean isDone(){
        return done;
    }

}
