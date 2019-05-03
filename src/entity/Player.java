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
        } else if (game.keyManager.isLeft){
            deltX = -3;
        } else if (game.keyManager.isUP){
            deltY = -3;
        } else if (game.keyManager.isDown){
            deltY = 3;
        }

        x = x + deltX;
        y = y + deltY;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image.getSubimage(48,0,48,48),x,y,width,height,null);
    }
}
