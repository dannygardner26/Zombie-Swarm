import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PowerUps extends GameObject
{

    private ArrayList<String> powerUps;
    private Hero hero;
    private ImageIcon icon;
    private String name;
    private int id;
    public PowerUps(int x, int y, Hero hero, ImageIcon icon, String name, int id)
    {
        super(x,y);
        this.hero = hero;
        this.icon = icon;
        this.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.name = name;
        this.id = id;
        
        this.setIcon(icon);

       



   }

    
    public void update()
    {
        
        boolean collision = this.hasCollidedWith(hero);
        if (collision) {
            this.setVisible(false);
            
            switch (id) {
                case 0:
                    hero.applySpeedBoost(2.0, 5000); 
                    break;
                case 1:
                    hero.applyAmmoBoost(100, 5000); 
                    break;
                case 2:
                    hero.applyCoinBoost(2.0, 5000); 
                    break;
            }
    
        }
            

        

    }
    

    }



    
    

