package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Steel extends Object {
    private Image steelsImg;
    public static final int width = 30, height = 30;

    public Steel(int x, int y) {
        super(x, y, 30, 30);
        try {
            steelsImg = ImageIO.read(new File("images/steels.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(steelsImg, x, y, x + 30, y + 30, 0, 0, 60, 60, Controller.getInstance());
    }
}
