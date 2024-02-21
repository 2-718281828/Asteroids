package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
import util.Console;

public class Player extends Entity {//
    boolean keys[] = new boolean[4];
    boolean moving = false;
    public Player(Model model, Vector3 vector3, EntityHandler entityHandler) {
        super(model, vector3, entityHandler);
    }

    double theta = 0;
    double s = 0.00025;
    double maxs = 0.01;

    public void logic() {
	moving = false;
        if(keys[2]){
            theta += 0.1;
            model.rotate(2,-0.1);
        }
        else if(keys[3]){
            theta -= 0.1;
            model.rotate(2,0.1);
        }

        if(keys[0]){ // w gore
           velocity.y +=  s * Math.cos(theta);
           velocity.x +=  s * Math.sin(theta);
	   moving = true;
        }
        else if(keys[1]){ // w dol
	    double c = 1-velocity.magnitude()/maxs;
            velocity.y += -s * Math.cos(theta)/2*c;
            velocity.x += -s * Math.sin(theta)/2*c;
	    moving = true;
        }
        position.add(velocity);
        model.move(velocity);
    }
}
