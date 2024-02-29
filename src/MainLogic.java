package src;

import com.sun.tools.javac.Main;
import engine.Logic;
import entity.EntityHandler;
import renderer.Camera;

public class MainLogic implements Logic {

	public Camera camera; // ten obiekt jest publiczny dla całej klasy i innych klas mających dostęp do tej klasy

	public MainLogic(Camera camera) {
		this.camera = camera; // this.camera odnosi się do kamery publicznej dla całej klasy, a camera jest dostępna tylko dla tego konstruktora, dlatego chcemy "upublicznić" kamerę, żeby móc z niej korzystać w innych funkcjach
	}

	int time = 0;
	int asteroidCount = 0;
	int counterTime = 0;
	int round = 0;
	int newScore = 0;
	int prevScore = 0;
	public void update() {
		if(((MainRenderer)camera.renderer).game){
			camera.update(); // aktualizacja kameryy
			((MainRenderer) camera.renderer).entityHandler.logic();
			time++;
			counterTime++;
			newScore += ((MainRenderer)camera.renderer).hud.score - prevScore;
			util.Console.log(newScore);

			if (newScore >=10000) {
				newScore -= 10000;
				((MainRenderer)camera.renderer).hud.lifes++;

			}
			if (time >= 60 * 20) {
				((MainRenderer) camera.renderer).spawnUfo();
				time = 0;
			}
			if (counterTime >= 20) {
				asteroidCount = 0;
				for (int i = 0; i < ((MainRenderer)camera.renderer).entityHandler.entities.size(); i++)
					if (((MainRenderer)camera.renderer).entityHandler.entities.get(i).getClass() == Asteroid.class)
						asteroidCount++;
				if (asteroidCount <= 0) {
					round++;
					for (int i = 0; i < 6*(1+round/2); i++) {
						((MainRenderer)camera.renderer).spawnAsteroid(0.7, true, null, ((MainRenderer) camera.renderer).triangles, ((MainRenderer) camera.renderer).entityHandler);
					}
				}
			}
			prevScore = ((MainRenderer)camera.renderer).hud.score;
		} else {
			round = 0;
			newScore = 0;
			time = 0;
			((MainRenderer) camera.renderer).entityHandler1.logic();
		}
		((MainRenderer) camera.renderer).hud.update();
	}

}
