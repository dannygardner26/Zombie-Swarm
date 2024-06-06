

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

    public Zombie(int x, int y, Hero hero) { //this constructor provides the parameter to create a target
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


        
        updateX();
        updateY();


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
