package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class Player extends Entity {
    boolean keys[] = new boolean[4];
    public Player(Model model, Vector3 vector3, EntityHandler entityHandler) {
        super(model, vector3, entityHandler);
    }

    public void logic() {

    }
}