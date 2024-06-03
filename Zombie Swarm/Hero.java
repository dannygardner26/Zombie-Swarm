import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Hero extends GameObject {

    private ImageIcon[][] icons;
    private int phase;
    private int direction;
    private int phaseCounter;
    private int dx;
    private int dy;
    private GamePanel gp;
    private Gun gun;
    private ArrayList<Gun> gunList;

    public Hero(int x, int y, GamePanel gp, ArrayList<Gun> list) {
        super(x, y);
        this.setSize(32, 36);
        direction = Direction.DOWN;
        phase = 0;
        phaseCounter = 0;
        dx = 0;
        dy = 0;
        this.gp = gp;
        this.gunList = list;
        this.gun = list.get(0);

        icons = new ImageIcon[4][3];

        for (int i = 0; i < icons.length; i++) {
            for (int j = 0; j < icons[i].length; j++) {
                icons[i][j] = new ImageIcon("./Zombie Swarm/images/hero" + i + "_" + j + ".png");

            }
        }
        
        this.setIcon(icons[direction][phase]);
        


    }

    /**
     * Change direction of the character
     * 
     * @param direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction.getDirection();
    }

    /**
     * update the icon to animate the character
     */
    private void updateIcon() {
        if (phaseCounter % 6 == 0) {
            phase = (phase + 1) % icons[direction].length; // Update phase
            this.setIcon(icons[direction][phase]);
        }
        phaseCounter++;
    }

    /**
     * reset character in idle position
     */
    public void setIdle() {
        phase = 0;
        this.setIcon(icons[direction][phase]);
        phaseCounter = 0;
    }

    /**
     * set dx for the character to make him move horizontally
     * 
     * @param dx horizontal velocity
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDx()
    {
        return dx;
    }
    public int getDy()
    {
        return dy;
    }

    /**
     * set dy for the character to make him move vertically
     * 
     * @param dy vertical velocity
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * update the character's location and image based on the dx and dy
     */
    public void update() {
        if (dx != 0 || dy != 0) {
            if(this.getY()<=0 && dy<0){
                this.setLocation(this.getX(),this.getY()- dy );
            }
            if(this.getX()<=0 && dx<0){
                this.setLocation(this.getX()-dx,this.getY() );
            }
            if(this.getY() + this.getHeight()>=gp.getHeight() && dy>0){
                this.setLocation(this.getX(),this.getY()-dy);
            }
            if(this.getX() + this.getWidth()>=gp.getWidth() && dx>0){
                this.setLocation(this.getX()-dx, this.getY());
            }
            
            this.setLocation(this.getX() + dx, this.getY() + dy);
            this.updateIcon();

        }
    }

    public int getDirection()
    {
        return direction;
    }

    public void gunPickup(Gun gun)
    {
        this.gun = gun;
    }
    
    public void fire(x,y)
    {
        gun.fire();
    }
}