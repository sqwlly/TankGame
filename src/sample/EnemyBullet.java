package sample;

import java.awt.*;

public class EnemyBullet extends Bullet {

    public EnemyBullet(int x, int y, int width, int height, Direction direction) {
        super(x, y, width, height, direction);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.speed = 2;
        setLife(true);
    }

    @Override
    public void draw(Graphics g) {
        if (!getLife()) Controller.getInstance().enemyBullets.remove(this);
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
        Move();
    }
}

