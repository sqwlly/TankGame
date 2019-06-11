package sample;

import java.awt.*;
import java.util.ArrayList;

public class Map extends Object{


    private int[][] map ;
    private int level;
    private int wallCount;
    private int waterCount;
    private int grassCount;
    private int steelCount;

    ArrayList<Wall> walls   = new ArrayList<>();
    ArrayList<Water> water = new ArrayList<>();
    ArrayList<Grass> grass = new ArrayList<>();
    ArrayList<Steel> steels = new ArrayList<>();
    Wall wall;
    Water _water;
    Grass _grass;
    Steel steel;
    MapFileReader mfr;
    public Map(int level) {
        this.level = level;
      //  this.getInstance() = Controller.getInstance();
        switch (this.level) {
            case 0:
                mfr = new MapFileReader(0);
                break;
            case 1:
                mfr = new MapFileReader(1);
                break;
            case 2:
                mfr = new MapFileReader(2);
                break;
        }
        map = mfr.loadMap();

        this.wallCount = mfr.wallCount;
        this.waterCount = mfr.waterCount;
        this.grassCount = mfr.grassCount;
        this.steelCount = mfr.steelCount;


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (wallCount == 0 && waterCount == 0 && grassCount == 0 && steelCount == 0) {
                    wallCount = mfr.wallCount;
                    waterCount = mfr.waterCount;
                    grassCount = mfr.grassCount;
                    steelCount = mfr.steelCount;
                    return;
                }
                switch (map[i][j]) {
                    case 0:
                        walls.add(new Wall(j * 30, i * 30));
                        wallCount--;
                        break;
                    case 1:
                        water.add(new Water(j * 30, i * 30));
                        waterCount--;
                        break;
                    case 2:
                        grass.add(new Grass(j * 30, i * 30));
                        grassCount--;
                        break;
                    case 3:
                        steels.add(new Steel(j * 30, i * 30));
                        steelCount--;
                        break;
                }
            }
        }

    }

    @Override
    public void draw(Graphics g){

        for( int j = 0; j < water.size(); j++){
            _water = water.get(j);
            _water.draw(g);
        }

        for( int i = 0; i < walls.size(); i++){
            wall = walls.get(i);
            wall.draw(g);
        }

        for( int k = 0; k < grass.size(); k++){
            _grass = grass.get(k);
            _grass.draw(g);
        }
        for( int n = 0; n < steels.size(); n++){
            steel = steels.get(n);
            steel.draw(g);
        }
    }
}
