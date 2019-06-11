package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Home extends Object{

    private static Image homeImg, destoryImg;

    private static final int WIDTH = 30, HEIGHT = 30;

    private boolean life;

    public Home(int x,int y) {
        super(x,y,WIDTH,HEIGHT);
        setLife(true);
    }

    static {
        try {
            homeImg = ImageIO.read(new File("images/symbol.gif"));
            destoryImg = ImageIO.read(new File("images/destory.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        if(isAlive()){
            g.drawImage(homeImg, x, y, x + 30, y + 30, 0, 0, 60, 60, Controller.getInstance());
        }else{
            g.drawImage(destoryImg, x, y, x + 30, y + 30, 0, 0, 60, 60, Controller.getInstance());
        }
    }

    public boolean isAlive() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

}
