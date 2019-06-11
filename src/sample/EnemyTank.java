package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class EnemyTank extends Tank {

    private static Image[] enemyTankImgs = new Image[10];

    private int TYPE, STEP = random.nextInt(50) + 5;

    private static int[] SPEED = {1,1,1,1,1,1};

    private static int[] HP = {1,2,3,4,5,6};

    private static Random random = new Random();

    private Born born;

    public EnemyTank(int x, int y, int width, int height, Direction direction, int TYPE) {
        super(x, y, width, height,HP[TYPE], direction);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.TYPE = TYPE;
        born = new Born(x,y);
    }

    /** @param 初始敌方坦克的静态图片
     *
     * */
    static {
        try {
            enemyTankImgs[0] = ImageIO.read(new File("images/Tanks_1.png"));
            enemyTankImgs[1] = ImageIO.read(new File("images/Tanks_2.png"));
            enemyTankImgs[2] = ImageIO.read(new File("images/Tanks_3.png"));
            enemyTankImgs[3] = ImageIO.read(new File("images/Tanks_4.png"));
            enemyTankImgs[4] = ImageIO.read(new File("images/Tanks_5.png"));
            enemyTankImgs[5] = ImageIO.read(new File("images/Tanks_6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        if(!isAlive()) {
            Controller.getInstance().enemyTanks.remove(this);
        }
        if(born.isLife()) {
            born.draw(g);
            return;
        }
        g.drawImage(enemyTankImgs[TYPE], x, y, x + Tank.WIDTH, y + Tank.HEIGHT, imgx, imgy, imgx + 60, imgy + 60, Controller.getInstance());
        Move();
    }

    @Override
    public void Move() {
        this.oldX = x;
        this.oldY = y;
        switch (this.direction) {
            case up:
                imgx = 60;
                this.y -= SPEED[TYPE]; break;
            case down:
                imgx = 120;
                this.y += SPEED[TYPE]; break;
            case left:
                imgx = 0;
                this.x -= SPEED[TYPE]; break;
            case right:
                imgx = 180;
                this.x += SPEED[TYPE]; break;
        }

        /* 到边界 */
        if(x < 0) x = 0; if(y < 0) y = 0;
        if(x + Tank.WIDTH > Controller.WIDTH) {
            x = Controller.WIDTH - Tank.WIDTH;
        }
        if(y + Tank.HEIGHT > Controller.HEIGHT) {
            y = Controller.HEIGHT - Tank.HEIGHT;
        }

        /* 与墙碰撞 */
        for(int i = 0; i < Controller.getInstance().map.walls.size(); ++i) {
            if(this.getRec().intersects(Controller.getInstance().map.walls.get(i).getRec())) {
                Stay();
            }
        }

        /* 随机行走发射子弹 */
        if(isAlive()) {
            if(STEP == 0) {
                Direction[] directions = Direction.values();
                STEP = random.nextInt(100) + 5;
                direction = directions[random.nextInt(directions.length)];
            }
            STEP--;
            if(random.nextInt(80) > 78) {
                Fire();
            }
        }

    }

    @Override
    public void Fire() {
        int x = this.x + Tank.WIDTH / 2 - EnemyBullet.WIDTH / 2;
        int y = this.y + Tank.HEIGHT / 2 - EnemyBullet.HEIGHT / 2;
        Controller.getInstance().enemyBullets.add(new EnemyBullet(x, y, Bullet.WIDTH, Bullet.HEIGHT, direction));
    }
}
