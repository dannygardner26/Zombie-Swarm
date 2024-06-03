import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class Gun extends GameObject{

    private ImageIcon iconGun;
    private int damage;
    private double bulletSpeed;
    private int reloadSpeed;
    private boolean visible;
    private String name;



    public Gun(String name, int x, int y, ImageIcon icon, int damage, double bulletSpeed, int reloadSpeed){
        super(x,y);
        this.setSize(15,20);

        this.visible = false;
        iconGun = icon;
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.reloadSpeed = reloadSpeed;
        this.name = name;
        
    }

    
    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void reload(){

    }





    public void fire(int x, int y){


        


    }
}
