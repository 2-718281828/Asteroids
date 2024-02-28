package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class Title extends Entity {
    public Title(Model model, Vector3 vector3, EntityHandler entityHandler) {
        super(model, vector3, entityHandler);
        model.scale(0.01);
    }

    @Override
    public void logic() {

    }
}