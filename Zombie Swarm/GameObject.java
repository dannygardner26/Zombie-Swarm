import java.awt.Rectangle;

import javax.swing.JLabel;


public abstract class GameObject extends JLabel {
    public GameObject(int x, int y) {
        this.setLocation(x, y);
    }

    


    public boolean hasCollidedWith(GameObject other) {
        Rectangle A = this.getBounds();
        Rectangle B = other.getBounds();
        return (A.intersects(B));


    }

    public abstract void update();
}
