package sample;

//import javafx.scene.Scene;
//import javafx.scene.layout.GridPane;
//import javafx.scene.control.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Controller extends JPanel {
    public JFrame game_jam;


    public int player1HP = 5, player2HP = 5;

    public static final int WIDTH = 666 - 6, HEIGHT = 598 - 27;

    /*     唯一的全局单例静态变量    */
    private static Controller tc;

    /** @programe 本项目采用的单例模式
     *  采用单例模式,将会非常安全高效并且节省资源
     * */
    static {
        tc = new Controller(); //只实例化一次
    }

    public static Controller getInstance() {
        return tc;
    }


    /*      游戏变量数组区域开始         */
    List<PlayerBullet> playerBullets = new ArrayList<>();
    List<EnemyBullet> enemyBullets = new ArrayList<>();

    List<EnemyTank> enemyTanks = new ArrayList<>();
    List<Boom> booms = new ArrayList<>();
    /*      游戏变量数组区域结束         */

    /*      游戏绘画变量区域开始         */
    PlayerTank player1, player2;
    PlayerBullet playerBullet;
    EnemyBullet enemyBullet;
    EnemyTank enemyTank;
    Wall wall;
    Steel steel;
    Map map;
    Water water;
    Home home;
    GameOver gameOver;
    Victory victory;
    JPanel jPanel1;
    Listening listener;
    JLabel tankCntLabel, player1HPLabel, player2HPLabel, scoreLabel, cnt, hp1, hp2, score;
    RankList rankList;
    Menu menu;
    /*      游戏绘画变量区域结束         */

    public Controller() {
        tc = this;
        menu = new Menu();
        game_jam = new JFrame("坦克大战");
        game_jam.setSize(WIDTH, HEIGHT + 27);
//        game_jam.setLayout(null); //取消边界布局
        game_jam.setLocationRelativeTo(null);
        game_jam.setBackground(Color.BLACK); //设置背景为黑色
        listener = new Listening(this);
        game_jam.addKeyListener(listener);
        this.setSize(WIDTH, HEIGHT);
//        this.setLayout(null);
        this.setBackground(Color.BLACK);
        //game_jam.add(jPanel1,BorderLayout.WEST);
        game_jam.add(this);
        game_jam.setResizable(false);
        game_jam.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { //点击关闭按钮时结束程序
                super.windowClosing(e);
                System.exit(0);
            }
        });
        game_jam.setVisible(true);
        new Thread(() -> {
            while (true) {
                try {
                    repaint();
                    player1.Move();
                    player2.Move();
                    Thread.sleep(30);
                } catch (Exception e) {

                }
            }
        }).start();
    }

    /**
     * @programe 初始化游戏对象
     * */
    public void init() {
        this.removeAll();
        game_jam.getContentPane().removeAll();
        enemyBullets.clear();
        enemyTanks.clear();
        player1HP = 5;
        player2HP = 5;
        gameOver = new GameOver();
        victory = new Victory();
        map = new Map(0);
        score = new JLabel("0");
        cnt = new JLabel("6");
        home = new Home(300, 540);
        new Thread(new MediaPlayer(MediaPlayer.PLAY_ENTERGAME)).start();
        initTank();
        jPanel1 = new JPanel();
        tankCntLabel = new JLabel("坦克数量");
        player1HPLabel = new JLabel("玩家A生命值");
        player2HPLabel = new JLabel("玩家B生命值");
        scoreLabel = new JLabel("分数");
        hp1 = new JLabel("5");
        hp2 = new JLabel("5");
        this.add(scoreLabel);
        this.add(score);
        this.add(tankCntLabel);
        this.add(cnt);
        this.add(player1HPLabel);
        this.add(hp1);
        this.add(player2HPLabel);
        this.add(hp2);
        game_jam.add(this);
        game_jam.setVisible(true);
    }

    /**
     * @programe 初始化游戏坦克
     */
    public void initTank() {
        player1 = new PlayerTank(200, 600, Tank.WIDTH, Tank.HEIGHT, Direction.up,0);
        player2 = new PlayerTank(400, 600, Tank.WIDTH, Tank.HEIGHT, Direction.up,1);
        enemyTanks.add(new EnemyTank(200, 200, Tank.WIDTH, Tank.HEIGHT, Direction.left, 2));
        enemyTanks.add(new EnemyTank(400, 300, Tank.WIDTH, Tank.HEIGHT, Direction.down, 3));
        enemyTanks.add(new EnemyTank(100, 0, Tank.WIDTH, Tank.HEIGHT, Direction.left, 4));
        for (int i = 0; i < 3; ++i) {
            enemyTanks.add(new EnemyTank(100 + i * (Tank.WIDTH + 30), 0, Tank.WIDTH, Tank.HEIGHT, Direction.down, i + 1));
        }
        for(int i = 0; i < 3; ++i) {
            enemyTanks.add(new EnemyTank(200 + i * (Tank.WIDTH + 30),150,Tank.WIDTH,Tank.HEIGHT,Direction.up,i));
        }
    }


    /**
     * @program 碰撞检测
     */
    public void crashCheck() {

        //玩家与普通墙
        for (int i = 0; i < map.walls.size(); ++i) {
            wall = map.walls.get(i);
            if (player1.getRec().intersects(wall.getRec())) {
                player1.Stay();
              //  break;
            }
            if (player2.getRec().intersects(wall.getRec())) {
                player2.Stay();
//                break;
            }
        }

        //玩家与铁墙
        for (int i = 0; i < map.steels.size(); ++i) {
            steel = map.steels.get(i);
            if (player1.getRec().intersects(steel.getRec())) {
                player1.Stay();
                //break;
            }
            if (player2.getRec().intersects(steel.getRec())) {
                player2.Stay();
//                break;
            }
        }

        //玩家与河流
        for (int i = 0; i < map.water.size(); ++i) {
            water = map.water.get(i);
            if (player1.getRec().intersects(water.getRec())) {
                player1.Stay();
                //break;
            }
            if (player2.getRec().intersects(water.getRec())) {
                player2.Stay();
                break;
            }
        }

        //玩家子弹
        for (int i = 0; i < playerBullets.size(); ++i) {
            playerBullet = playerBullets.get(i);
            playerBullet.hitTank();
            playerBullet.hitSteel();
            playerBullet.hitWall();
            playerBullet.hitHome();
        }

        //敌方子弹
        for (int i = 0; i < enemyBullets.size(); ++i) {
            enemyBullet = enemyBullets.get(i);
            enemyBullet.hitTank();
            enemyBullet.hitSteel();
            enemyBullet.hitWall();
            enemyBullet.hitHome();
        }

        //敌方坦克与铁墙
        for (int i = 0; i < enemyTanks.size(); ++i) {
            enemyTank = enemyTanks.get(i);
            if(enemyTank.getRec().intersects(player1.getRec())) {
                player1.Stay();
                enemyTank.Stay();
            }
            if(enemyTank.getRec().intersects(player2.getRec())) {
                player2.Stay();
                enemyTank.Stay();
            }
            for (int j = 0; j < map.steels.size(); ++j) {
                steel = map.steels.get(j);
                if (enemyTank.getRec().intersects(steel.getRec())) {
                    enemyTank.Stay();
                    break;
                }
            }
        }

        for (int i = 0; i < enemyTanks.size(); ++i) {
            for (int j = 0; j < enemyTanks.size(); ++j) {
                if (i != j && enemyTanks.get(i).getRec().intersects(enemyTanks.get(j).getRec())) {
                    enemyTanks.get(i).Stay();
                    break;
                }
            }
        }


        //敌方坦克与河流
        for (int i = 0; i < enemyTanks.size(); ++i) {
            enemyTank = enemyTanks.get(i);
            for (int j = 0; j < map.water.size(); ++j) {
                water = map.water.get(j);
                if (enemyTank.getRec().intersects(water.getRec())) {
                    enemyTank.Stay();
                    break;
                }
            }
        }

    }

    /**
     * @program 游戏画面绘制
     */
    public void paint(Graphics g) {
        super.paint(g); //清除
        if(menu != null) {
            menu.draw(g);
            return;
        }
        if(rankList != null) {
            rankList.draw(g);
            return;
        }

        if(player1 == null || player2 == null)return;
        crashCheck();
        //绘画游戏坦克数量
        cnt.setText(String.valueOf(Math.max(0, enemyTanks.size())));
        //绘画游戏玩家血量
        hp1.setText(String.valueOf(Math.max(0, player1HP)));
        hp2.setText(String.valueOf(Math.max(0, player2HP)));
        //地图
        map.draw(g);
        //老窝
        home.draw(g);

        if(enemyTanks.size() == 0) {
            victory.draw(g);
        }

        if (!player1.isAlive() && !player2.isAlive()) {
            home.setLife(false);
            gameOver.draw(g);
        }

        if (!home.isAlive()) {
            player1.setLife(false);
            player2.setLife(false);
            gameOver.draw(g);
        }

        //玩家坦克
        player1.draw(g);
        player2.draw(g);

        //敌方坦克
        for (int i = 0; i < enemyTanks.size(); ++i) {
            enemyTanks.get(i).draw(g);
        }

        //玩家子弹
        for (int i = 0; i < playerBullets.size(); ++i) {
            playerBullets.get(i).draw(g);
        }

        //敌方子弹
        for (int i = 0; i < enemyBullets.size(); ++i) {
            enemyBullets.get(i).draw(g);
        }

        //爆炸绘制
        for (int i = 0; i < booms.size(); ++i) {
            booms.get(i).draw(g);
        }

        repaint();
    }

}
