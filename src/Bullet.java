package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class Bullet extends Entity {

    MainRenderer renderer;
    Vector3 invPosition = new Vector3(0, 0, 0);


    public Bullet(Model bulletM, Vector3 vector3, EntityHandler entityHandler, Vector3 velocity, MainRenderer renderer) {
        super(bulletM, vector3, entityHandler);
        this.velocity = velocity;
        this.renderer = renderer;
        model.scale(0.005);
    }

    int t = 0;

    @Override
    public void logic() {

        // odmierza czas aby usunąć ten obiekt
        t++;
        if (t >= 70) {
            model.remove(renderer.triangles);
            entityHandler.entities.remove(this);
        }


        invPosition.x = position.x;
        invPosition.y = position.y;
        if (position.x <= -1) {
            position.x = 1;
        } else if (position.x >= 1) {
            position.x = -1;
        }
        if (position.y <= -1 * (renderer.dimensions.y / renderer.dimensions.x)) {
            position.y = 1 * (renderer.dimensions.y / renderer.dimensions.x);
        } else if (position.y >= 1 * (renderer.dimensions.y / renderer.dimensions.x)) {
            position.y = -1 * (renderer.dimensions.y / renderer.dimensions.x);
        }

        invPosition.multiply(-1);
        invPosition.add(position);
        model.move(invPosition);

        // przemieszcza obiekt i model
        position.add(velocity);
        model.move(velocity);

        for (int i = 0; i < entityHandler.entities.size(); i++) {
            if (entityHandler.entities.get(i) != this) {
                if (collision(entityHandler.entities.get(i).hitbox)) {
                    if (entityHandler.entities.get(i).getClass() == Asteroid.class) {
                        util.Console.log("Kolizja z asteroidą (pocisk)");
                        entityHandler.entities.get(i).model.remove(renderer.triangles);
                        entityHandler.entities.remove(entityHandler.entities.get(i));
                    }
                }
            }
        }
    }

}