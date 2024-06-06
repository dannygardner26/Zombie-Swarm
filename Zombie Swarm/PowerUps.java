import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PowerUps extends GameObject
{

    private ArrayList<String> powerUps;
    private Hero hero;
    private ImageIcon[] icon;

    public PowerUps(int x, int y, Hero hero)
    {
        super(x,y);
        this.hero = hero;
        powerUps = new ArrayList<String>();
        icon = new ImageIcon[3];
        icon[0] = new ImageIcon("Zombie Swarm/images/powerR.png");
        icon[1] = new ImageIcon("Zombie Swarm/images/ammo.png");
        icon[2] = new ImageIcon("Zombie Swarm/images/coinDoubler.png");
        
        powerUps.add("SpeedBoost");
        powerUps.add("AmmoBoost");
        powerUps.add("CoinBoost");

        int xt = (int)(Math.random()* powerUps.size());
        String temp = powerUps.get(xt);

        if(temp.equals("SpeedBoost")){
            this.setIcon(icon[0]);
        }
        if(temp.equals("AmmoBoost")){
            this.setIcon(icon[1]);
        }
        if(temp.equals("CoinBoost")){
            this.setIcon(icon[2]);
        }

       



   }

   
    public void update()
    {
        boolean collision = hero.hasCollidedWith(this);
        if (collision) {
            this.setVisible(false);
            
        }
    }

    }



    
    

