package window;

import java.awt.*;

public class Menu {
    Game game;

    Rectangle play = new Rectangle(350,200, 100,50);
    Rectangle quit = new Rectangle(350, 280, 100, 50);

    public Menu(Game game){
        this.game = game;
    }

    public void move(){

        if (game.mouseManager.isPressMouse){

            int pressX = game.mouseManager.userPressX;
            int pressY = game.mouseManager.userPressY;

            if ((pressX>=350) && (pressX<=(play.x+play.width))){
                if ((pressY>=200) && (pressY<=(play.y+play.height))){

                    if (game.needStart) {
                        game.player.life = 100;
                        game.level.changeLevel(1);
                    }
                    game.State = 1;

                }
            }

            if ((pressX>=350) && (pressX<=(quit.x+quit.width))){
                if ((pressY>=280) && (pressY<=(quit.y+quit.height))){
                    System.exit(0);
                }
            }

        }

    }

    public void render(Graphics g){

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",1,20));

        g.drawString("Играть",play.x+18,play.y+35);
        g.drawRect(play.x,play.y,play.width,play.height);
        g.drawString("Выход",quit.x+18,quit.y+35);
        g.drawRect(quit.x,quit.y,quit.width,quit.height);

    }
}
