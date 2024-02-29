package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
import renderer.*;
import java.util.Random;
import java.io.*;
import java.awt.*;
import java.net.URL;

public class Ufo extends Entity {
    MainRenderer renderer;
    Camera camera;
    Random random;
	URL pocisk = getClass().getResource("pocisk.model");

    public Ufo(Model model, Vector3 position, EntityHandler entityHandler, Vector3 velocity, MainRenderer renderer, Camera camera){
        super(model, position, entityHandler);
        this.renderer = renderer;
        this.velocity = velocity;
        model.scale(0.001);
	random = new Random();

    }
    int timer = 0;
    @Override
    public void logic() {
	    timer ++;
	    if (timer >= 0.7 * 60) {
		timer = 0; 
		try {
		Model modelB = LoadModel.loadModel(new File(pocisk.toURI()), Color.white, renderer, camera);
		entityHandler.entities.add(new BulletUfo(modelB, new Vector3(position), entityHandler, new Vector3(random.nextDouble(0.05)-0.025, random.nextDouble(0.05)-0.025, 0), renderer));
		modelB.init(renderer.triangles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    }
        // przemieszcza obiekt i model
        position.add(velocity);
        model.move(velocity);

    }
}
