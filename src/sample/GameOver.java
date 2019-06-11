package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOver extends Object {

    private boolean first;

    public GameOver() {
        first = true;
        this.x = 200;
        this.y = Controller.HEIGHT - 27;
    }

    JTextField playerName;
    JButton sure;
    JLabel jLabel;

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("monaco", Font.BOLD, 50));
        if (y <= 200) {
            g.drawString("GAME OVER", x, y);
            if(first) { //防止重复生成
                upLoad();
//                new Thread(new MediaPlayer(MediaPlayer.PLAY_GAMEOVER)).start();
                first = false;
            }
            return;
        }
        g.drawString("GAME OVER", x, y);
        y -= 3;
    }

    public void upLoad() {
        playerName = new JTextField(14);
        sure = new JButton("确定");
        jLabel = new JLabel("玩家名字");
//        sure.addKeyListener(Controller.getInstance().listener);
        sure.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Player player = new Player(playerName.getText(), Integer.parseInt(Controller.getInstance().score.getText()));
                MysqlDemo demo = new MysqlDemo();
                demo.savePlayerName(player);
                sure.setVisible(false);
                playerName.setVisible(false);
                jLabel.setVisible(false);
                JOptionPane.showMessageDialog(null,"恭喜！你的名字已被留下～");
                int op = JOptionPane.showConfirmDialog(null,"是否再来一局","提示",JOptionPane.YES_NO_OPTION);
                if(op == JOptionPane.YES_OPTION) {
                    Controller.getInstance().init();
                }else if(op == JOptionPane.NO_OPTION) {
                    Controller.getInstance().menu = new Menu();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
//        playerName.addKeyListener(Controller.getInstance().listener);
        Controller.getInstance().add(jLabel);
        Controller.getInstance().add(playerName);
        Controller.getInstance().add(sure);
        Controller.getInstance().game_jam.add(Controller.getInstance(),BorderLayout.CENTER);
        Controller.getInstance().game_jam.setVisible(true);
    }


}
