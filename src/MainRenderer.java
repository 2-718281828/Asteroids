package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector2;
import maths.Vector3;
import renderer.*;
import util.Console;

import java.awt.*;
import java.io.File;

public class MainRenderer extends Renderer {

    public Triangles triangles;
    public Model torus;
    String classPath = getClass().getResource("").getPath(); // żebyśmy nie musieli tego pisać za każdym razem
     public MainRenderer(Vector2 dimensions, Camera camera) {
            super(dimensions, camera);//
            triangles = new Triangles();
            try {
		Console.log(classPath + "torus.model");
                torus = LoadModel.loadModel(new File(classPath + "torus.model"), Color.green, camera.renderer, camera); // ładujemy model z pliku
                torus.init(triangles); // inicjalizujemy model (wymagane)
            } catch (Exception e) {
                e.printStackTrace();
            }

            KeyHandler keyHandler = new KeyHandler(new Player(torus, new Vector3(0,0,0),entityHandler)); // nowy keyhanlder ktory rejestruje przyciski od gracza, jako agrument dajemy mu nowego gracza, ktory znajduje sie w 0,0,0, jego modelem jest torus
	    this.addKeyListener(keyHandler); // dodajemy keyhandler jako sluchacz przyciskow

         Player player = new Player(torus, new Vector3(0,0,0), entityHandler);
         KeyHandler keyHandler1 = new KeyHandler(player);
	player.model.init(triangles);
     }
    public EntityHandler entityHandler = new EntityHandler(); // tworzymy nowy entityHanlder - zarzadza obiektami

     public void render(Graphics2D graphics) {
         triangles.render(graphics);
     }
}
