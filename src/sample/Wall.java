package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Wall extends Object {

    public static final int WIDTH = 30, HEIGHT = 30;

    private int HP = 7;

    private boolean isAlive;

    private static Image wallImg, wallsImg;

    public Wall(int x, int y) {
        super(x,y,WIDTH,HEIGHT);
        this.isAlive = true;
    }

    /** @param 初始普通墙的静态图片
     *
     * */
    static {
        try {
            wallImg = ImageIO.read(new File("images/wall.gif"));
            wallsImg = ImageIO.read(new File("images/walls.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!isAlive) {
            Controller.getInstance().map.walls.remove(this);
            return;
        }
        g.drawImage(wallsImg, x, y, x + 30, y + 30, 0, 0, 60, 60, Controller.getInstance());
    }

    public void HPDecrease() {
        HP--;
        if (HP <= 0) {
            setAlive(false);
        }
    }

    public void setAlive(boolean life) {
        this.isAlive = life;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
