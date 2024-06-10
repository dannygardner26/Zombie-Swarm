import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gun extends GameObject{

    private ImageIcon iconGun;
    private int damage;
    private int reloadSpeed;
    private double fireRate;
    private String name;
    private ArrayList<Bullet> bulletList;
    private int ammo;
    private int maxAmmo;
    private Timer reloadTimer;
    private int reloadTime;
    private GamePanel gp;
    private boolean isReloading;
    private boolean done;
    private Hero hero;
    private boolean spawned;
    private int testTick;
    /**
     * 
     * @param name the name of the gun
     * @param x x of the gun pickup
     * @param y y of the gun pickup
     * @param icon icon of the gun pickup/display icon
     * @param damage damage of the gun 
     * @param reloadSpeed reloadSpeed of the gun
     * @param ammo max ammo of the gun
     * @param fireRate firerate of the gun
     * @param gp gamepanel
     * @param hero hero
     */
    public Gun(String name, int x, int y, ImageIcon icon, int damage, int reloadSpeed, int ammo, double fireRate, GamePanel gp, Hero hero){
        super(x,y);
        this.setSize(80,40);
        reloadTime = 0;
        iconGun = icon;
        this.gp = gp;
        testTick = 0;
        this.hero = hero; 
        this.damage = damage;
        this.reloadSpeed = reloadSpeed;
        this.name = name;
        this.ammo = ammo;
        this.reloadTime = 0;
        this.fireRate = fireRate;
        this.spawned = false;
        this.maxAmmo = ammo;
        this.isReloading = false;
        this.done = false;
        reloadTimer = new Timer(100, new ActionListener() { 
            
            public void actionPerformed(ActionEvent e) {
                reloadTick();
            }
        });
        this.setIcon(icon);
    }

    
    @Override
    public void update() {


        //this code handles the reload and making sure that it displays the time properly/reloads properly
        if(reloadTime > reloadSpeed){
            if(ammo<maxAmmo){
                ammo = maxAmmo;
            }
            reloadTime = 0;
            isReloading = false;
            reloadTimer.stop();
            }

            
            
            
    }

    //another class helping indicate that the gun is reloading to prevent shooting while reloading
    public void reload(){
        if(!isReloading){
        isReloading = true;
        reloadTime = reloadSpeed;
        reloadTimer.start();
        }
        
        
    }

    //getters and setters
    public int getAmmo(){
        return this.ammo;
    }

    public ImageIcon getPng(){
        return iconGun;
    }

    public int getMaxAmmo(){
        return maxAmmo;
    }

    public double getFireRate(){
        return this.fireRate;
    }

    public int getDamage(){
        return this.damage;
    }

    public String getName(){
        return this.name;
    }
    //removes 1 ammo everytime the gun is fired
    public void fire(){
        ammo--; 
    }
    //counts how long inbetween each reload, and helps display it on the screen
    public void reloadTick(){
        if (reloadTime > 0) {
            reloadTime--;
        } else {
            reloadTimer.stop();
            if(ammo<maxAmmo){
                ammo = maxAmmo;
            }
            reloadTime = 0;
            isReloading = false;
        }
        gp.repaint();
    }

    public int getReloadTime(){
        return reloadTime;
    }
    
    public boolean isReloading(){
        return isReloading;
    }
    public void gunsetAmmo(int ammo){
        this.ammo += ammo;
    }

    public boolean getDone(){
        return done;
    }
    
    public void spawned(boolean spawned){
        this.spawned = spawned;
    }

    
}

