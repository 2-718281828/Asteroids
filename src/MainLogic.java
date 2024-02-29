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

        public void update() {
            if(((MainRenderer)camera.renderer).game){
                camera.update(); // aktualizacja kameryy
                ((MainRenderer) camera.renderer).entityHandler.logic();
            } else {
                ((MainRenderer) camera.renderer).entityHandler1.logic();
            }
            ((MainRenderer) camera.renderer).hud.update();
        }

}