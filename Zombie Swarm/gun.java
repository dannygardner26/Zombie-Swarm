import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Gun extends GameObject{

    private ImageIcon iconGun;
    private int damage;
    private int reloadSpeed;
    private int fireRate;
    private boolean visible;
    private String name;
    private ArrayList<Bullet> bulletList;
    private int ammo;
    private int reloadTimer;
    private int numBullets;

    public Gun(String name, int x, int y, ImageIcon icon, int damage, int reloadSpeed, int ammo, int fireRate, int numBullets){
        super(x,y);
        this.setSize(15,20);
        this.numBullets = numBullets;
        this.visible = false;
        iconGun = icon;
        this.damage = damage;
        this.reloadSpeed = reloadSpeed;
        this.name = name;
        this.ammo = ammo;
        this.reloadTimer = 0;
    }

    
    @Override
    public void update() {
       reloadTimer++;
    }


    public void reload(){

    }

    
    public int getAmmo(){
        return this.ammo;
    }

    public int getFireRate(){
        return fireRate;
    }

    public int getDamage(){
        return fireRate;
    }

    
    

    
}
