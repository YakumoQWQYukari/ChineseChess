package code.board;

import javafx.scene.shape.Circle;

public class Point extends Circle {
    private int x;
    private int y;

    public Point(double centerX, double centerY, double radius, int x, int y) {
        super(centerX, centerY, radius);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int b) {
        this.y = y;
    }
}
