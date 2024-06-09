import javax.swing.ImageIcon;

public class Bullet extends GameObject{

    private int damage;
    private ImageIcon iconBullet;
    private double fireRate;
    private int bulletSpeed;
    private double dx;
    private double dy;
    private boolean done;
    private GamePanel gp;
    private double movingX;
    private double movingY;

    public Bullet(int x, int y, int damage, double fireRate, int mouseX, int mouseY, GamePanel gp)
    {
        super(x,y);
        this.setSize(4,4);
        
        iconBullet = new ImageIcon("./Zombie Swarm/images/BulletCircle.png");
        this.setIcon(iconBullet);
        
        this.damage = damage;
        
        this.fireRate = fireRate;
        this.done = false;
        

        double angle = Math.atan2(mouseY - y, mouseX - x);

        this.dx = (5 * Math.cos(angle));//find a way to add decimals and make it so that the horiz/vert movement is seperates
        this.dy = (5 * Math.sin(angle));// this will allow for it to shoot left and right fast, but up and down slow in certain cases
        this.gp = gp;
        this.movingX = 0;
        this.movingY = 0;
    }

    @Override
    public void update() {

        updateX();
        updateY();

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

    public void updateX(){
        movingX += dx;

        
        if((int)movingX!=0)
        {
            this.setLocation(this.getX() + (int)movingX, this.getY());
            movingX = movingX - (int)movingX;
        }
    }
    public void updateY(){
        movingY += dy;
        if((int)movingY!=0)
        {
            this.setLocation(this.getX(), this.getY() + (int)movingY);
            movingY = movingY - (int)movingY;
        }
    }


    public int getDamage(){
        return damage;
    }




    public boolean isDone(){
        return done;
    }

    public void isDead(Boolean baka){
        this.done = baka;
    }

}
