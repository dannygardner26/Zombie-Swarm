import java.awt.Rectangle;

import javax.swing.JLabel;

/**
 * this class is an abstract class intended to represent
 * any interactive character or object which we will be
 * able to add to our game.
 * 
 */
public abstract class GameObject extends JLabel {
    public GameObject(int x, int y) {
        this.setLocation(x, y);
    }

    


    /**
     * 
     * determine if this object has collided with other
     * 
     * @param other GameObject with which we are detecting collision
     * @return true if collision is detected between this and other
     */
    public boolean hasCollidedWith(GameObject other) {
        Rectangle A = this.getBounds();
        Rectangle B = other.getBounds();
        return (A.intersects(B));


    }

    public abstract void update();
}
