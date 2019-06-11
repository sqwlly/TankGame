package sample;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class RankList extends Object implements KeyListener {

    int top;
    static Vector<Player> players;
    String drawString;

    public RankList() {
        this.top = 10;
        this.x = Controller.WIDTH / 2 - 70;
        this.y = Controller.HEIGHT / 6;
        players = new MysqlDemo().showRank();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawString("排名      姓名       分数",x,y);
        g.setColor(Color.white);
        for(int i = 1; i <= players.size(); ++i) {
            drawString = i + ".        "+players.get(i - 1).getName() + "         " + players.get(i - 1).getScore();
            g.drawString(drawString,x,y + i * 30);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Controller.getInstance().menu = new Menu();
            Controller.getInstance().rankList = null;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
