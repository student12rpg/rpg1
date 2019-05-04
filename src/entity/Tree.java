package entity;

import window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tree extends GameObject {

    int width, height;
    BufferedImage image;

    public Tree(Game game, int x, int y) {
        super(game, x, y);
        this.width=64;
        this.height=128;
        try {
            image = ImageIO.read(new File("images/inventary.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(){

    }

    public void render(Graphics g){
        g.drawImage(image.getSubimage(480,30,32,65),x,y,width,height,null);
    }
}
