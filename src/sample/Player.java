package sample;

/** @class Player
 *  玩家类,用作排行榜，记录玩家
 *  名字以及分数
 *
 * */

public class Player {
    private String name;
    private int score;


    public Player(String name,int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
