import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class will house all game objects which are part of the
 * simulation. You must add several targets which can be
 * hit by fireballs and removed from the game.
 */

public class GamePanel extends JPanel implements ActionListener, MouseListener{

    private BufferedImage background;
    private Hero hero;
    private static GamePanel tempGP;
    private ArrayList<Gun> gunList;
    private ArrayList<Zombie> zombieList;
    private int tickCounter;
    private int randomNum;
    private Gun gun;
    private Coin coin;
    private ArrayList<Bullet> bulletList;
    private ArrayList<ImageIcon> gunPics;
    private int enemyTimer;
    private int enemySpawnRate;
    /**
     
     */
    public GamePanel() {
        this.setLayout(null);
        tempGP = this;
        randomNumScramble();
        enemyTimer = 0;
        zombieList = new ArrayList<Zombie>();
        bulletList = new ArrayList<Bullet>();
        gunPics = new ArrayList<ImageIcon>();
        gunList = new ArrayList<Gun>();
        for(int i = 0; i < 19; i++){
            gunPics.add(new ImageIcon("./images/realGun" + i + ".png"));
        }
        Gun pistol = new Gun("pistol", 0, 0, gunPics.get(0), 5, 10, 8, 50);
        gunList.add(pistol);
        Gun AssaultRifle = new Gun("pistol", 0, 0, gunPics.get(1), 5, 10,  30, 200);
        gunList.add(AssaultRifle);
        enemySpawnRate = 2;
        

        URL imageURL = getClass().getResource("./images/backgrounddetailed2.png");

        try {
            background = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        

        

        hero = new Hero(100, 100, tempGP, gunList);
        this.add(hero);
        hero.setVisible(true);
        
/* 
        int x = (int)(Math.random()*10);
        int rany = (int)(Math.random()*this.getHeight());
        int ranx = (int)(Math.random()*this.getWidth());
        for(int i= 0; i<x; i++){
            coin = new Coin(ranx,rany, hero);
            this.add(coin);
        }
        */

            coin = new Coin(200,200, hero);
            this.add(coin);
        


       


        this.addMouseListener(this);
    
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                


                int code = e.getKeyCode();
                switch (code) {
                    case KeyEvent.VK_W:
                            hero.setDy(-5);
                            hero.setDx(0); 
                        hero.setDirection(new Direction(Direction.UP));                     
                        break;
                    case KeyEvent.VK_A:
                            hero.setDy(0);
                            hero.setDx(-5);
                        hero.setDirection(new Direction(Direction.LEFT));
                        break;
                    case KeyEvent.VK_S:                       
                            hero.setDy(5);
                            hero.setDx(0);                      
                        hero.setDirection(new Direction(Direction.DOWN));
                        break;
                    case KeyEvent.VK_D:
                            hero.setDy(0);// moving up
                            hero.setDx(5);// horizontal v Vertical mutually exclusive                              
                        hero.setDirection(new Direction(Direction.RIGHT));
                        break;

                    case KeyEvent.VK_SPACE:
                        
                        break;
                             
                    
                }
            }
            

            @Override
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {
                    case KeyEvent.VK_W:
                        hero.setDy(0);
                        hero.setIdle();
                        break;
                        
                    case KeyEvent.VK_A:
                        hero.setDx(0);
                        hero.setIdle();
                        break;
                    case KeyEvent.VK_S:
                        hero.setDy(0);
                        hero.setIdle();
                        break;
                    case KeyEvent.VK_D:
                        hero.setDx(0);
                        hero.setIdle();
                        break;
                }
            }

        });

       


        // create and start the game timer. This gamepanel is passed
        // as the action listener which will be triggered every 17 milliseconds
        Timer gameLoop = new Timer(10, this);
        gameLoop.start();

        
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        hero.update();
        coin.update();
        
        enemyTimer++;
        if(enemyTimer > 10)
            {
                enemyTimer  = 0;
                for (int i = 0; i < (int)(Math.random()*enemySpawnRate+1); i++){
                    Zombie temp = new Zombie((int)(Math.random()*this.getWidth()), (int)(Math.random()*this.getHeight()));
                    zombieList.add(temp);
                    this.add(temp);
                    temp.setVisible(true);
                }
            }




        for(int i = 0; i < bulletList.size(); i++){
            if(bulletList.get(i).isDone())
            {
                bulletList.remove(i);
                i--;
            }
            else
            {
                bulletList.get(i).update();
            }
        }

        for(int i = 0; i < zombieList.size(); i++){
            zombieList.get(i).update();
            if(zombieList.get(i).isDone())
            {
                zombieList.remove(i);
                i--;
            }
        }

        tickCounter++;

        if(tickCounter > randomNum){
            int x = (int)(Math.random()*400+50);
            int y = (int)(Math.random()*400+50);
            Zombie temp = new Zombie(x,y);
            zombieList.add(temp);
            randomNumScramble();
            tickCounter = 0;
            this.add(temp);
            temp.setVisible(true);
        }



    }

    public void randomNumScramble(){
        this.randomNum = (int)(Math.random()*100+100);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        Bullet temp = new Bullet(hero.getX(),hero.getY(), hero.getDamage(), hero.getFireRate(),  e.getX(), e.getY(), this);
        this.add(temp);
        bulletList.add(temp);
        temp.setVisible(true);





    }

    @Override
    public void mousePressed(MouseEvent e) {       
    }

    @Override
    public void mouseReleased(MouseEvent e) {       
    }

    @Override
    public void mouseEntered(MouseEvent e) {        
    }

    @Override
    public void mouseExited(MouseEvent e) {     
    }

    

    

}
