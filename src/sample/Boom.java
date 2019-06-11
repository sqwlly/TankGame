package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Boom extends Object{

    private static Image boomImg;

    private boolean life;

    private int step;

    int[] imgx = {0, 136, 272, 408, 544, 680, 816, 952, 1088};

    public Boom(int x, int y) {
        super(x, y);
        this.life = true;
    }

    /** @param 初始爆炸演化的静态图片
     *
     * */
    static {
        try {
            boomImg = ImageIO.read(new File("images/boom.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!life) return;
        if(step == 0){
            new Thread(new MediaPlayer(MediaPlayer.PLAY_BOOM)).start();//爆炸声音
        }
        if (step == imgx.length - 1) {
            life = false;
            step = 0;
            Controller.getInstance().booms.remove(this);
            return;
        }
        g.drawImage(boomImg, x, y, x + 68, y + 53, imgx[step], 0, imgx[step + 1], 107, Controller.getInstance());
        step++;
    }

}
