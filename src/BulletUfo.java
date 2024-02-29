package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class BulletUfo extends Entity {
    MainRenderer renderer;
    int lifetime = 0;
    

    public BulletUfo(Model model, Vector3 vector3, EntityHandler entityHandler, Vector3 velocity, MainRenderer renderer){
        super(model, vector3, entityHandler);
        this.velocity = velocity;
        this.renderer = renderer;
        model.scale(0.005);
    }

    @Override
    public void logic() {

	    lifetime++;
	    if (lifetime >= 120) {
		    entityHandler.entities.remove(this);
		    model.remove(renderer.triangles);
	    }
	    util.Console.log("abc");
	    position.add(velocity);
	    model.move(velocity);
    }
}
