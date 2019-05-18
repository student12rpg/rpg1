package entity;

import window.Game;
import world.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject{

    boolean AnimFlag = false;

    int width, height;
    BufferedImage image;

    public Player(Game game, int x, int y){
        super(game,x,y); //вызывает конструктор GameObject
        this.width = 64;
        this.height = 64;

        try {
            image = ImageIO.read(new File("images/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void move() {

        if (game.mouseManager.isPressMouse){
            game.listRpgObjects.add(new Star(game,x,y));
            game.mouseManager.isPressMouse = false;
        }

        this.deltX = 0;
        this.deltY = 0;

        if (game.keyManager.isRight){
            deltX = 3;
        }
        if (game.keyManager.isLeft){
            deltX = -3;
        }
        if (game.keyManager.isUP){
            deltY = -3;
        }
        if (game.keyManager.isDown){
            deltY = 3;
        }

        checkCollision(x + deltX, y + deltY);

        x = x + deltX;
        y = y + deltY;

        if (x<0) x = 0;
        if (y<0) y = 0;
        if (x>(game.level.count_tile_width-1)*64) x = (game.level.count_tile_width-1)*64;
        if (y>(game.level.count_tile_height-1)*64) y = (game.level.count_tile_height-1)*64;

        checkRpgObjects();

        game.centerPlayer(x,y);
    }

    public void checkCollision(int testX, int testY){

        boolean isCollision = false;
        int index_tile = 0;

        for (int i=0; i<game.level.count_tile_height;i++){
            for (int j=0; j<game.level.count_tile_width; j++) {
                index_tile = game.level.map[i][j];
                Tile tile = game.level.tiles[index_tile];
                if (tile.isBlock) {
                    int obj_left = j * tile.TILE_WIDTH;
                    int obj_right = obj_left + tile.TILE_WIDTH;
                    int obj_top = i * tile.TILE_HEIGHT;
                    int obj_bottom = obj_top + tile.TILE_HEIGHT;

                    boolean xcoll = ((testX+10<obj_right) && ((testX+width-10)>obj_left));
                    boolean ycoll = ((testY+7<obj_bottom) && ((testY+height)>obj_top));

                    if (xcoll && ycoll) {
                        isCollision = true;
                    }
                }
            }
        }

        if (isCollision){
            deltY = 0;
            deltX = 0;
        }
    }


    public void checkRpgObjects(){

        boolean nextLevel = false;

        for (GameObject item : game.listRpgObjects){
            int obj_left = item.x;
            int obj_right = item.x + item.width;
            int obj_top = item.y;
            int obj_bottom = item.y + item.height;

            boolean xcoll = ((x<obj_right) && ((x+width)>obj_left));
            boolean ycoll = ((y+5<obj_bottom) && ((y+height-2)>obj_top));

            if (xcoll && ycoll) {
                if (item.id == RpgObjectType.FLAG) {
                    nextLevel = true;
                }

                //если комната закрыта - включить свет
                if (item.id == RpgObjectType.LOSE) {
                    ((Lose)item).isLigth = true;
                }
            }
        }

        if (nextLevel == true) game.level.nextLevel();
    }

    @Override
    public void render(Graphics g) {
        if (game.keyManager.isRight)
            g.drawImage(image.getSubimage(0,96,48,48),x-game.offsetX,y-game.offsetY,width,height,null);
        else
        if (game.keyManager.isLeft)
            g.drawImage(image.getSubimage(0,48,48,48),x-game.offsetX,y-game.offsetY,width,height,null);
        else
        if (game.keyManager.isUP)
            g.drawImage(image.getSubimage(0,144,48,48),x-game.offsetX,y-game.offsetY,width,height,null);
        else //default
        g.drawImage(image.getSubimage(48,0,48,48),x-game.offsetX,y-game.offsetY,width,height,null);
    }
}
