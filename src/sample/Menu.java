package sample;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu extends Object implements KeyListener {

    private int index, total;

    public Menu() {
        Controller.getInstance().removeAll();
        this.total = 3;
        this.index = 0;
        this.x = Controller.WIDTH / 2 - 40;
        this.y = Controller.HEIGHT / 4;
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
        g.drawString("排行榜",x, y + 60);
        g.setColor(c);
        if(index == 2){
            g.setColor(Color.ORANGE);
        }
        g.drawString("退出游戏",x, y + 120);
        g.setColor(c);
    }

    public void setIndex(int index)
    {
        this.index = index;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
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
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
