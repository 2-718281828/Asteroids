package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class Bullet extends Entity {

    public Bullet(Model bulletM, Vector3 vector3, EntityHandler entityHandler, Vector3 velocity) {
        super(bulletM, vector3, entityHandler);
        this.velocity = new Vector3(velocity);
        model.scale(0.05);
    }

    @Override
    public void logic() {

    }
}