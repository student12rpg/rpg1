package window;

import entity.GameObject;
import entity.Player;
import entity.Tree;
import manage.KeyManager;
import manage.MouseManager;
import world.Level;

import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


public class Game implements Runnable {

	private Display display;

	public int width, height;
	public String title;

	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	private boolean running = false;

	public KeyManager keyManager;
	public MouseManager mouseManager;

	public ArrayList<GameObject> listRpgObjects = new ArrayList<GameObject>();
	public ArrayList<GameObject> listRemoveObjects = new ArrayList<>();

	public Player player = new Player(this, 300,300);
	//Tree tree = new Tree(this,100,350);
	public Level level = new Level(this);

	public int offsetX = 0;
	public int offsetY = 0;

	public void centerPlayer(int x, int y){
		offsetX = x - width/2;
		offsetY = y - height/2;
	}

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	//!!!!!Инициализация
	private void init() {
		display = new Display(this.title, width, height);
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		display.getCanvas().addMouseListener(mouseManager);
		display.getJFrame().addKeyListener(keyManager);

	}

	//!!!ОБНОВЛЕНИЕ
	private void move() {
		level.move();
		for (GameObject item : listRpgObjects) {
			item.move();
		}
		player.move();

		//удаление ненужных объектов
		for (GameObject item : listRemoveObjects) {
			listRpgObjects.remove(item);
		}
		//полная очистка кэша удаляемых объектов
		listRemoveObjects.clear();
	}

	//!!ПРОРИСОВКА
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		//Рисуем!!!
		///////////////////////////////////////////////////////////////
		g.clearRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(0,0,width,height);

		level.render(g);
		//tree.render(g);
		for (GameObject item : listRpgObjects) {
			item.render(g);
		}
		player.render(g);

		///////////////////////////////////////////////////////////////


		//end draw
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {

		init(); //инициализация

		int fps = 60;
		double kolNS = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		//бесконечный цикл пока running
		while (running) {
			now = System.nanoTime();

			delta += (now - lastTime) / kolNS;
			lastTime = now;

			if (delta >= 1) {
				move();  //пересчет объектов
				render();  //перерисовка объектов
				delta--;
			}
		}

		stop();
	}

	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
