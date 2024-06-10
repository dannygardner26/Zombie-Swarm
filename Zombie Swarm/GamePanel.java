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
import java.util.Random;



public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

    private BufferedImage background;
    private Hero hero;
    private static GamePanel tempGP;
    private ArrayList<Gun> gunList  = new ArrayList<Gun>();
    private ArrayList<Gun> gunListUpdate;

    private ArrayList<Zombie> zombieList;
    private int tickCounter;
    private int randomNum;
    private Gun gun;
    
        private Random random = new Random();



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
    private double coinThreshold;

    private double allMulti;

    
    private ImageIcon[] powerIcons;
    private ArrayList<PowerUps> powerList;
    private boolean reloading;
    private int timeAlive;
    private int powerUptimer;


    private Sounds seGun;
    private String coinse;
    private String gsound;
    private String reloads;
    private String zombieD;

    /**
     basic constructor and setup of fields to be used by GamePanel
     */
    public GamePanel() { 
        this.setLayout(null);
        tempGP = this;
        gunTimer = 0;
        this.timeAlive = 0;
        this.fireDelay = 0;
        powerList = new ArrayList<PowerUps>();
        firing = false;
        enemyTimer = 0;
        zombieList = new ArrayList<Zombie>(); 
        bulletList = new ArrayList<Bullet>();
        gunPics = new ArrayList<ImageIcon>();
        coinList = new ArrayList<Coin>();
        // gunList = new ArrayList<Gun>();
        gunListUpdate = new ArrayList<Gun>();
        
        

        this.healthMulti = 10;
        this.fireTimer = 100;
        this.allMulti = 1;
        this.coinThreshold = 5;



        seGun = new Sounds();
        coinse = "Zombie Swarm/images/coin-donation-2-180438.wav";
        zombieD = "Zombie Swarm/images/zombie-death-2-95167.wav";
        gsound = "Zombie Swarm/images/12-Gauge-Pump-Action-Shotgun-Close-Gunshot-D-www.fesliyanstudios.com - Copy.wav";
        reloads = "Zombie Swarm/images/1911-reload-6248.wav";

        for(int i = 0; i < 19; i++){
            gunPics.add(new ImageIcon("./Zombie Swarm/images/realGun" + i + ".png"));
        }
        
                             
                                  
        
       

        hero = new Hero(100, 100, tempGP);
        this.add(hero);
        hero.setVisible(true);
        //creation of all our guns
        Gun pistol = new Gun("Pistol", 0, 0, gunPics.get(0), 5, 10, 8, 8, this, hero);
        hero.addGun(pistol);
        gunListUpdate.add(pistol);

        Gun AssaultRifle = new Gun("Assault Rifle", 0, 0, gunPics.get(1), 5, 10,  30, 15, this, hero);
        gunList.add(AssaultRifle);
        Gun revolver = new Gun("Revolver", 0, 0, gunPics.get(2), 10, 10, 6, 5, this ,hero);
        gunList.add(revolver);  
        Gun burst = new Gun("Burst Rifle", 0, 0, gunPics.get(3), 15, 20, 30, 5, this,hero);
        gunList.add(burst);
        Gun deagalGun = new Gun("Deagal Pistol", 0, 0, gunPics.get(4), 20, 15, 3, 3, this ,hero);
        gunList.add(deagalGun);
        Gun stingerSMG = new Gun("Stinger Submachine Gun", 0,0, gunPics.get(5), 5, 15, 12, 15, this,hero);
        gunList.add(stingerSMG);
        Gun machineGun = new Gun("Machine Gun", 0,0, gunPics.get(6), 3, 50, 100, 30, this, hero);
        gunList.add(machineGun);
       
        Gun hunterRifleGun = new Gun("Hunter Sniper Rifle", 0, 0, gunPics.get(8), 75, 20, 1, 1, this,hero);
        gunList.add(hunterRifleGun);

        Gun dmrRifle = new Gun("Designated Marksman Rifle", 0, 0, gunPics.get(9), 15, 25, 12, 5, this,hero);
        gunList.add(dmrRifle);

        Gun boltactioGun = new Gun("Bolt Action Sniper Rifle", 0, 0, gunPics.get(10), 50, 30, 2, 2, this,hero);
        gunList.add(boltactioGun);

        Gun machinePistolGun = new Gun("Machine Pistol Gun", 0, 0, gunPics.get(11), 4, 24, 10, 20, this,hero);
        gunList.add(machinePistolGun);

        //Gun pistol = new Gun("Designated Marksman Rifle", 0, 0, gunPics.get(12), 4, 5, 3, 3, this,hero);
        //gunList.add(revolver);

        Gun drumGun = new Gun("Drum Gun Assualt Rifle", 0, 0, gunPics.get(13), 5, 5, 40, 25, this,hero);
        gunList.add(drumGun);

        Gun starblasterGun = new Gun("Blaster Assualt Rifle", 0, 0, gunPics.get(14), 4, 5, 20, 14, this,hero);
        gunList.add(starblasterGun);

        Gun submachineGun = new Gun("Submachine Gun", 0, 0, gunPics.get(15), 2, 20, 30, 25, this,hero);
        gunList.add(submachineGun);
        
        






       




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
        
        
        
        PowerUps ammoBoost = new PowerUps(400, 300, hero, powerIcons[0], "Ammo Boost", 0);
        powerList.add(ammoBoost);
        this.add(ammoBoost);
        ammoBoost.setVisible(true);

        PowerUps speedBoost = new PowerUps(200, 300, hero, powerIcons[1], "Speed Boost", 1);
        powerList.add(speedBoost);
        this.add(speedBoost);
        speedBoost.setVisible(true);

        PowerUps healthBoost = new PowerUps(100, 300, hero, powerIcons[2], "Health Boost", 2);
        powerList.add(healthBoost);
        this.add(healthBoost);
        healthBoost.setVisible(true);
        
        
        this.addMouseListener(this);
    // this key listener handles all key inputs
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                


                int code = e.getKeyCode();
                switch (code) {
                    case KeyEvent.VK_W: //handles movement up, while accounting for speed boost
                            
                            if(!hero.applySpeedBoost()) {
                                hero.setDy(-7);
                                hero.setDx(0);

                                
                            }
                            else{
                                hero.setDy(-5);
                                hero.setDx(0);
                            }
                            
                        hero.setDirection(new Direction(Direction.UP));                     
                        break;
                    case KeyEvent.VK_A://handles movement left, while accounting for speed boost
                           
                            if(!hero.applySpeedBoost()) {
                                hero.setDy(0);
                                hero.setDx(-7);

                                
                            }
                            else{
                                hero.setDy(0);
                                hero.setDx(-5);
                            }
                        hero.setDirection(new Direction(Direction.LEFT));
                        break;
                    case KeyEvent.VK_S:       //handles movement down, while accounting for speed boost                
                            hero.setDy(5);
                            hero.setDx(0);
                            if(!hero.applySpeedBoost()) {
                                hero.setDy(7);
                                hero.setDx(0);

                                
                            }
                            else{
                                hero.setDy(5);
                                hero.setDx(0);
                            }
                                                
                        hero.setDirection(new Direction(Direction.DOWN));
                        break;
                    case KeyEvent.VK_D://handles movement right, while accounting for speed boost
                            
                            if(!hero.applySpeedBoost()) {
                                hero.setDy(0);
                                hero.setDx(7);

                                
                            }
                            else{
                                hero.setDy(0);
                                hero.setDx(5);
                            }                             
                        hero.setDirection(new Direction(Direction.RIGHT));
                        break;

                    case KeyEvent.VK_Q: //switches the gun 1 to the left
                            hero.gunLeft();
                        break;
                    case KeyEvent.VK_E: //switches the gun 1 to the right
                            hero.gunRight();
                        break;
                    case KeyEvent.VK_R: // reloads gun
                            
                            if (!reloading) { //the !reloading is used to make sure that you cant spam reloading
                                reloading = true; 
                                hero.reload();
                                seGun.setFile(reloads);
                                seGun.play();
                                Timer reloadTimer = new Timer((int)(hero.getReloadTime() * 1000), new ActionListener() { //this timer 
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
            public void keyReleased(KeyEvent e) { //handles the stopping of movement whenever keys are released
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

       


      
        Timer gameLoop = new Timer(10, this);
        gameLoop.start();

        
        this.setFocusable(true);
        this.requestFocusInWindow();
    }


    /**
     * the code below is whats used to display information on the screen
     * the reload time ammo and gun are all displayed
     * this is constantly updated by using the repaint() method
     */



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


       

        int currentWidth = (int) ((hero.getHealth() / (double) hero.getMaxHealth()) * 100);


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


        //this code is used to spawn a gun whenever the bar fills up (past 100%)
        if(coinPercent > 1)
        {
            spawnGun();
            increaseThreshold();
        }


            int gunImgWidth =  hero.getGunPng().getIconWidth();
            int gunImgHeight = hero.getGunPng().getIconHeight();
            int xPos = this.getWidth() - gunImgWidth - 5;
            int yPos = this.getHeight() - gunImgHeight - 5;

            

            g.drawImage(hero.getGunPng().getImage(), 0, this.getHeight()-120, this);



        

    }   

    //main timer which loops through all updates and code
    @Override
    public void actionPerformed(ActionEvent e) { 
        hero.update();
        fireTimer-= hero.getFireRate();
        timeAlive++;
        this.repaint();

        allMulti += 0.0002;
        
       

        
        /**
         * this is a good example of the template we used to create things like powerups, coins, zombies, and guns on the screen
         * we called this template the isDone template
         * first, the code creates coins by using coinTimer, this creates a coin at a random point on the screen and makes it appear, as well as storing it into an arrayList
         * then, the code checks if any of the coins in the arrayList are "done", meaning they need to be removed from the screen
         */

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

        //update loop
        for(int i = 0; i < powerList.size(); i++)
        {
            powerList.get(i).update();
        }


        /**this is the code we used to implement a "hold to fire" shooting technique
         * first the code checks if the gun is firing and not reloading - this prevents it from shooting while reloading
         * then, the code checks if the gun CAN shoot, and then generates a bullet as such
         * this bullet is displayed and added to an arrayList as part of the "isDone" template"
         * 
        */
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


        /**
         * another example of the "isDone" template
         * spawns random zombies in random ammounts
         */

        enemyTimer++;
        if(enemyTimer > 200)
            {
                enemyTimer  = 0;
                for (int i = 0; i < (int)(Math.random()+1); i++){
                    healthMulti = (int)(allMulti * healthMulti);
                    Zombie temp = new Zombie((int)(Math.random()*this.getWidth()), (int)(Math.random()*this.getHeight()), hero, healthMulti, bulletList, this);
                    zombieList.add(temp);
                    this.add(temp);
                    temp.setVisible(true);
                }
            }

        //this code is the collision check for the gun pickups
        //when a gun gets picked up it gets added to the hero and removed from the screen
        for(int i = 0; i < gunList.size(); i++){
                if(hero.hasCollidedWith(gunList.get(i))){


                hero.addGun(gunList.get(i));
                gunList.get(i).setVisible(false);
                this.repaint();
                gunList.remove(i);
                i--;

            }
        }
       
        
            
        //another example of the "isDone" coding, randomyl spawning powerups across the screen
        powerUptimer++;
        if(powerUptimer>450){
            
            PowerUps temprandomPowerUp = powerList.get((int) (Math.random() * powerList.size()));
            this.add(temprandomPowerUp);
            temprandomPowerUp.setVisible(true);
            powerUptimer = 0;
        }

    

        
        //"isDone" coding, removing bullets if they have collided with a zombie, and updating them if not

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


        //update loops and "isDone" coding for zombie

        for(int i = 0; i < zombieList.size(); i++){
            zombieList.get(i).update();
            if(zombieList.get(i).isDone())
            {
                removeZombie(zombieList.get(i), i);
                i--;
            }
        }

        //"isDone" coding for coin and updates

        for (int i = 0; i < coinList.size(); i++){
            coinList.get(i).update(); 
            if(coinList.get(i).isDone()){
                removeCoin(coinList.get(i), i);
                i--;
            }
        }



    }

    

    
    //code for when a coin is collected, also calculates how many coins are needed each time to get a new gun

    public void coinCollected()
    {
        coins++;

        repaint();


        
    }

    public void increaseThreshold(){ //thsi code is used to independently make it harder to get guns each individual time
        double coinPercent = coins / (double)coinThreshold;
        if(coinPercent > 1){
            coinThreshold = Math.pow(coinThreshold, 1.1);
        }
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    @Override
    public void mousePressed(MouseEvent e) {  //whenever mouse is pressed, the gun tries to fire

        firing = true;
           
    }

    @Override
    public void mouseReleased(MouseEvent e) {   //whenever mouse ISNT pressed, gun doesnt fire
        firing = false;    
    }

    @Override
    public void mouseEntered(MouseEvent e) {        
    }

    @Override
    public void mouseExited(MouseEvent e) {     
    }

    public void spawnGun(){ // spawns a gun whenever the coin bar is full, picks a random gun and spawns it at a random point
        coins = 0;
        int ran = (int)(Math.random()*gunList.size());
        System.out.println(ran + "   " + gunList.size());
        Gun random = gunList.get(ran);
      

        random.setVisible(true);
        random.spawned(true);
        random.setLocation((int)((Math.random()*(getWidth()-160))+80), (int)((Math.random()*(getHeight()-80))+40));

        this.add(random);
        random.setVisible(true);


    }

// this code is used to play sounds whenever a zombie gets removed
    public void removeZombie(Zombie zombie, int i){
        zombieList.remove(i);
        seGun.setFile(zombieD);
        seGun.play();
        zombie.setVisible(false);

        remove(zombie);
    }
// this code is used to play sounds whenever a coin gets picked up

    public void removeCoin(Coin coin, int i){
        coinList.remove(i);
        coin.setVisible(false);
        seGun.setFile(coinse);
        seGun.play();
        remove(coin);
    }
// this code is used to remove a bullet whenever it hits something
    public void removeBullet(Bullet bullet, int i){
        bulletList.remove(i);
        bullet.setVisible(false);
        remove(bullet);
    }

    @Override //this code is used to update MouseX and MouseY whenever the mouse is moved
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override //actually updates the mouse position
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }
    
    public void reloading(boolean reloading){ //this code is used to set the reloading status
        this.reloading = reloading;
    }

    public void lose(){// this code is used to bring the player back to the main menu
        //TODO when this method runs make it bring back to main menu
    }
    //this code is used to randomly make coins and add them to a list - isDone template
    public void makeCoin (int x, int y){
        Coin temp = new Coin(x,y, hero, this);
        coinList.add(temp);
            this.add(temp);
            temp.setVisible(true);
    }
}




