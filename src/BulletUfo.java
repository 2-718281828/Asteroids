package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class BulletUfo extends Entity {
    MainRenderer renderer;

    public BulletUfo(Model bulletM, Vector3 vector3, EntityHandler entityHandler, Vector3 velocity, MainRenderer renderer){
        super(bulletM, vector3, entityHandler);
        this.velocity = velocity;
        this.renderer = renderer;
        model.scale(0.005);

    }

    @Override
    public void logic() {

    }
}