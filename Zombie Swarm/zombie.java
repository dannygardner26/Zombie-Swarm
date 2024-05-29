

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
        randomValScramble();

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
        if (animationCounter == 0)
            phase = 0;
        if (animationCounter == 10)
            phase = 1;
        if (animationCounter == 20)
            phase = 2;
        if (animationCounter == 30)
            phase = 3;
        if (animationCounter == 40)
            phase = 0;
        if (animationCounter > randomVal)
        {
            animationCounter = 0;
            phase = 0;
            randomValScramble();
        }
        
        }
    
    public void randomValScramble()
    {
        this.randomVal = (int)((Math.random()*150) + 100);
    }
    
    
}
