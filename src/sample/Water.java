package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Water extends Object {
    private Image waterImg;

    public static final int WIDTH = 30, HEIGHT = 30;

    public Water(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        try {
            waterImg = ImageIO.read(new File("images/water.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(waterImg, x, y, x + 30, y + 30, 0, 0, 60, 60, Controller.getInstance());
    }
}
