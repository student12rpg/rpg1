package world;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public int TILE_WIDTH=64;
    public int TILE_HEIGHT=64;

    BufferedImage image;
    public int id;
    public boolean isBlock; //можно ли пройти (стена)
    public Tile(BufferedImage image, int id, boolean isBlock){
        this.image = image;
        this.id = id;
        this.isBlock = isBlock;
    }

    public void move(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(image,x,y,TILE_WIDTH,TILE_HEIGHT,null);
    }
}
