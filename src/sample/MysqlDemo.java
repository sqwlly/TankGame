package sample;

import java.sql.*;
import java.util.HashMap;
import java.util.Vector;

public class MysqlDemo {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://111.231.145.72:3306/TankGame?useSSL=true";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "sqwlly";
    static final String PASS = "sqw123";
    Connection conn;
    public MysqlDemo() {
        conn = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    public Vector<Player> showRank() {
        String sql = "select * from playerlist order by playerscore desc limit 10";
        Vector<Player> vector = new Vector<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
              //  int id = rs.getInt("id");
                String name = rs.getString("playername");
                String score = rs.getString("playerscore");
                vector.add(new Player(name,Integer.valueOf(score)));
            }
            rs.close();
            conn.close();
        }catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    public void savePlayerName(Player player) {

        String sql;
        sql = "INSERT INTO playerlist(playername,playerscore) values(?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setInt(2, player.getScore());
            preparedStatement.executeUpdate();
            System.out.println("插入成功 Goodbye!");
            preparedStatement.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
