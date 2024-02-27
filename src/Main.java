package src;

import engine.Engine;
import maths.Vector2;
import renderer.*;

import java.util.Locale;

public class Main {
    public static int WIDTH = 1280, HEIGHT = 720;
    public static String TITLE = "Gra wektorowa";

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US")); // ustawiamy lokalizację na US, żeby si się floaty ładowały
        Camera camera = new Camera();
        Renderer renderer = new MainRenderer(new Vector2(WIDTH, HEIGHT), camera);
        Window window = new Window(new Vector2(WIDTH, HEIGHT), TITLE, renderer);
	renderer.requestFocus();
        renderer.perspective = false;
        camera.enableRotationPitch = false; // obracanie się wokół osi x (góra - dół)
        camera.enableRotationYaw = false; // obracanie się wokół osi y (prawo - lewo)
        camera.enableMovement = false; // poruszanie się kamerą
        MainLogic mainLogic = new MainLogic(camera); // tutaj 60 razy na sekundę jest wykonywana logika
        Engine engine = new Engine(renderer, mainLogic); // tutaj jest pętla gryy
        engine.start(); // uruchamiamy silnik
    }
}