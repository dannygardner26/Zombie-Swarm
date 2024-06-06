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
        this.name = name;
        this.id = id;
        
        switch (id) {
            case 0:
                                   
                break;
            case 1:
                                       
                break;
            case 2:
                                       
                break;
        }

       



   }

   
    public void update()
    {
        boolean collision = hero.hasCollidedWith(this);
        if (collision) {
            this.setVisible(false);
            
            switch (id) {
                case 0:
                        //what you do with speed boost after pickup DONT FORGET HOW LONG IT LASTS - one parameter for multi and one for lenth              
                    break;
                case 1:
                        // what ytou do with ammo boost after pickup - method in hero class that sets maxAmmo to big ah number, then stops after a timer or a certain amount of ticks
                        // measure ticks using for loops          AND LENGTH    
                    break;
                case 2:
                        // what you do with coin boost after pickup - add a coinMulti variable to multi coins on pickup in coin class/gamepanel AND LENGTH             
                    break;
            }
    
        }

        

    }

    }



    
    

