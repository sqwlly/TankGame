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
        if(Controller.getInstance().player == null || !Controller.getInstance().player.isAlive()) return;
        if(Controller.getInstance().victory == null) return;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.game.player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.game.player.down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.game.player.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.game.player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER && !Controller.getInstance().player.isAlive()) {
            //Controller.getInstance().initTank();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(Controller.getInstance().player == null || !Controller.getInstance().player.isAlive()) return;

        if(Controller.getInstance().victory == null) return;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.game.player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.game.player.down = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.game.player.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.game.player.right = false;
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.game.player.Fire();
            if(Controller.getInstance().player.isAlive()) {
                new Thread(new MediaPlayer(MediaPlayer.PLAY_FIRE)).start();
            }
        }
    }
}
