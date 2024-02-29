package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class Ufo extends Entity {
    MainRenderer renderer;

    public Ufo(Model model, Vector3 position, EntityHandler entityHandler, Vector3 velocity, MainRenderer renderer){
        super(model, position, entityHandler);
        this.renderer = renderer;
        this.velocity = velocity;
        model.scale(0.001);

    }
    @Override
    public void logic() {
        // przemieszcza obiekt i model
        position.add(velocity);
        model.move(velocity);

    }
}