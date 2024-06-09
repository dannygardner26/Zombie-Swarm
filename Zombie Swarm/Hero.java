import java.util.ArrayList;
import java.util.Timer;


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
    private int gunIndex;

    
    private double speedMultiplier;
    private int maxAmmo;
    private double coinMultiplier;

    private double maxHealth;
    private double health;

    public Hero(int x, int y, GamePanel gp) {
        super(x, y);
        this.setSize(32, 36);
        direction = Direction.DOWN;
        phase = 0;
        phaseCounter = 0;
        dx = 0;
        dy = 0;
        gunIndex = 0;
        speedMultiplier = 0.0;
        this.gun = null;
        maxHealth = 10;
        health = 10;

        this.gp = gp;
        this.gunList = new ArrayList<Gun>();
        
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
    if(gunList.size() > 0)
        this.gun = gunList.get(gunIndex);
        if(gun != null)
        gun.update();

        if (dx != 0 || dy != 0) {
            if(this.getY()<=0 && dy<0){
                this.setLocation(this.getX() ,this.getY()- dy );
            }
            if(this.getX()<=0 && dx<0){
                this.setLocation(this.getX()-dx,this.getY());

            }
            if(this.getY() + this.getHeight()>=gp.getHeight() && dy>0){
                this.setLocation(this.getX(),this.getY()-dy);

            }
            if(this.getX() + this.getWidth()-20>=gp.getWidth() && dx>0){
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

    
    
    

    public int getAmmo(){
        
        if(gun != null){
            return gun.getAmmo();
            }
            else
                return 0;
    }
    public int getMaxAmmo(){
        if(gun != null){
            return gun.getMaxAmmo();
            }
            else
                return 0;
        }
    

    public int useAmmo(){
        return gun.getAmmo();
    }

    public String getName(){
        if(gun != null){
        return gun.getName();
        }
        else
            return "null";
    }

    public double getFireRate(){
        if(gun != null)
        return gun.getFireRate();
        return 0;
    }

    public int getDamage(){
        return gun.getDamage();
    }

    public void addGun(Gun gun){
        gunList.add(gun);

        this.gun = gunList.get(gunIndex);
    }

    public ImageIcon getGunPng(){
        return gun.getPng();
    }

    public void gunRight(){
        gunIndex = (gunIndex + 1) % gunList.size();
        

    }

    public void gunLeft(){
        gunIndex = (gunIndex - 1);
        
        if(gunIndex < 0){
            gunIndex = gunList.size()-1;
        }
   }

    public void reload(){
        gun.reload();
        gp.repaint();

    }
    public void fire(){
        if (gun.getAmmo() > 0) {
        gun.fire();
        gp.repaint();
        }
    }

    public double getReloadTime(){
        if(gun!= null){
        if (gun.isReloading())
            return (double) gun.getReloadTime() / 10.0;

        else
            return 0.0;
        }
        return 0;

    }
    public boolean applySpeedBoost() {
        
        if(speedMultiplier!=0 ){
            return false;
        }
        return true;

        
    }
    public void setMultiplier(double multiplier)
    {
        speedMultiplier = multiplier;
    }   
 public double getMultiplier(){
        
        return speedMultiplier;
    }

    public void resetSpeedBoost() {
        this.speedMultiplier = 1.0;
    }

    public void applyAmmoBoost(int extraAmmo) {
        gun.gunsetAmmo(extraAmmo);
        
    }

    public void resetAmmoBoost(int extraAmmo) {
        this.maxAmmo -= extraAmmo;
    }

    public void applyCoinBoost(double multiplier, int duration) {
        this.coinMultiplier = multiplier;
        
    }

    private void resetCoinBoost() {
        this.coinMultiplier = 1.0;
    }


    public void hurt(int dmg){
        if(health - dmg < 0)
        {
            gp.lose();
            System.out.println("LOSS");
        }
        else
        {
            health -= dmg;
        }
    }
    public void heal(int heal) {
        health += heal;
        if (health > maxHealth) {
                health = maxHealth;
        }
    }


    public double getHealth(){
        return health;
    }

    public double getMaxHealth(){
        return maxHealth;
    }


}
