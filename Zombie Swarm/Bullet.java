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
    /**
     * 
     * @param x bullet startring x
     * @param y bullet starting y
     * @param damage bullet damage
     * @param fireRate firerate of the gun thats shooting the bullet
     * @param mouseX bullet ending X
     * @param mouseY bullet ending Y
     * @param gp gamepanel
     */
    public Bullet(int x, int y, int damage, double fireRate, int mouseX, int mouseY, GamePanel gp)
    {
        super(x,y);
        this.setSize(4,4);
        
        iconBullet = new ImageIcon("./Zombie Swarm/images/BulletCircle.png");
        this.setIcon(iconBullet);
        
        this.damage = damage;
        
        this.fireRate = fireRate;
        this.done = false;
        

        double angle = Math.atan2(mouseY - y, mouseX - x); //gets the angle of launch for the bullet
        //calculates dx and dy exactly by using the Math.tan from above
        this.dx = (5 * Math.cos(angle)); 
        this.dy = (5 * Math.sin(angle));
        this.gp = gp;
        this.movingX = 0;
        this.movingY = 0;
    }

    @Override
    public void update() {
        //updates x and y independently
        updateX();
        updateY();
        //destorys the bullet once it leaves the screen
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
    /**
     * this was one of the first problems we encountered while coding this project
     * dy and dx being integer values would cause bullets shot at certain angles to "snap" and only shoot at like 30, 60, 90 degrees etc and not shoot true to the mouse
     * to counteract that, instead of trying to move 0.5 pixels every time, which gets shortened to 0, it iterates through movingX and increases it until its 1 at least
     */
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

    public void isDead(Boolean done){
        this.done = done;
    }

}
