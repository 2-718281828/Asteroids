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

	double theta = 0; // kąt pod jakim gracz jest skierowany
	double s = 0.0005;
	double maxs = 0.01;
	double rotA = 0, rotB = 0;
	Vector3 posA = new Vector3(0, 0, 0);
	Vector3 posB = new Vector3(0, 0, 0);
	Vector3 invPosition = new Vector3(0, 0, 0);

	public void logic() {
		boolean prevMoving = moving;
		if (keys[2]) {
			// jeśli strzałka w lewo wciśnięta, obróć przeciwnie do wsk zegara
			theta -= 0.1;
			model.rotate(2, -0.1);
		} else if (keys[3]) {
			// jeśli strzałka w prawo wciśnięta, obróć zgodnie z wsk zegara
			theta += 0.1;
			model.rotate(2, 0.1);
		}

		if (keys[0]) { 
			moving = true;
			double movingAngle = Math.atan2(velocity.y, velocity.x); // kęt pod jakim porusza się (nie jest skierowany, tylko się porusza) statek
			// ograniczenie preękości
			if (Math.abs(velocity.y) >= Math.abs(maxs * Math.cos(movingAngle))) {
				if (velocity.y < 0) {
					velocity.y = -Math.abs(maxs * Math.cos(movingAngle));
				}
				else if (velocity.y >= 0) {
					velocity.y = Math.abs(maxs * Math.cos(movingAngle));
				}
			}
			if (Math.abs(velocity.x) >= Math.abs(maxs * Math.sin(movingAngle))) {
				if (velocity.x < 0) {
					velocity.x = -Math.abs(maxs * Math.sin(movingAngle));
				}
				else if (velocity.x >= 0) {
					velocity.x = Math.abs(maxs * Math.sin(movingAngle));
				}
			}
			// dodanie do prędkości
			velocity.y += s * Math.cos(theta);
			velocity.x -= s * Math.sin(theta);
		} else {
			moving = false;
		}
		if (prevMoving != moving) { // nie sprawdza wartości zmiennej moving, ale sprawdza czy ta wartość się zmieniła w ostatniej klatce
			if (moving) {
				// jeśli wykryto zmianę zmiennej moving na true, to zmień model na ten z płomykiem
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
			} else {
				// jeśli wykryto zmianę zmiennej moving na false, to zmień model na zwykły
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

		invPosition.x = -position.x;
		invPosition.y = -position.y;
		invPosition.z = -position.z;
		position.add(velocity); // przemieszcza obiekt gracza

		// teleportuje gracza na drugą stronę ekranu jeśli za niego wyjdzie
		if (position.x <= -1){
			position.x = 1;
		}
		else if(position.x >= 1){
			position.x = -1;
		}
		if(position.y <= -1*(renderer.dimensions.y/renderer.dimensions.x)){
			position.y = 1*(renderer.dimensions.y/renderer.dimensions.x);
		}
		else if(position.y >= 1*(renderer.dimensions.y/renderer.dimensions.x)){
			position.y = -1*(renderer.dimensions.y/renderer.dimensions.x);
		}

		invPosition.add(position);
		model.move(invPosition); // przemieszcza model gracza
					 // invPosition to różnica położenia obiektu a modelu, dzięki temu program wie o ile ma przemieśćic gracza, gdyby przemieszczać model po prostu o velocity, to nie teleportował by on się na granicach ekranu
	}
}
