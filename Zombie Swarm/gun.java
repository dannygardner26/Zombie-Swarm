import javax.swing.ImageIcon;

public class Gun{

    private ImageIcon iconGun;
    private int damage
    private int bulletSpeed
    private int reloadSpeed





    public Gun(ImageIcon icon, int damage, double bulletSpeed, int reloadSpeed){
        super(x,y);
        this.setSize(15,20);

        iconGun = new ImageIcon[4][5];
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.reloadSpeed = reloadSpeed;
        
        
    }

    
    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void reload(){

    }

    public void fire(){

    }
}