import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Gun extends GameObject{

    private ImageIcon iconGun;
    private int damage;
    private int bulletSpeed;
    private int reloadSpeed;
    private int fireRate;
    private boolean visible;
    private String name;
    private ArrayList<Bullet> bulletList;
    private int ammo;

    public Gun(String name, int x, int y, ImageIcon icon, int damage, int bulletSpeed, int reloadSpeed, int ammo, int fireRate){
        super(x,y);
        this.setSize(15,20);

        this.visible = false;
        iconGun = icon;
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.reloadSpeed = reloadSpeed;
        this.name = name;
        ammo = ammo;
    }

    
    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void reload(){

    }





    public void fire(int x, int y){
        
        bulletList.add(new Bullet(x,y, damage, fireRate, bulletSpeed));

        


    }
}
