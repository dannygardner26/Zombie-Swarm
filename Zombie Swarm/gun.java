import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

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
    private int reloadTimer;

    public Gun(String name, int x, int y, ImageIcon icon, int damage, int reloadSpeed, int ammo, double fireRate){
        super(x,y);
        this.setSize(15,20);

        this.visible = false;
        iconGun = icon;
        this.damage = damage;
        this.reloadSpeed = reloadSpeed;
        this.name = name;
        this.ammo = ammo;
        this.reloadTimer = 0;
        this.fireRate = fireRate;
        this.maxAmmo = ammo;
    }

    
    @Override
    public void update() {
       reloadTimer++;
    }


    public void reload(){
        ammo = maxAmmo;
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
    
    

    
}
