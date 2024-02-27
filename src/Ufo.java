package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class Ufo extends Entity {
    MainRenderer renderer;
    Vector3 invPosition = new Vector3(0, 0, 0);

    public Ufo(Model model, Vector3 position, EntityHandler entityHandler, Vector3 velocity, double size, MainRenderer renderer){
        super(model, position, entityHandler);
        this.renderer = renderer;
        this.velocity = velocity;

    }
    @Override
    public void logic() {

        invPosition.multiply(-1);
        invPosition.add(position);
        model.move(invPosition);

        // przemieszcza obiekt i model
        position.add(velocity);
        model.move(velocity);

    }
}