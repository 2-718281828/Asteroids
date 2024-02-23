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
    public EntityHandler entityHandler = new EntityHandler(); // tworzymy nowy entityHanlder - zarzadza obiektami
    URL classPath = getClass().getResource("player.model"); // żebyśmy nie musieli tego pisać za każdym razem
    URL classPathh = getClass().getResource("playerMoving.model"); // żebyśmy nie musieli tego pisać za każdym razem

     public MainRenderer(Vector2 dimensions, Camera camera) {
            super(dimensions, camera);//
            triangles = new Triangles();
            try {
                playerM = LoadModel.loadModel(new File(classPath.toURI()), Color.white, camera.renderer, camera); // ładujemy model z pliku
		playerMMoving = LoadModel.loadModel(new File(classPathh.toURI()), Color.white, camera.renderer, camera);
            } catch (Exception e) {
                e.printStackTrace();
            }
         Player player = new Player(playerM, new Vector3(0,0,0), entityHandler, this);
         KeyHandler keyHandler1 = new KeyHandler(player);
         entityHandler.entities.add(player);
         addKeyListener(keyHandler1);
	 addKeyListener(camera);
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