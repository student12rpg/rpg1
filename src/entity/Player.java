package entity;

import window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject{

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

        x = x + deltX;
        y = y + deltY;

        checkRpgObjects();
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
                nextLevel = true;
            }
        }

        if (nextLevel == true) game.level.nextLevel();
    }

    @Override
    public void render(Graphics g) {
        //g.fillRect(x,y,width,height);
        g.drawImage(image.getSubimage(48,0,48,48),x,y,width,height,null);
    }
}
