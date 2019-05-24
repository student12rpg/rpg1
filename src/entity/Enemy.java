package entity;

import window.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy extends GameObject{

    BufferedImage image;

    public Enemy(Game game, int x, int y) {
        super(game, x, y);
        this.width = 64;
        this.height = 96;

        try {
            image = ImageIO.read(new File("images/zombie.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.id = RpgObjectType.ENEMY_ZOMBE;
    }

    @Override
    public void move() {
        if (x>game.player.x) x--;
        if (x<game.player.x) x++;
        if (y>game.player.y) y--;
        if (y<game.player.y) y++;

     /*   if (Math.abs(x-game.player.x)>100) {
            game.listAddObjects.add(new StarEnemy(game,x,y));
        }
*/
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image.getSubimage(0,0,32,48),x-game.offsetX,y-game.offsetY,width,height,null);
    }
}
