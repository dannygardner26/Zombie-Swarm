
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Zombie extends GameObject {

    private ImageIcon[][] icons;
    private int phase;
    private int animationCounter;
    private Direction direction;
    private Boolean done;
    private Hero hero;
    private double dx;
    private double dy;
    private double movingX;
    private double movingY;
    private double hp;
    private ArrayList<Bullet> bulletList;
    private GamePanel gp;
    private int phaseCounter;
    private boolean readyToHit;
    private int hitTimer;
    /**
     * 
     * @param x x of the zombie
     * @param y y of the zombie
     * @param hero hero
     * @param healthMulti health multiplier for the zombie so it gets harder as time goes on
     * @param bulletList list of bullets for zombie to compare itself against
     * @param gp GamePanel
     */
    public Zombie(int x, int y, Hero hero, int healthMulti, ArrayList<Bullet> bulletList, GamePanel gp) { //this constructor provides the parameter to create a target
        super(x, y); 
        this.phase = 0;
        this.hitTimer = 0;
        this.readyToHit = true;
        this.setSize(32, 28);
        this.animationCounter = 0;
        this.direction = new Direction(0);
        this.done = false;
        this.phaseCounter = 0;
        icons = new ImageIcon[4][3];
        this.hero = hero;
        this.gp = gp;
        this.movingX = 0;
        this.movingY = 0;
        this.dy = 0;
        this.dx = 0;
        this.hp = healthMulti;
        this.bulletList = bulletList;


        for (int i = 0; i < icons.length; i++) {
            for (int j = 0; j < icons[i].length; j++) {
                icons[i][j] = new ImageIcon("./Zombie Swarm/images/sprite_" + i + "_" + j + ".png");

            }
        }
    
        this.setIcon(icons[direction.getDirection()][0]);
    }

    @Override
    public void update() {
        updateIcon();
        //this code is used to animate through the icons of the zombie to make them animate walking
        animationCounter++;
        if (animationCounter >= icons[0].length * 10) { 
            animationCounter = 0;
        }
        this.setIcon(icons[direction.getDirection()][animationCounter/10]);

        //same logic as used for bullet to calculate trajectory, just is recalculated every single time
        double angle = Math.atan2(hero.getY() - this.getY(),hero.getX() - this.getX() );

        this.dx = (  Math.cos(angle));
        this.dy = (  Math.sin(angle));
        //same logic for bullets - update them seperately to account for loss of data due to int
        updateX();
        updateY();

        // this code is used to see if a bullet has hit a zombie yet
        // if bullet hits a zombie, takes away health based on the damage assigned to a bullet through the gun
        //once the zombie dies, it has a chance to spawn a coin, then is removed from screen
        for(int i = 0; i < bulletList.size(); i++){
            if(this.hasCollidedWith(bulletList.get(i))){
                bulletList.get(i).isDead(true);
                hp = hp - bulletList.get(i).getDamage();
                if(hp <= 0){
                    if((int)(Math.random()*100) > 40)
                    {
                        gp.makeCoin(this.getX(), this.getY());
                    }
                    this.die();
                }
            }
        }
        //this code below is used to calculate how the zombies will hit the hero, and how fast the damage will be
        //we used a hit timer to make sure that the zombie cant destory the hero instantly
        hitTimer++;
        if(hitTimer > 50)
            readyToHit = true;

        if(this.hasCollidedWith(hero) && readyToHit)
        {
            hero.hurt(1);
            gp.repaint();
            hitTimer = 0;
            readyToHit = false;
        }



    }
    //the seperated x and y update methods using MovingX and movingY to find out how far its actually moving each update
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






//this code is used to find what way the zombie should be facing based on how its moving

    private void updateIcon() {
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    this.direction = new Direction(Direction.RIGHT);
                } 
                else {
                    this.direction = new Direction(Direction.LEFT);
                    }
            } 
            else {
                if (dy > 0) {
                    this.direction = new Direction(Direction.DOWN);
                } 
                else {
                    this.direction = new Direction(Direction.UP);
                }
        }
        
        }
    
    public boolean isDone(){
            return done;
        }
    public void die(){
        this.done = true;
    }
    
    
}
