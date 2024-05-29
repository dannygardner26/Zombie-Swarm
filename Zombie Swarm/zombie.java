

import javax.swing.ImageIcon;


public class Zombie extends GameObject {

    private ImageIcon[][] icons;
    private int phase;
    private int animationCounter;
    private Direction direction;

    public Zombie(int x, int y) { //this constructor provides the parameter to create a target
        super(x, y); 
        this.phase = 0;
        this.setSize(32, 28);
        this.animationCounter = 0;
        this.direction = new Direction(0);

        icons = new ImageIcon[4][3];


        for (int i = 0; i < icons.length; i++) {
            for (int j = 0; j < icons[i].length; j++) {
                icons[i][j] = new ImageIcon("./images/sprite_" + i + "_" + j + ".png");

            }
        }
    
        this.setIcon(icons[direction.getDirection()][0]);
    }

    @Override
    public void update() {
        updateIcon();
        animationCounter++;
        this.setIcon(icons[direction.getDirection()][animationCounter%3]);



    }
    private void updateIcon() {
        animationCounter++;
    
        }
    
    
    
    
}
