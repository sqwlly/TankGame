package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Victory extends Object{

    boolean first;
    int change;
    public Victory() {
        this.x = 240;
        this.y = Controller.HEIGHT - 27;
        this.first = true;
    }

    JTextField playerName;
    JButton sure;
    JLabel jLabel;

    @Override
    public void draw(Graphics g) {
        if(change == 1) {
            g.setColor(Color.YELLOW);
        }else{
            g.setColor(Color.white);
        }
        change ^= 1; //颜色闪烁变换
        g.setFont(new Font("monaco", Font.BOLD, 50));
        if (y <= 200) {
            g.drawString("VICTORY", x, y);
            if(first) { //防止重复生成
                upLoad();
                first = false;
            }
            return;
        }
        g.drawString("VICTORY", x, y);
        y -= 3;
    }

    public void upLoad() {
        playerName = new JTextField(14);
        sure = new JButton("确定");
        jLabel = new JLabel("玩家名字");
        sure.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Player player = new Player(playerName.getText(), Integer.parseInt(Controller.getInstance().score.getText()));
                new MysqlDemo().savePlayerName(player); //将名字以及分数上传到服务器
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
        /**  游戏结束，产生名字输入框以及label和button
         *
         * */
        Controller.getInstance().add(jLabel);
        Controller.getInstance().add(playerName);
        Controller.getInstance().add(sure);
        Controller.getInstance().game_jam.add(Controller.getInstance(),BorderLayout.CENTER);
        Controller.getInstance().game_jam.setVisible(true);
    }

}
