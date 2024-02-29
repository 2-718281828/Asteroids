package src;

import entity.*;
import renderer.*;
import maths.*;

import java.io.Console;

public class Asteroid extends Entity {

	double size;
	Vector3 invPosition = new Vector3(0, 0, 0);
	MainRenderer renderer;

	public Asteroid(Model model, Vector3 position, EntityHandler entityHandler, Vector3 velocity, double size, MainRenderer renderer) {
		super(model, position, entityHandler);
		this.velocity = velocity;
		this.size = size;
		this.renderer = renderer;
		model.scale(size * 0.04);
		updateHitbox();
	}

	public void logic() {
			invPosition.x = position.x;
	invPosition.y = position.y;
        if (position.x <= -1){
            position.x = 1;
        }
        else if(position.x >= 1){
            position.x = -1;
        }
        if(position.y <= -1*(renderer.dimensions.y/renderer.dimensions.x)){
            position.y = 1*(renderer.dimensions.y/renderer.dimensions.x);
        }
        else if(position.y >= 1*(renderer.dimensions.y/renderer.dimensions.x)){
            position.y = -1*(renderer.dimensions.y/renderer.dimensions.x);
        }

        invPosition.multiply(-1);
        invPosition.add(position);
        model.move(invPosition);

        // przemieszcza obiekt i model
	    position.add(velocity);
	    model.move(velocity);


//		for (int i = 0; i < entityHandler.entities.size(); i++) {
//			if (entityHandler.entities.get(i) != this) {
//				if (collision(entityHandler.entities.get(i).hitbox)) {
//					if (entityHandler.entities.get(i).getClass() == Player.class) {
//						util.Console.log("Kolizja z asteroidą");
//						entityHandler.entities.get(i).model.remove(renderer.triangles);
//						entityHandler.entities.remove(entityHandler.entities.get(i));
//					}
//				}
//			}
//		}



		updateHitbox();
	}

}