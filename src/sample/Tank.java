package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/** @class Tank
 *  抽象坦克基类，可以派生出不同类型的坦克
 * */

public abstract class Tank extends Object {
    protected int HP, imgx = 60, imgy = 0, oldX, oldY;
    public static final int WIDTH = 27, HEIGHT = 27;

    public boolean up = false, down = false, left = false, right = false;

    protected Born born;

    private boolean isAlive, STOP = false;

    protected Direction direction;

    public Tank(int x, int y, int width, int height,int HP,Direction direction) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.isAlive = true;
        this.HP = HP;
        born = new Born(x,y);
    }

    /** @programe Stay
     *  当碰到障碍物时，停留，不能前进
     * */
    public void Stay() {
        x = oldX;
        y = oldY;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setLife(boolean life) {
        this.isAlive = life;
    }

    public void HPDecrease() {
        HP--;
        if(HP <= 0) {
            isAlive = false;
        }
    }

    public abstract void Move();

    public abstract void Fire();

}
