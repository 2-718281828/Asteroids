package src;

import maths.Vector2;
import maths.Vector3;
import renderer.*;
import util.Console;

import java.awt.*;
import java.io.File;

public class MainRenderer extends Renderer {

    public Triangles triangles;
    public Model torus;
    String classPath = getClass().getResource("torus.model").getPath(); // żebyśmy nie musieli tego pisać za każdym razem
     public MainRenderer(Vector2 dimensions, Camera camera) {
            super(dimensions, camera);
            triangles = new Triangles();
            try {
                torus = LoadModel.loadModel(new File(), Color.green, camera.renderer, camera); // ładujemy model z pliku
                torus.init(triangles); // inicjalizujemy model (wymagane)
            } catch (Exception e) {
                e.printStackTrace();
            }
            KeyHandler keyHandler = new KeyHandler(new Player(torus, new Vector3(0,0,0),));
     }

     public void render(Graphics2D graphics) {
         triangles.render(graphics);
     }

}