package sample;

import java.awt.*;

public abstract class Bullet extends Object{

    public static final int WIDTH = 5, HEIGHT = 5;

    protected Direction direction;

    private boolean life;

    protected int speed;

    public Bullet(int x, int y, int width, int height, Direction direction) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        setLife(true);
    }

    public void Move() {
        switch (this.direction) {
            case up: this.y -= this.speed; break;
            case down: this.y += this.speed; break;
            case left: this.x -= this.speed; break;
            case right: this.x += this.speed; break;
        }

        //超出边界
        if(x > Controller.WIDTH || x < 0 || y > Controller.HEIGHT || y < 0) {
            if (this instanceof EnemyBullet) {
                Controller.getInstance().enemyBullets.remove(this);
            } else {
                Controller.getInstance().playerBullets.remove(this);
            }
        }
    }

//    public abstract void Move();

    public abstract void draw(Graphics g);

    /** @programe 击中钢铁
     *
     * */
    public boolean hitSteel() {
        for(int i = 0; i < Controller.getInstance().map.steels.size(); ++i) {
            if(this.getRec().intersects(Controller.getInstance().map.steels.get(i).getRec())) {
                this.setLife(false);
                new Thread(new MediaPlayer(MediaPlayer.PLAY_HIT)).start();
                return true;
            }
        }
        return false;
    }

    /** @programe 击中坦克
     *
     * */
    public boolean hitTank() {
        if(this instanceof PlayerBullet) {
            for (int i = 0; i < Controller.getInstance().enemyTanks.size(); ++i) {
                if (this.getRec().intersects(Controller.getInstance().enemyTanks.get(i).getRec())) {
                    Controller.getInstance().enemyTanks.get(i).HPDecrease();
                    if (!Controller.getInstance().enemyTanks.get(i).isAlive()) {
                        //击毁坦克可以增加分数
                        Controller.getInstance().score.setText(String.valueOf(Integer.parseInt(Controller.getInstance().score.getText()) + 10));
                        //增加爆炸对象
//                        Controller.getInstance().booms.add(new Boom(Controller.getInstance().enemyTanks.get(i).x - Tank.WIDTH / 2 - PlayerBullet.WIDTH / 2, Controller.getInstance().enemyTanks.get(i).y - Tank.HEIGHT / 2 - PlayerBullet.HEIGHT / 2));
                    }
                    this.setLife(false);
                    new Thread(new MediaPlayer(MediaPlayer.PLAY_HIT)).start(); //爆炸音效开始
                    return true;
                }
            }
            return false;
        }else{
            if(this.getRec().intersects(Controller.getInstance().player1.getRec())) {
                this.setLife(false);
                new Thread(new MediaPlayer(MediaPlayer.PLAY_HIT)).start();
                Controller.getInstance().player1HP--; //被敌方坦克击中，会减少血量
                Controller.getInstance().player1.HPDecrease();
                return true;
            }
            if(this.getRec().intersects(Controller.getInstance().player2.getRec())) {
                this.setLife(false);
                new Thread(new MediaPlayer(MediaPlayer.PLAY_HIT)).start();
                Controller.getInstance().player2HP--; //被敌方坦克击中，会减少血量
                Controller.getInstance().player2.HPDecrease();
                return true;
            }
            return false;
        }
    }

    /** @programe 击中普通墙壁
     *
     * */
    public boolean hitWall() {
        for(int i = 0; i < Controller.getInstance().map.walls.size(); ++i) {
            if(this.getRec().intersects(Controller.getInstance().map.walls.get(i).getRec())) {
                Controller.getInstance().map.walls.get(i).HPDecrease();
                if(!Controller.getInstance().map.walls.get(i).isAlive() && this instanceof PlayerBullet) {
                    //破坏墙壁也能获得分数
                    Controller.getInstance().score.setText(String.valueOf(Integer.parseInt(Controller.getInstance().score.getText()) + 3));
                }
                this.setLife(false);
                return true;
            }
        }
        return false;
    }

    /** @programe 击中老窝
     *
     * */
    public boolean hitHome() {
        if(this.getRec().intersects(Controller.getInstance().home.getRec())) {
            Controller.getInstance().home.setLife(false);
            Controller.getInstance().gameOver = new GameOver();
            Controller.getInstance().booms.add(new Boom(Controller.getInstance().player1.x - Tank.WIDTH / 2 - PlayerBullet.WIDTH / 2, Controller.getInstance().player1.y - Tank.HEIGHT / 2 - PlayerBullet.HEIGHT / 2));
            Controller.getInstance().booms.add(new Boom(Controller.getInstance().player2.x - Tank.WIDTH / 2 - PlayerBullet.WIDTH / 2, Controller.getInstance().player2.y - Tank.HEIGHT / 2 - PlayerBullet.HEIGHT / 2));
            Controller.getInstance().enemyBullets.clear();
            Controller.getInstance().playerBullets.clear();
            return true;
        }else {
            return false;
        }
    }

    public void setLife(boolean life){
        this.life = life;
    }

    public boolean getLife() {
        return this.life;
    }
}
