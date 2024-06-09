import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
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
    private double coins;
    private int gunTimer;
    private double coinThreshold;

    private double allMulti;

    
    private ImageIcon[] powerIcons;
    private ArrayList<PowerUps> powerList;
    private boolean reloading;
    private int timeAlive;


    private Sounds seGun;
    private String coinse;
    private String gsound;
    private String reloads;
    private String zombieD;

    /**
     
     */
    public GamePanel() {
        this.setLayout(null);
        tempGP = this;
        gunTimer = 0;
        this.timeAlive = 0;
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
        this.healthMulti = 10;
        this.fireTimer = 100;
        this.allMulti = 1;
        this.coinThreshold = 15;


        seGun = new Sounds();
        coinse = "Zombie Swarm/images/coin-donation-2-180438.wav";
        zombieD = "Zombie Swarm/images/zombie-death-2-95167.wav";
        gsound = "Zombie Swarm/images/12-Gauge-Pump-Action-Shotgun-Close-Gunshot-D-www.fesliyanstudios.com - Copy.wav";
        reloads = "Zombie Swarm/images/1911-reload-6248.wav";

        for(int i = 0; i < 19; i++){
            gunPics.add(new ImageIcon("./images/realGun" + i + ".png"));
        }
        Gun pistol = new Gun("Pistol", 0, 0, gunPics.get(0), 5, 10, 8, 8, this);
        gunList.add(pistol);
        Gun AssaultRifle = new Gun("Assault Rifle", 0, 0, gunPics.get(1), 5, 10,  30, 15, this);
        gunList.add(AssaultRifle);
        Gun revolver = new Gun("Revolver", 0,0, gunPics.get(2), 10, 10, 6, 5, this);
        gunList.add(revolver);
        Gun burst = new Gun("Burst Rifle", 0,0, gunPics.get(3), 7, 10, 6, 8, this);
        gunList.add(burst);
        Gun deagalGun = new Gun("Deagal Pistol", 0,0, gunPics.get(4), 10, 10, 3, 3, this);
        gunList.add(deagalGun);
        Gun stingerSMG = new Gun("Stinger Submachine Gun", 0,0, gunPics.get(5), 3, 5, 24, 15, this);
        gunList.add(stingerSMG);
        Gun machineGun = new Gun("Machine Gun", 0,0, gunPics.get(6), 1, 30, 60, 30, this);
        gunList.add(machineGun);
        //Gun greGun = new Gun("Revolver", 0,0, gunPics.get(7), 10, 10, 6, 2, this);
        //gunList.add(greGun);
        Gun hunterRifleGun = new Gun("Hunter Sniper Rifle", 0,0, gunPics.get(8), 10, 5, 1, 1, this);
        gunList.add(hunterRifleGun);

        Gun dmrRifle = new Gun("Designated Marksman Rifle", 0,0, gunPics.get(9), 4, 5, 3, 3, this);
        gunList.add(dmrRifle);

        Gun boltactioGun = new Gun("Bolt Action Sniper Rifle", 0,0, gunPics.get(10), 10, 7, 2, 2, this);
        gunList.add(boltactioGun);

        Gun machinePistolGun = new Gun("Machine Pistol Gun", 0,0, gunPics.get(11), 3, 4, 10, 17, this);
        gunList.add(machinePistolGun);

        //Gun pistol = new Gun("Designated Marksman Rifle", 0,0, gunPics.get(12), 4, 5, 3, 3, this);
        //gunList.add(revolver);

        Gun drumGun = new Gun("Drum Gun Assualt Rifle", 0,0, gunPics.get(13), 3, 5, 15, 19, this);
        gunList.add(drumGun);

        Gun starblasterGun = new Gun("Blaster Assualt Rifle", 0,0, gunPics.get(13), 5, 5, 14, 10, this);
        gunList.add(starblasterGun);

        Gun submachineGun = new Gun("Submachine Gun", 0,0, gunPics.get(13), 2, 8, 30, 23, this);
        gunList.add(submachineGun);
        







        hero = new Hero(100, 100, tempGP, pistol);
        this.add(hero);
        hero.setVisible(true);




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
        powerList = new ArrayList<PowerUps>();

        
        //Zombie Swarm/images/power_2.png
        for (int i = 0; i < 3; i++) {
            ImageIcon tempImage = new ImageIcon("Zombie Swarm/images/power_" + i + ".png");
            powerIcons[i] = tempImage;
        }
        
        
        PowerUps ammoBoost = new PowerUps(400,300, hero, powerIcons[0], "Ammo Boost", 0);
        powerList.add(ammoBoost);
        powerList.get(0).setVisible(true); 
        this.add(ammoBoost); 
        //powerList.add(speedBoost);
        

        PowerUps speedBoost = new PowerUps(200, 200, hero, powerIcons[1], "Speed Boost", 1);
        powerList.add(speedBoost);
        ammoBoost.setVisible(true); 
        this.add(speedBoost); 

        PowerUps coinBoost = new PowerUps(20, 200, hero, powerIcons[2], "Coin Boost", 2);
        powerList.add(coinBoost);
        coinBoost.setVisible(true); 
        this.add(coinBoost); 
        
        
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
                            
                            if(hero.applySpeedBoost()) {
                                hero.setDy(-10);
                                hero.setDx(0);

                                
                            }
                            else{
                                hero.setDy(-5);
                            hero.setDx(0);
                            }
                            
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
                            // hero.reload();
                            // seGun.setFile(reloads);
                            // seGun.play();
                            if (!reloading) { 
                                reloading = true; 
                                hero.reload();
                                seGun.setFile(reloads);
                                seGun.play();
                                Timer reloadTimer = new Timer((int)(hero.getReloadTime() * 1000), new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        reloading = false; // Reset the reloading flag
                                        ((Timer)e.getSource()).stop();
                                    }
                                });
                                reloadTimer.setRepeats(false);
                                reloadTimer.start();
                            }
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
        // as the action listener which will be triggered every 10 milliseconds
        Timer gameLoop = new Timer(10, this);
        gameLoop.start();

        
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 14));
        g.drawString(hero.getName(), 10, this.getHeight()-50);
        g.drawString("Ammo: " + hero.getAmmo() + "/" + hero.getMaxAmmo(), 10, this.getHeight()-10);
        g.drawString("Reload Time: " + hero.getReloadTime(), 10, this.getHeight()-30);
        g.drawString("Score: " + (coins*100 +timeAlive), this.getWidth()-100, 20);


        // g.drawImage(hero.getGunPng(), 40, 40);
         // Example: Draw power-ups
         
        double healthPercent = hero.getHealth() / hero.getMaxHealth();
        int currentWidth = (int) ((healthPercent) * 100);


        g.setColor(Color.RED);
        g.fillRect(10,10, 100, 10);
        g.setColor(Color.GREEN);
        g.fillRect(10, 10, currentWidth, 10);
        g.setColor(Color.WHITE);
        g.drawRect(10, 10, 100, 10);


        double coinPercent = coins / (double)coinThreshold;
        int currentCoinWidth = (int) ((coinPercent) * 100);


        g.setColor(Color.WHITE);
        g.fillRect(10,30, 100, 10);
        g.setColor(Color.YELLOW);
        g.fillRect(10, 30, currentCoinWidth, 10);
        g.setColor(Color.BLACK);
        g.drawRect(10, 30, 100, 10);
        }

    


    @Override
    public void actionPerformed(ActionEvent e) {
        hero.update();
        fireTimer-= hero.getFireRate();
        timeAlive++;
        this.repaint();

        allMulti += 0.0002;
        
        for(int i = 0; i < powerList.size(); i++){
            if(powerList.get(i).getDone()){
                powerList.remove(i);
                i--;
            }
        }

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

        for(int i = 0; i < coinList.size(); i++){
            if(coinList.get(i).isDone())
            {
                coinList.get(i).setVisible(false);
                coinList.remove(i);
                i--;


            }
        }


        for(int i = 0; i < powerList.size(); i++)
        {
            powerList.get(i).update();
        }

        if(firing && !reloading){
            if(fireTimer < 0){
                if(hero.getAmmo() > 0){
                fireTimer = 100;
                Bullet temp = new Bullet(hero.getX()+16,hero.getY()+18, hero.getDamage(), hero.getFireRate(),  mouseX, mouseY, this);
                this.add(temp);
                bulletList.add(temp);
                temp.setVisible(true);
                hero.fire();
                seGun.setFile(gsound);
                seGun.play();
                }
            } 
        }
        enemyTimer++;
        if(enemyTimer > 200)
            {
                enemyTimer  = 0;
                for (int i = 0; i < (int)(Math.random()*enemySpawnRate*allMulti+1); i++){
                    healthMulti = (int)(allMulti * healthMulti);
                    Zombie temp = new Zombie((int)(Math.random()*this.getWidth()), (int)(Math.random()*this.getHeight()), hero, healthMulti, bulletList, this);
                    zombieList.add(temp);
                    this.add(temp);
                    temp.setVisible(true);
                }
            }

        
            
        gunTimer++;
        if(gunTimer > 100)
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

        System.out.println("collceted");
        System.out.println(coins);
        repaint();


        double coinPercent = coins / (double)coinThreshold;
        if(coinPercent > 1){
            coinThreshold = Math.pow(coinThreshold, 1.1);
        }
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
        seGun.setFile(zombieD);
        seGun.play();
        zombie.setVisible(false);

        remove(zombie);
    }

    public void removeCoin(Coin coin, int i){
        coinList.remove(i);
        coin.setVisible(false);
        seGun.setFile(coinse);
        seGun.play();
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
    
    public void reloading(boolean reloading){
        this.reloading = reloading;
    }

    public void lose(){
        //TODO when this method runs make it bring back to main menu
    }
    

}
