package src;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.*;
import util.Console;

import java.awt.*;
import java.beans.Visibility;
import java.io.File;
import java.net.URL;

public class Player extends Entity {
	Camera camera = new Camera();
	boolean keys[] = new boolean[4];
	boolean space = false;
	boolean moving = false;
	public Model bulletM;
	MainRenderer renderer;
	public Triangles triangles;


	public Player(Model model, Vector3 vector3, EntityHandler entityHandler, MainRenderer renderer) {
		super(model, vector3, entityHandler);
		this.renderer = renderer;
		//model.scale(0.01);
	}

	double theta = 0; // kąt pod jakim gracz jest skierowany
	double s = 0.0003;
	double vPocisku = 0.005;
	double maxs = 0.01;
	double rotA = 0, rotB = 0;
	Vector3 posA = new Vector3(0, 0, 0);
	Vector3 posB = new Vector3(0, 0, 0);
	Vector3 invPosition = new Vector3(0, 0, 0);

	public void logic() {
		boolean prevMoving = moving;
		if (keys[2]) {
			// jeśli strzałka w prawo wciśnięta, obróć przeciwnie do wsk zegara
			theta -= 0.1;
			model.rotate(2, -.1);
		} else if (keys[3]) {
			// jeśli strzałka w lewo wciśnięta, obróć zgodnie z wsk zegara
			theta += 0.1;
			model.rotate(2, 0.1);
		}
		if (keys[0]) {
			moving = true;
			// dodanie do prędkości
			velocity.y += s * Math.cos(theta);
			velocity.x -= s * Math.sin(theta);

			double movingAngle = Math.atan2(-velocity.x, velocity.y); // kęt pod jakim porusza się (nie jest skierowany, tylko się porusza) statek
			// ograniczenie preękości
			if (Math.abs(velocity.y) >= Math.abs(maxs * Math.cos(movingAngle))) {
				velocity.y = maxs * Math.cos(movingAngle);
			}
			if (Math.abs(velocity.x) >= Math.abs(maxs * Math.sin(movingAngle))) {
				velocity.x = -maxs * Math.sin(movingAngle);
			}
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
		//bullet
		if(space){
			//spawnowanie pocisku, jego polozenie to position (polizenia gracza)
			URL classPathhh = getClass().getResource("pocisk.model"); // żebyśmy nie musieli tego pisać za każdym razem

			try {
				bulletM = LoadModel.loadModel(new File(classPathhh.toURI()), Color.white, camera.renderer, camera); // ładujemy model z pliku
			} catch (Exception e) {
				e.printStackTrace();
			}
			Bullet bullet = new Bullet(bulletM, new Vector3(0,0,0), entityHandler, new Vector3(0,0,0));
			entityHandler.entities.add(bullet);
			bullet.model.init(triangles);

			velocity.x = -vPocisku*Math.sin(theta);
			velocity.y = vPocisku*Math.cos(theta);




			//model = renderer.bulletM;
//			rotA = theta;
//			posA.x = model.rotationAxis.x;
//			posA.y = model.rotationAxis.y;
//			posA.z = model.rotationAxis.z;
//			model.remove(renderer.triangles);
//			model = renderer.playerMMoving;
//			model.rotate(2, -rotB);
//			model.rotate(2, theta);
//			posB.multiply(-1);
//			model.move(posB);
//			posB.multiply(-1);
//			model.move(position);
//			model.init(renderer.triangles);


//			Vector3 posA = new Vector3(0, 0, 0);
//			Vector3 posB = new Vector3(0, 0, 0);
//			Vector3 invPosition = new Vector3(0, 0, 0);

		}
	}
}