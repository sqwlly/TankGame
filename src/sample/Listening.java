package sample;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listening implements KeyListener { //接口一定要有抽象方法
    public Controller game;

    public Listening(Controller game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Controller.getInstance().menu != null) {
            Controller.getInstance().menu.keyPressed(e);
        }
        if(Controller.getInstance().rankList != null) {
            Controller.getInstance().rankList.keyPressed(e);
        }
        //玩家死亡之后将不能移动和发射子弹
        if((Controller.getInstance().player1 == null && Controller.getInstance().player2 == null )|| (!Controller.getInstance().player1.isAlive() && !Controller.getInstance().player2.isAlive())) return;
        if(Controller.getInstance().victory == null) return;

        //玩家1
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.game.player1.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.game.player1.down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.game.player1.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.game.player1.right = true;
        }
        //玩家2
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.game.player2.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.game.player2.down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.game.player2.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.game.player2.right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //玩家死亡之后将不能移动和发射子弹
        if((Controller.getInstance().player1 == null && Controller.getInstance().player2 == null )|| (!Controller.getInstance().player1.isAlive() && !Controller.getInstance().player2.isAlive())) return;
        if(Controller.getInstance().victory == null) return;

        //玩家1
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.game.player1.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.game.player1.down = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.game.player1.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.game.player1.right = false;
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(Controller.getInstance().player1.isAlive()) {
                this.game.player1.Fire();
                new Thread(new MediaPlayer(MediaPlayer.PLAY_FIRE)).start();
            }
        }

        //玩家2
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.game.player2.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.game.player2.down = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.game.player2.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.game.player2.right = false;
        } else if(e.getKeyCode() == KeyEvent.VK_1) {
            if(Controller.getInstance().player2.isAlive()) {
                this.game.player2.Fire();
                new Thread(new MediaPlayer(MediaPlayer.PLAY_FIRE)).start();
            }
        }
    }
}
