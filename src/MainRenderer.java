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
import java.util.concurrent.CompletionService;

public class MainRenderer extends Renderer {

    public Triangles triangles;
    public Model playerM, playerMMoving;
    public EntityHandler entityHandler = new EntityHandler(); // tworzymy nowy entityHanlder - zarzadza obiektamii
    URL classPath = getClass().getResource("player.model"); // żebyśmy nie musieli tego pisać za każdym razem
    URL classPathh = getClass().getResource("playerMoving.model"); // żebyśmy nie musieli tego pisać za każdym razem
URL asteroid1 = getClass().getResource("asteroida1.model");
URL asteroid2 = getClass().getResource("a2.model");
URL asteroid3 = getClass().getResource("a3.model");
Model asteroidM, asteroidMM, asteroidMMM;

     public MainRenderer(Vector2 dimensions, Camera camera) {
            super(dimensions, camera);//
            triangles = new Triangles();
            try {
                playerM = LoadModel.loadModel(new File(classPath.toURI()), Color.white, camera.renderer, camera); // ładujemy model z pliku
		        playerMMoving = LoadModel.loadModel(new File(classPathh.toURI()), Color.white, camera.renderer, camera);
                asteroidM = LoadModel.loadModel(new File(asteroid1.toURI()), Color.white, camera.renderer, camera);
                asteroidMM = LoadModel.loadModel(new File(asteroid2.toURI()), Color.white, camera.renderer, camera);
                asteroidMMM = LoadModel.loadModel(new File(asteroid3.toURI()), Color.white, camera.renderer, camera);

            } catch (Exception e) {
                e.printStackTrace();
            }

         Player player = new Player(playerM, new Vector3(0,0,0), entityHandler, this);
         KeyHandler keyHandler1 = new KeyHandler(player);
         entityHandler.entities.add(player);
	 entityHandler.entities.add(new Asteroid(asteroidM, new Vector3(0, 0, 0), entityHandler, new Vector3(0.01, -0.01, 0), 1, this));
	asteroidM.init(triangles);
	asteroidMM.init(triangles);
	asteroidMMM.init(triangles);
         addKeyListener(keyHandler1);
	 addKeyListener(camera);
	 playerM.scale(0.5);
	 playerMMoving.scale(0.5);
	player.model.init(triangles);
     }

     public void render(Graphics2D graphics) {
         triangles.render(graphics);
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