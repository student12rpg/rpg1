package entity;

import window.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class StarEnemy extends GameObject {

    BufferedImage image;
    public double angle; //угол

    public StarEnemy(Game game, int x, int y) {
        super(game, x, y);
        this.width = 32;
        this.height = 32;

        try {
            image = ImageIO.read(new File("images/star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.id = RpgObjectType.STAR;
        angle = Math.atan2(x+game.offsetX-game.width/2,y+game.offsetY-game.height/2);
    }

    @Override
    public void move() {
        x += (float) (2*Math.cos(angle) + 15 * Math.sin(angle));
        y += (float) (2*Math.sin(angle) + 15 * Math.cos(angle));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image,x-game.offsetX,y-game.offsetY,width,height, null);
    }
}
