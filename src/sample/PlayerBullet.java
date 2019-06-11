package sample;

import java.awt.*;

public class PlayerBullet extends Bullet{

    public static final int WIDTH= 5, HEIGHT = 5;

    public PlayerBullet(int x, int y, int width, int height,Direction direction) {
        super(x, y, width, height,direction);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.speed = 4;
    }

    @Override
    public void draw(Graphics g) {
        if(!getLife()) Controller.getInstance().playerBullets.remove(this);
        g.setColor(Color.white);
        g.fillOval(x,y,width,height);
        Move();
    }
}
