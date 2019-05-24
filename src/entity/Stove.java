package entity;

import window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Stove extends GameObject {

    BufferedImage image;

    public Stove(Game game, int x, int y) {
        super(game, x, y);
        this.width=64;
        this.height=90;
        try {
            image = ImageIO.read(new File("images/inventary.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.id = RpgObjectType.STOVE;
    }

    public void move(){

    }

    public void render(Graphics g){
        g.drawImage(image.getSubimage(255,130,66,96),x-game.offsetX,y-game.offsetY,width,height,null);
    }
}
