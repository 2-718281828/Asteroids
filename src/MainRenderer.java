package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector2;
import maths.Vector3;
import renderer.*;

import java.awt.*;
import java.io.Console;
import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.random.*;

public class MainRenderer extends Renderer {

    Random random = new Random();

    public Triangles triangles;
    public Model playerM, playerMMoving;
    public EntityHandler entityHandler = new EntityHandler(); // tworzymy nowy entityHanlder - zarzadza obiektamii
    URL classPath = getClass().getResource("player.model"); // żebyśmy nie musieli tego pisać za każdym razem
    URL classPathh = getClass().getResource("playerMoving.model"); // żebyśmy nie musieli tego pisać za każdym razem
    URL asteroid1 = getClass().getResource("asteroida1.model");
    URL asteroid2 = getClass().getResource("a2.model");
    URL asteroid3 = getClass().getResource("a3.model");
    URL ufo = getClass().getResource("ufo11.model");
    Model asteroidM, asteroidMM, asteroidMMM, ufo1;
    public HUD hud;

    public MainRenderer(Vector2 dimensions, Camera camera) {
        super(dimensions, camera);//
        triangles = new Triangles();
        try {
            playerM = LoadModel.loadModel(new File(classPath.toURI()), Color.white, camera.renderer, camera); // ładujemy model z pliku
            playerMMoving = LoadModel.loadModel(new File(classPathh.toURI()), Color.white, camera.renderer, camera);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Player player = new Player(playerM, new Vector3(0, 0, 0), entityHandler, this);
        KeyHandler keyHandler1 = new KeyHandler(player);
        entityHandler.entities.add(player);

        for (int i = 0; i < 6; i++) {
            spawnAsteroid(0.7, true, null);
        }
        spawnUfo();


        hud = new HUD(player);
        addKeyListener(keyHandler1);
        addKeyListener(camera);
        playerM.scale(0.5);
        playerMMoving.scale(0.5);
        player.model.init(triangles);
    }

    public void render(Graphics2D graphics) {
        triangles.render(graphics);
        hud.render(graphics);
    }

    public void spawnUfo() {
        int b = random.nextInt(2);
        double p = random.nextDouble(2*dimensions.y/dimensions.x) - (dimensions.y/dimensions.x);
        double vx = 0.0025;
        switch (b) {
            case 0:
                try {
                    ufo1 = LoadModel.loadModel(new File(ufo.toURI()), Color.white, camera.renderer, camera);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ufo1.init(triangles);
                entityHandler.entities.add(new Ufo(ufo1, new Vector3(-1, p, 0), entityHandler, new Vector3(vx, 0, 0), this));
                break;
            case 1:
                try {
                    ufo1 = LoadModel.loadModel(new File(ufo.toURI()), Color.white, camera.renderer, camera);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ufo1.init(triangles);
                entityHandler.entities.add(new Ufo(ufo1, new Vector3(1, p, 0), entityHandler, new Vector3(-vx, 0, 0),  this));
                break;

        }
        util.Console.log(p);
    }

    public void spawnAsteroid(double size, boolean randomPosition, Vector3 position) {
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
                    asteroidMM = LoadModel.loadModel(new File(asteroid2.toURI()), Color.white, camera.renderer, camera);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                asteroidMM.init(triangles);
                entityHandler.entities.add(new Asteroid(asteroidMM, new Vector3(x, y, 0), entityHandler, new Vector3(vx, vy, 0), size, this));
                break;


            case 2:
                try {
                    asteroidMMM = LoadModel.loadModel(new File(asteroid3.toURI()), Color.white, camera.renderer, camera);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                asteroidMMM.init(triangles);
                entityHandler.entities.add(new Asteroid(asteroidMMM, new Vector3(x, y, 0), entityHandler, new Vector3(vx, vy, 0), size, this));
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