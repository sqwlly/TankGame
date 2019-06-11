package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Born extends Object {
    private static final int WIDTH = 30, HEIGHT = 30;
    private int step;
    private boolean life;
    int[] imgx = {
            0, 60, 120, 180, 240, 300, 360, 420, 480,
            480, 420, 360, 300, 240, 180, 120, 60, 0,
            0, 60, 120, 180, 240, 300, 360, 420, 480,
            480, 420, 360, 300, 240, 180, 120, 60, 0,
    };

    private static Image bornImg;

    public Born(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        this.life = true;
    }

    /** @param 初始静态出生时的闪光图片
     * */
    static {
        try {
            bornImg = ImageIO.read(new File("images/start.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!life) return;
        if (step == imgx.length - 1) {//如果步长等于数组的长度（也就是说闪光图片显示完毕）
            life = false;
            step = 0;
            return;
        }
        g.drawImage(bornImg, x, y, x + 30, y + 30, imgx[step], 0, imgx[step + 1], 60, Controller.getInstance());
        step++;
    }

    public boolean isLife() {
        return life;
    }
}
