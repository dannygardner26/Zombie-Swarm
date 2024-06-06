import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PowerUps extends GameObject
{

    private ArrayList<String> powerUps;
    private Hero hero;
    private ImageIcon icon;

    public PowerUps(int x, int y, Hero hero)
    {
        super(x,y);
        this.hero = hero;
        powerUps = new ArrayList<String>();



   }

   public String determinePower()
   {
        powerUps.add("SpeedBoost");
        powerUps.add("AmmoBoost");
        powerUps.add("CoinBoost");

        int x = (int)(Math.random()* powerUps.size());
        return powerUps.get(x);


   }

    public void update(){
        boolean collision = hero.hasCollidedWith(this);
        if (collision) {
            this.setVisible(false);
            
        }
    }

    }



    
    

