package sample;

import javafx.scene.media.Media;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import javafx.scene.media.MediaPlayer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class Menu extends Object{

    private int index, total, t;

    private boolean pause;

    private MediaPlayer mediaPlayer;
//    private Thread music;
    public Menu() {
        Controller.getInstance().removeAll();
        this.total = 4;
        this.index = 0;
        this.x = Controller.WIDTH / 2 - 40;
        this.y = Controller.HEIGHT / 4;
        mediaPlayer =  new MediaPlayer(new Media(Paths.get(sample.MediaPlayer.PLAY_STARTCARTOON).toUri().toString()));
//        music = new Thread(String.valueOf(mediaPlayer));
//        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
//        music.start();
       // new Thread(this).start();
    }

    public void turnOnMusic() {
        pause = false;
        mediaPlayer.play();
        /*synchronized (music) {
            music.notify();
        }*/

    }

    public void turnOffMusic() {
        mediaPlayer.pause();
        pause = true;
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("楷体",Font.BOLD,20));
        g.setColor(Color.white);
        Color c = g.getColor();
        if(index == 0) {
            g.setColor(Color.orange);
        }
        g.drawString("开始游戏",x,y);
        g.setColor(c);
        if(index == 1){
            g.setColor(Color.orange);
        }
        g.drawString("排 行 榜",x, y + 60);
        g.setColor(c);
        if(index == 2){
            g.setColor(Color.orange);
        }
        g.drawString("背景音乐",x, y + 120);
        g.setColor(c);
        if(index == 3){
            g.setColor(Color.orange);
        }
        g.drawString("退出游戏",x, y + 180);
        g.setColor(c);
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            setIndex((index + 1) % total);
        }else if(e.getKeyCode() == KeyEvent.VK_UP) {
            setIndex((index - 1 + total) % total);
        }else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (index)
            {
                case 0:
                    Controller.getInstance().menu = null;
                    Controller.getInstance().init();
                    break;

                case 1:
                    Controller.getInstance().menu = null;
                    Controller.getInstance().rankList = new RankList();
                    break;
                case 2:
                    if(pause) {
                        turnOnMusic();
                    }else{
                        turnOffMusic();
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }

   /* @Override
    public void run() {
        try {
            while(true) {
                if(!music.isAlive()) {
                    synchronized (music) {
                        //music.wait();
                    }
                }
                Thread.sleep(35);
                System.out.println(t++ + " " + pause);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
