package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.*;
import util.Console;

public class Player extends Entity {
    boolean keys[] = new boolean[4];
    boolean moving = false;
    MainRenderer renderer;
    public Player(Model model, Vector3 vector3, EntityHandler entityHandler, MainRenderer renderer) {
        super(model, vector3, entityHandler);
	this.renderer = renderer;
    }

    double theta = 0;
    double s = 0.00025;
    double maxs = 0.01;
    double rotA = 0, rotB = 0;
    Vector3 posA = new Vector3(0, 0, 0);
    Vector3 posB = new Vector3(0, 0, 0);

    public void logic() {
	    boolean prevMoving = moving;
        if(keys[2]){
            theta -= 0.1;
            model.rotate(2,-0.1);
        }
        else if(keys[3]){
            theta += 0.1;
            model.rotate(2,0.1);
        }

        if(keys[0]){ // w gore
	double c = 1-velocity.magnitude()/maxs;
           velocity.y +=  s * Math.cos(theta)*c;
           velocity.x -=  s * Math.sin(theta)*c;
	   moving = true;
        }
        else if(keys[1]){ // w dol
	    double c = 1-velocity.magnitude()/maxs;
            velocity.y += -s * Math.cos(theta)/2*c;
            velocity.x += s * Math.sin(theta)/2*c;
	    moving = true;
        } else {
		moving = false;
	}
	if (prevMoving != moving) {
		if (moving) {
			rotA = theta;
			posA.x = model.rotationAxis.x;
			posA.y = model.rotationAxis.y;
			posA.z = model.rotationAxis.z;
			model.remove(renderer.triangles);
			model = renderer.playerMMoving;
			model.rotate(2, -rotB);
			model.rotate(2, theta);
			posB.multiply(-1);
			model.move(posB);
			posB.multiply(-1);
			model.move(position);
			model.init(renderer.triangles);
		}
		else {
			rotB = theta;
			posB.x = model.rotationAxis.x;
			posB.y = model.rotationAxis.y;
			posB.z = model.rotationAxis.z;
			model.remove(renderer.triangles);
			model = renderer.playerM;
			model.rotate(2, -rotA);
			model.rotate(2, theta);
			posA.multiply(-1);
			model.move(posA);
			posA.multiply(-1);
			model.move(position);
			model.init(renderer.triangles);
		}
	}
        position.add(velocity);
        model.move(velocity);
    }
}
