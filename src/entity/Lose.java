package entity;

import window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Lose extends GameObject {

    BufferedImage image;

    public boolean isLigth;

    public Lose(Game game, int x, int y) {
        super(game, x, y);
        this.width=770;
        this.height=390;

        isLigth = false; //выключен свет
        try {
            image = ImageIO.read(new File("images/inventary.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.id = RpgObjectType.LOSE;
    }

    public void move(){

    }

    public void render(Graphics g){
        //если свет выключен - рисуем тень
        if (isLigth == false) {
            g.setColor(new Color(0, 0, 0, 220));
            g.fillRect(x - game.offsetX, y - game.offsetY, width, height);
        }
    }
}
