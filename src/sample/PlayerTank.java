package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PlayerTank extends Tank{

    private static Image[] playerImgs = new Image[5];
    private int speed = 3, TYPE;

    private Born born;

    public PlayerTank(int x, int y, int width, int height,Direction direction, int TYPE) {
        super(x, y, width, height, 5, direction);
        this.x = x;
        this.y = y;
        this.TYPE = TYPE;
        this.width = width;
        this.height = height;
        this.direction = direction;
        born = new Born(x, y);
    }

    /** @param 初始玩家坦克的静态图片
     *
     * */
    static {
        try {
            playerImgs[0] = ImageIO.read(new File("images/Tanks.png"));
            playerImgs[1] = ImageIO.read(new File("images/Tanks_6.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "系统文件丢失\n原因：加载图片素材失败！");
        }
    }

    @Override
    public void draw(Graphics g) {
        if(!isAlive()) {
            return;
        }
        if(born.isLife()) {
            born.draw(g);
            return;
        }
        g.drawImage(playerImgs[TYPE], x, y, x + Tank.WIDTH, y + Tank.HEIGHT, imgx, imgy, imgx + 60, imgy + 60, Controller.getInstance());
    }

    @Override
    public void Move() {
        this.oldX = x;
        this.oldY = y;

        if(up) {
            this.direction = Direction.up;
            imgx = 60;
            this.y -= speed;
        }else if(down) {
            this.direction = Direction.down;
            imgx = 120;
            this.y += speed;
        }else if(left) {
            this.direction = Direction.left;
            imgx = 0;
            this.x -= speed;
        }else if(right) {
            this.direction = Direction.right;
            imgx = 180;
            this.x += speed;
        }

        //坦克到达地图边界
        if(x < 0) x = 0; if(y < 0) y = 0;
        if(x + Tank.WIDTH > Controller.WIDTH) {
            x = Controller.WIDTH - Tank.WIDTH;
        }
        if(y + Tank.HEIGHT > Controller.HEIGHT) {
            y = Controller.HEIGHT - Tank.HEIGHT;
        }

        Controller.getInstance().crashCheck();

    }

    @Override
    public void Fire() {
        //if(Controller.getInstance().playerBullets.size() >= 1) return; //限制玩家每次只能发一颗子弹

        int x = this.x + Tank.WIDTH / 2 - PlayerBullet.WIDTH / 2;
        int y = this.y + Tank.HEIGHT / 2 - PlayerBullet.HEIGHT / 2;
        Controller.getInstance().playerBullets.add(new PlayerBullet(x, y, PlayerBullet.WIDTH, PlayerBullet.HEIGHT, this.direction));
    }

}
