package world;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level {

    public int count_tile_width = 8;
    public int count_tile_height = 8;

    public int currentLevel = 0;

    BufferedImage image;

    public Tile[] tiles = new Tile[256];

    public int[][] map = new int[count_tile_height][count_tile_width];

    public Level(){

        try {
            image = ImageIO.read(new File("images/Tiles.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //создание массива плиток для карты
        tiles[0] = new Tile(image.getSubimage(0,64,64,64),0,false);
        //id=1 стена
        tiles[1] = new Tile(image.getSubimage(64,0,64,64),1, true);

        changeLevel(2);
    }

    public void changeLevel(int level){

        if (level == 1)
        {
            int[][] map1 = {
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0}
            };

            map = map1;
        } else
        if (level == 2)
        {
            int[][] map1 = {
                    {0, 0, 0, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}
            };

            map = map1;
        } else //конец игры
        {

        }
    }

    public void move(){

    }

    public void render(Graphics g){
        //  tiles[0].render(g,100,100);
        for (int y=0; y<count_tile_height; y++){
            for (int x=0; x<count_tile_width; x++){
                tiles[map[y][x]].render(g, x*64, y*64);
            }
        }
    }
}
