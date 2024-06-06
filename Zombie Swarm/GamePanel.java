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
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class will house all game objects which are part of the
 * simulation. You must add several targets which can be
 * hit by fireballs and removed from the game.
 */

public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

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
    private ArrayList<Coin> coinList;
    private int enemyTimer;
    private int enemySpawnRate;
    private int coinTimer;
    private int healthMulti;
    private int fireTimer;
    private int fireDelay;
    private Boolean firing;
    private int mouseX;
    private int mouseY;
    private int coins;
    private int gunTimer;
    private PowerUps power;
    private ImageIcon[] powerIcons;
    private ArrayList<PowerUps> powerList;
    
    
    /**
     
     */
    public GamePanel() {
        this.setLayout(null);
        tempGP = this;
        gunTimer = 0;
        this.fireDelay = 0;
        randomNumScramble();
        powerList = new ArrayList<PowerUps>();
        firing = false;
        enemyTimer = 0;
        zombieList = new ArrayList<Zombie>();
        bulletList = new ArrayList<Bullet>();
        gunPics = new ArrayList<ImageIcon>();
        coinList = new ArrayList<Coin>();
        gunList = new ArrayList<Gun>();
        this.healthMulti = 1;
        this.fireTimer = 0;
        for(int i = 0; i < 19; i++){
            gunPics.add(new ImageIcon("./images/realGun" + i + ".png"));
        }
        Gun pistol = new Gun("Pistol", 0, 0, gunPics.get(0), 5, 10, 8, 5);
        gunList.add(pistol);
        Gun AssaultRifle = new Gun("Assault Rifle", 0, 0, gunPics.get(1), 5, 10,  30, 20);
        gunList.add(AssaultRifle);
        Gun revolver = new Gun("Revolver", 0,0, gunPics.get(2), 10, 10, 6, 2);
        gunList.add(revolver);









        enemySpawnRate = 2;
        coinTimer = 0;
        mouseX = 0;
        mouseY = 0;
        this.addMouseMotionListener(this);
        coins = 0;

        URL imageURL = getClass().getResource("./images/backgrounddetailed2.png");

        try {
            background = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        powerIcons = new ImageIcon[3];


        for(int i = 0; i < 3; i++){
            ImageIcon tempImage = new ImageIcon("Zombie Swarm/images/power_" + i + ".png"); 
            powerIcons[i] = tempImage;  
        }
        PowerUps speedBoost = new PowerUps(200, 200, hero, powerIcons[0], "Speed Boost", 0);
        powerList.add(speedBoost);
        PowerUps ammoBoost = new PowerUps(200, 200, hero, powerIcons[1], "Ammo Boost", 1);
        powerList.add(ammoBoost);
        PowerUps coinBoost = new PowerUps(200, 200, hero, powerIcons[2], "Coin Boost", 2);
        powerList.add(coinBoost);

        

        hero = new Hero(100, 100, tempGP, pistol);
        this.add(hero);
        hero.setVisible(true);

        
        

       
        
        

            
        


       


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

                    case KeyEvent.VK_Q:
                            hero.gunLeft();
                        break;
                    case KeyEvent.VK_E:
                            hero.gunRight();
                        break;
                    case KeyEvent.VK_R:
                            hero.reload();
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
                    case KeyEvent.VK_Q:
                        
                        break;
                    case KeyEvent.VK_E:
                        
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
        fireTimer++;

        coinTimer++;
        if (coinTimer > 200){
            int rany = (int)(Math.random()*this.getHeight());
            int ranx = (int)(Math.random()*this.getWidth());
            Coin temp = new Coin(ranx,rany, hero, this);
            coinList.add(temp);
            this.add(temp);
            temp.setVisible(true);
            this.coinTimer = 0;
        }
        for(int i = 0; i < powerList.size(); i++)
        {
            powerList.get(i).update();
        }

        if(firing){
            if(fireTimer > hero.getFireRate()){
                if(hero.getAmmo() > 0){
                fireTimer = 0;
                Bullet temp = new Bullet(hero.getX(),hero.getY(), hero.getDamage(), hero.getFireRate(),  mouseX, mouseY, this);
                this.add(temp);
                bulletList.add(temp);
                temp.setVisible(true);
                hero.fire();
                }
            } 
        }
        enemyTimer++;
        if(enemyTimer > 200)
            {
                enemyTimer  = 0;
                for (int i = 0; i < (int)(Math.random()*enemySpawnRate+1); i++){
                    Zombie temp = new Zombie((int)(Math.random()*this.getWidth()), (int)(Math.random()*this.getHeight()), hero, healthMulti, bulletList);
                    zombieList.add(temp);
                    this.add(temp);
                    temp.setVisible(true);
                }
            }

        gunTimer++;
        if(gunTimer > 1000)
            {
                hero.addGun(gunList.get((int)(Math.random()*gunList.size())));
            }


        for(int i = 0; i < bulletList.size(); i++){
            if(bulletList.get(i).isDone())
            {
                removeBullet(bulletList.get(i), i);
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
                removeZombie(zombieList.get(i), i);
                i--;
            }
        }

        for (int i = 0; i < coinList.size(); i++){
            coinList.get(i).update(); 
            if(coinList.get(i).isDone()){
                removeCoin(coinList.get(i), i);
                i--;
            }
        }



    }

    public void randomNumScramble(){
        this.randomNum = (int)(Math.random()*100+100);


    }

    public void coinCollected()
    {
        coins++;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // if(fireTimer > hero.getFireRate()){
        //     fireTimer = 0;
        //     Bullet temp = new Bullet(hero.getX(),hero.getY(), hero.getDamage(), hero.getFireRate(),  e.getX(), e.getY(), this);
        //     this.add(temp);
        //     bulletList.add(temp);
        //     temp.setVisible(true);
        // }





    }

    @Override
    public void mousePressed(MouseEvent e) {  

        firing = true;

            
    }

    @Override
    public void mouseReleased(MouseEvent e) {   
        firing = false;    
    }

    @Override
    public void mouseEntered(MouseEvent e) {        
    }

    @Override
    public void mouseExited(MouseEvent e) {     
    }

    public void removeZombie(Zombie zombie, int i){
        zombieList.remove(i);
        zombie.setVisible(false);
        remove(zombie);
    }

    public void removeCoin(Coin coin, int i){
        coinList.remove(i);
        coin.setVisible(false);
        remove(coin);
    }

    public void removeBullet(Bullet bullet, int i){
        bulletList.remove(i);
        bullet.setVisible(false);
        remove(bullet);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }
}
