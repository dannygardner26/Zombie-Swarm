import javax.swing.ImageIcon;

public class Gun{

    private ImageIcon iconGun;

    public Gun(ImageIcon icon, int damage, double bulletSpeed, int reloadSpeed){
        super(x,y);
        this.setSize(15,20);

        iconGun = new ImageIcon[4][5];

        
        
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