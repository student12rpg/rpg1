package entity;

import window.Game;

import java.awt.*;

public class Player extends GameObject{

    int width, height;

    public Player(Game game, int x, int y){
        super(game,x,y); //вызывает конструктор GameObject
        this.width = 32;
        this.height = 32;
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
        g.fillRect(x,y,width,height);
    }
}
