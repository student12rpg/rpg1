package world;

import entity.*;
import window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Level {

    public int count_tile_width = 20;
    public int count_tile_height = 20;

    public int currentLevel = 1;

    BufferedImage image;

    public Tile[] tiles = new Tile[256];

    public int[][] map = new int[count_tile_height][count_tile_width];

    Game game;

    public Level(Game game){

        this.game = game;

        try {
            image = ImageIO.read(new File("images/Tiles.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //создание массива плиток для карты
        tiles[0] = new Tile(image.getSubimage(0,64,64,64),0,false);
        //id=1 стена
        tiles[1] = new Tile(image.getSubimage(64,0,64,64),1, true);
        //id=2 дорожка
        tiles[2] = new Tile(image.getSubimage(192,64,64,64),1, false);
        //брусчатка
        tiles[3] = new Tile(image.getSubimage(64,64,64,64),1, false);

        changeLevel(1);
    }

    public void nextLevel(){
        currentLevel++;
        changeLevel(currentLevel);
    }

    public void changeLevel(int level){

        game.listRpgObjects.clear();

        if (level == 1)
        {
            game.player.x = 300;
            game.player.y = 300;
            //добавление объектов уровня 1
            game.listRpgObjects.add(new Tree(game, 100, 350));
            game.listRpgObjects.add(new Tree(game, 400, 50));
            game.listRpgObjects.add(new Flag(game, 540, 1100));
            //Артем - терь - тоже объект
            game.listRpgObjects.add(new Lose(game, 320, 764));
            //враг
            game.listRpgObjects.add(new Enemy(game,500,100));
            game.listRpgObjects.add(new Enemy(game,1000,200));
            game.listRpgObjects.add(new Enemy(game,100,500));

            game.listRpgObjects.add(new Stove(game,700,900));
            game.listRpgObjects.add(new Stove(game,600,900));
            game.listRpgObjects.add(new Stove(game,400,900));

            int[][] map1 = {
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 1, 1, 2, 2, 1, 0, 0, 1},
                    {0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 1, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 1, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 1, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            };

            map = map1;
        } else
        if (level == 2)
        {
            game.player.x = 400;
            game.player.y = 300;

            Random r = new Random();
            //враг
            for (int i = 0; i<20; i++) {
                game.listRpgObjects.add(new Enemy(game, r.nextInt(1000), r.nextInt(1000)));
            }

            game.listRpgObjects.add(new Stove(game,100,400));
            game.listRpgObjects.add(new Stove(game,200,500));
            game.listRpgObjects.add(new Stove(game,300,600));

            game.listRpgObjects.add(new Flag(game, 540, 1100));

            int[][] map1 = {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            };

            map = map1;
        } else //конец игры
        {
            game.State = 3;
        }
    }

    public void move(){

    }

    public void render(Graphics g){
        //  tiles[0].render(g,100,100);
        for (int y=0; y<count_tile_height; y++){
            for (int x=0; x<count_tile_width; x++){
                tiles[map[y][x]].render(g, (x*64)-game.offsetX, (y*64)-game.offsetY);
            }
        }
    }
}
