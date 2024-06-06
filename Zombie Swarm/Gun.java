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
    private boolean visible;
    private String name;
    private ArrayList<Bullet> bulletList;
    private int ammo;
    private int maxAmmo;
    private Timer reloadTimer;
    private int reloadTime;
    

    public Gun(String name, int x, int y, ImageIcon icon, int damage, int reloadSpeed, int ammo, double fireRate){
        super(x,y);
        this.setSize(15,20);
        reloadTime = 0;
        this.visible = false;
        iconGun = icon;
        this.damage = damage;
        this.reloadSpeed = reloadSpeed;
        this.name = name;
        this.ammo = ammo;
        this.reloadTime = 0;
        this.fireRate = fireRate;
        this.maxAmmo = ammo;
        
        reloadTimer = new Timer(100, new ActionListener() { 
            
            public void actionPerformed(ActionEvent e) {
                reloadTick();
            }
        });
        reloadTimer.start();
    }

    
    @Override
    public void update() {
       
    }


    public void reload(){
        if(reloadTime > reloadSpeed){
        ammo = maxAmmo;
        reloadTime = 0;
        }
        
    }

    
    public int getAmmo(){
        return this.ammo;
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
        reloadTime++;
    }
    

    
}

