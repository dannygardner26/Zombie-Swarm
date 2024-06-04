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

    public Gun(String name, int x, int y, ImageIcon icon, int damage, int reloadSpeed, int ammo, int fireRate){
        super(x,y);
        this.setSize(15,20);

        this.visible = false;
        iconGun = icon;
        this.damage = damage;
        this.reloadSpeed = reloadSpeed;
        this.name = name;
        this.ammo = ammo;
    }

    
    @Override
    public void update() {
        
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

    
    

    // public void fire(int x, int y, int mouseX, int mouseY){
        
    //     bulletList.add(new Bullet(x,y, damage, fireRate, bulletSpeed,x,y));

        


    // }
}
