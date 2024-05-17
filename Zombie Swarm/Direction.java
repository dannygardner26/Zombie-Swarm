/**
 * This class is intended to make handling
 * direction consistent across classes
 */

 public class Direction {
    public static final int DOWN = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int LEFT = 3;

    private int direction;

    public Direction(int direction) {
        if (direction < 0 || direction > 3) {
            throw new IllegalArgumentException("direction must be in range 0 to 3");
        }
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
