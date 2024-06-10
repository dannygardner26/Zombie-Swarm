import java.util.ArrayList;

import javax.swing.ImageIcon;


public class PowerUps extends GameObject
{

    private ArrayList<String> powerUps;
    private Hero hero;
    private ImageIcon icon;
    private String name;
    private int id;
    private boolean done;
/**
 * 
 * @param x
 * @param y
 * @param hero
 * @param icon
 * @param name
 * @param id
 */
    public PowerUps(int x, int y, Hero hero, ImageIcon icon, String name, int id)
    {
        super(x,y);
        this.hero = hero;
        this.icon = icon;
        
        this.name = name;
        this.id = id;
        this.done = false;
        this.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.setIcon(icon);
       
       



   }
  

    
    public void update()
    {
        
        boolean collision = this.hasCollidedWith(hero);
        if (collision && isVisible()) {
            this.setVisible(false);
            this.isDone(true);
            switch (id) {
                case 0:
                hero.applyAmmoBoost(20); 
                    break;
                case 1:
                    
                    hero.setMultiplier(2.0); 
                    break;
                case 2:
                    hero.heal(1000000); 
                    break;
            }
    
        }
            

        

    }

    public void isDone(boolean done){
        this.done = done;
    }

    public boolean getDone(){
        return done;
    }

    

    }



    
    

