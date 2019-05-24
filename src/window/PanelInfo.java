package window;

import java.awt.*;

public class PanelInfo {

    Game game;

    int width;
    int height;

    public PanelInfo(Game game){
        this.game = game;
        width = game.width;
        height = 60;
    }

    public void render(Graphics g) {
        g.setColor(new Color(10, 0, 0, 100));
        g.fillRect(1, 1, width-1, height);

        g.setColor(Color.WHITE);
        g.drawRect(0,0, width, height);

        //уровень жизни пользователя
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arail",1,20));
        g.drawString("life: ", 30,40);
        g.drawRect(80,22, 100, 20);

        //заливаем квадрат красным цветом
        g.setColor(Color.RED);
        g.fillRect(81,23,99,19);

        //заливаем по life пользователя
        g.setColor(Color.GREEN);

        if (game.player.life>=0) {
            g.fillRect(81, 23, (int)game.player.life, 19);
        }

        //кол-во убитых врагов
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arail",1,20));
        g.drawString("Враги: "+game.countDiedEnemy, 200,40);


    }
}
