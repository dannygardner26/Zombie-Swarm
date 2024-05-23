import javax.swing.ImageIcon;

public class Gun extends GameObject {


    private ImageIcon[][] iconGun;
    public Gun(int x, int y){
        super(x,y);
        this.setSize(15,20);

        iconGun = new ImageIcon[4][5];

        for(int i = 0; i<iconGun.length; i++){
            for(int j= 0; j<iconGun[0].length; j++){
                if(i==3 && j==4){
                    iconGun[i][j]= null;
                }
                iconGun[i][j]= new ImageIcon("./images/");
            }
        }
    }


    
    

}