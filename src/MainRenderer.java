package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector2;
import maths.Vector3;
import renderer.*;
import util.Console;

import java.awt.*;
import java.io.File;
import java.util.concurrent.CompletionService;

public class MainRenderer extends Renderer {

    public Triangles triangles;
    public Model torus;
    public EntityHandler entityHandler = new EntityHandler(); // tworzymy nowy entityHanlder - zarzadza obiektami

    String classPath = getClass().getResource("player.model").getPath(); // żebyśmy nie musieli tego pisać za każdym razem
     public MainRenderer(Vector2 dimensions, Camera camera) {
            super(dimensions, camera);//
            triangles = new Triangles();
            System.out.print(classPath);
            try {
                torus = LoadModel.loadModel(new File("player.model"), Color.green, camera.renderer, camera); // ładujemy model z pliku
                torus.init(triangles); // inicjalizujemy model (wymagane)
            } catch (Exception e) {
                e.printStackTrace();
            }
         Player player = new Player(torus, new Vector3(0,0,0), entityHandler);
         KeyHandler keyHandler1 = new KeyHandler(player);
         entityHandler.entities.add(player);
         addKeyListener(keyHandler1);

	player.model.init(triangles);
     }

     public void render(Graphics2D graphics) {
         triangles.render(graphics);
     }
}