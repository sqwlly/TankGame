package sample;

import java.awt.*;

public abstract class Object {
    public int x, y, width, height;

    protected Object() { }

    protected Object(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public Object(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public abstract void draw(Graphics g);

    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }

}
