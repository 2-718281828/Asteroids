package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
import util.Console;

public class Player extends Entity {//
    boolean keys[] = new boolean[4];
    public Player(Model model, Vector3 vector3, EntityHandler entityHandler) {
        super(model, vector3, entityHandler);
    }

    double theta = 0;
    double s = 0.01;

    public void logic() {
        velocity.x = 0;
        velocity.y = 0;
        if(keys[2]){
            theta += 0.1;
            model.rotate(2,-0.1);
        }
        else if(keys[3]){
            theta -= 0.1;
            model.rotate(2,0.1);
        }

        if(keys[0]){ //w gore
           velocity.y =  s * Math.cos(theta);
           velocity.x =  s * Math.sin(theta);
        }
        else if(keys[1]){
            velocity.y = -s * Math.cos(theta)/2;
            velocity.x = -s * Math.sin(theta)/2;
        }
        model.updateVerticies();
        position.add(velocity);
        model.move(velocity);






    }
}