

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



    public Zombie(int x, int y, Hero hero, int healthMulti, ArrayList<Bullet> bulletList) { //this constructor provides the parameter to create a target
        super(x, y); 
        this.phase = 0;
        this.setSize(32, 28);
        this.animationCounter = 0;
        this.direction = new Direction(0);
        this.done = false;
        icons = new ImageIcon[4][3];
        this.hero = hero;
        this.movingX = 0;
        this.movingY = 0;
        this.dy = 0;
        this.dx = 0;
        this.hp = healthMulti * 10;
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
        animationCounter++;
        this.setIcon(icons[direction.getDirection()][animationCounter%3]);

        double angle = Math.atan2(hero.getY() - this.getY(),hero.getX() - this.getX() );

        this.dx = (  Math.cos(angle));//find a way to add decimals and make it so that the horiz/vert movement is seperates
        this.dy = (  Math.sin(angle));// this will allow for it to shoot left and right fast, but up and down slow in certain cases

        updateX();
        updateY();

        for(int i = 0; i < bulletList.size(); i++){
            if(this.hasCollidedWith(bulletList.get(i))){
                bulletList.get(i).isDead(true);
                hp = hp - bulletList.get(i).getDamage();
                if(hp <= 0){
                    this.die();
                }
            }
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







    private void updateIcon() {
        animationCounter++;
    
        }
    
    public boolean isDone(){
            return done;
        }
    public void die(){
        this.done = true;
    }
    
    
}
