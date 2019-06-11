package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Grass extends Object{
    private Image grassImg;
    public Grass(int x,int y) {
        super(x,y);
    }

    @Override
    public void draw(Graphics g) {
        try {
            grassImg = ImageIO.read(new File("images/grass.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(grassImg, x, y, x+30, y+30, 0, 0, 60, 60, Controller.getInstance());
    }
}
