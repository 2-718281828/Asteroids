package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector2;
import maths.Vector3;
import renderer.*;
import util.Console;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.random.*;

public class MainRenderer extends Renderer {
	public boolean game = false;

	Random random = new Random();

	public Triangles triangles;
	public Triangles triangles1;
	public Model playerM, playerMMoving;
	public EntityHandler entityHandler = new EntityHandler(); // tworzymy nowy entityHanlder - zarzadza obiektamii
	public EntityHandler entityHandler1 = new EntityHandler();
	URL classPath = getClass().getResource("player.model"); // żebyśmy nie musieli tego pisać za każdym razem
	URL classPathh = getClass().getResource("playerMoving.model"); // żebyśmy nie musieli tego pisać za każdym razem
	URL asteroid1 = getClass().getResource("asteroida1.model");
	URL asteroid2 = getClass().getResource("a2.model");
	URL asteroid3 = getClass().getResource("a3.model");
	URL ufo = getClass().getResource("ufo11.model");
	URL title = getClass().getResource("text1.model");
	public HUD hud;
	public Player player;
	Camera camera;

	public MainRenderer(Vector2 dimensions, Camera camera) {
		super(dimensions, camera);//
		this.camera = camera;
		triangles = new Triangles();
		triangles1 = new Triangles();
		try {
			playerM = LoadModel.loadModel(new File(classPath.toURI()), Color.white, camera.renderer, camera); // ładujemy model z pliku
			playerMMoving = LoadModel.loadModel(new File(classPathh.toURI()), Color.white, camera.renderer, camera);

		} catch (Exception e) {
			e.printStackTrace();
		}

		player = new Player(playerM, new Vector3(0, 0, 0), entityHandler, this);
		KeyHandler keyHandler1 = new KeyHandler(player);
		entityHandler.entities.add(player);

		for (int i = 0; i < 10; i++) {
			spawnAsteroid(0.7, true, null, triangles1, entityHandler1);
		}

		spawnTitle();

		hud = new HUD(player);
		addKeyListener(keyHandler1);
		addKeyListener(camera);
		playerM.scale(0.5);
		playerMMoving.scale(0.5);
	}

	public void startGame() {
		for (int i = 0; i < 6; i++) {
			spawnAsteroid(0.7, true, null, triangles, entityHandler);
		}
		player.crashP = false;
		player.time1 = 0;
		player.position.multiply(-1);
		player.model.move(player.position);
		player.velocity = new Vector3(0, 0, 0);
		player.position = new Vector3(0, 0, 0);
		player.model.rotate(2, -player.theta);
		player.theta = 0;
		player.model.init(triangles);
		hud.score = 0;
	}

	public void endGame() {
		for (int j = 0; j < 5; j++)
			for (int i = 0; i < entityHandler.entities.size(); i++) {
				if (entityHandler.entities.get(i).getClass() == Asteroid.class || entityHandler.entities.get(i).getClass() == Ufo.class) {
					entityHandler.entities.get(i).model.remove(triangles);
					entityHandler.entities.remove(entityHandler.entities.get(i));
				}
			}
	}

	public void render(Graphics2D graphics) {
		if (!game) {
			triangles1.render(graphics);
		} else {
			triangles.render(graphics);
		}
		hud.render(graphics);
	}

	public void spawnTitle() {
		Model text = null;
		try {
			text = LoadModel.loadModel(new File(title.toURI()), Color.white, camera.renderer, camera);
		} catch (Exception e) {
			e.printStackTrace();
		}
		text.scale(0.012);
		text.init(triangles1);
		Vector3 v = new Vector3(text.rotationAxis);
		v.multiply(-1);
		text.move(v);
		text.move(new Vector3(0.08,0.22,0));
	}

	public void spawnUfo() {
		int b = random.nextInt(2);
		double p = random.nextDouble(2 * dimensions.y / dimensions.x) - (dimensions.y / dimensions.x);
		double vx = 0.0025;
		Model ufo1 = null;
		switch (b) {
			case 0:
				try {
					ufo1 = LoadModel.loadModel(new File(ufo.toURI()), Color.white, camera.renderer, camera);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ufo1.init(triangles);
				entityHandler.entities.add(new Ufo(ufo1, new Vector3(-1, p, 0), entityHandler, new Vector3(vx, 0, 0), this, camera));
				break;
			case 1:
				try {
					ufo1 = LoadModel.loadModel(new File(ufo.toURI()), Color.white, camera.renderer, camera);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ufo1.init(triangles);
				entityHandler.entities.add(new Ufo(ufo1, new Vector3(1, p, 0), entityHandler, new Vector3(-vx, 0, 0),  this, camera));
				break;

		}
	}

	public void spawnAsteroid(double size, boolean randomPosition, Vector3 position, Triangles triangles, EntityHandler entityHandler) {
		int a = random.nextInt(3);
		double x, y;
		if (randomPosition) {
			x = random.nextDouble(2) - 1;
			y = random.nextDouble(2) - 1;
		} else {
			x = position.x;
			y = position.y;
		}
		//predkosc
		double vx = random.nextDouble(0.01) - 0.005;
		double vy = random.nextDouble(0.01) - 0.005;
		Model asteroidM = null;
		switch (a) {
			case 0:
				try {
					asteroidM = LoadModel.loadModel(new File(asteroid1.toURI()), Color.white, camera.renderer, camera);
				} catch (Exception e) {
					e.printStackTrace();
				}
				asteroidM.init(triangles);
				entityHandler.entities.add(new Asteroid(asteroidM, new Vector3(x, y, 0), entityHandler, new Vector3(vx, vy, 0), size, this));
				break;
			case 1:
				try {
					asteroidM= LoadModel.loadModel(new File(asteroid2.toURI()), Color.white, camera.renderer, camera);
				} catch (Exception e) {
					e.printStackTrace();
				}
				asteroidM.init(triangles);
				entityHandler.entities.add(new Asteroid(asteroidM, new Vector3(x, y, 0), entityHandler, new Vector3(vx, vy, 0), size, this));
				break;


			case 2:
				try {
					asteroidM= LoadModel.loadModel(new File(asteroid3.toURI()), Color.white, camera.renderer, camera);
				} catch (Exception e) {
					e.printStackTrace();
				}
				asteroidM.init(triangles);
				entityHandler.entities.add(new Asteroid(asteroidM, new Vector3(x, y, 0), entityHandler, new Vector3(vx, vy, 0), size, this));
				break;
		}

	}
}

//    URL classPath = getClass().getResource("player.model"); // żebyśmy nie musieli tego pisać za każdym razem
//    public MainRenderer(Vector2 dimensions, Camera camera) {
//        super(dimensions, camera);//
//        triangles = new Triangles();
//        System.out.print(classPath);
//        try {
//            torus = LoadModel.loadModel(new File(classPath.toURI()), Color.white, camera.renderer, camera); // ładujemy model z pliku
//            torus.init(triangles); // inicjalizujemy model (wymagane)
