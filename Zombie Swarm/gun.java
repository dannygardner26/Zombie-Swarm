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
    private boolean exists;
    private int testTick;

    public Gun(String name, int x, int y, ImageIcon icon, int damage, int reloadSpeed, int ammo, double fireRate, GamePanel gp, Hero hero){
        super(x,y);
        this.setSize(80,40);
        reloadTime = 0;
        this.setVisible(false);
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
        this.exists = false;
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



        if(reloadTime > reloadSpeed){
            if(ammo<maxAmmo){
                ammo = maxAmmo;
            }
            reloadTime = 0;
            isReloading = false;
            reloadTimer.stop();
            }

            
            
            boolean collision = hero.hasCollidedWith(this);
            
            testTick++;
            if(testTick>30){
            System.out.println("Collision" + collision);
            System.out.println("Visible" + exists);
            testTick = 0;
            }


            if (collision && this.isVisible()) {
                done = true;
                System.out.println("collidedANDvisible");
                
            }
    }


    public void reload(){
        if(!isReloading){
        isReloading = true;
        reloadTime = reloadSpeed;
        reloadTimer.start();
        }
        
        
    }

    
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

    public void fire(){
        ammo--;
    }
    
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
    
    public void exists(boolean exists){
        this.exists = exists;
    }

}

