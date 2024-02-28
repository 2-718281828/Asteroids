package src;

import java.awt.*;
import util.Console;

public class HUD {
	public int score;
	public int lifes = 3;
	Player player;
	Font font;
	public HUD(Player player) {
		this.player = player;
		font = new Font("OCR A Extended", Font.PLAIN, 30);
	}

	public void update() {

	}

	public void render(Graphics2D graphics) {
		graphics.setColor(Color.white);
		graphics.setFont(font);
		if(!player.renderer.game){
			graphics.drawString("Press ENTER to play", 0, 0);
		}
		graphics.setColor(Color.white);
		graphics.setFont(font);
		graphics.drawString("Score: " + score, 10, 30);
		graphics.drawString("Lifes: " + lifes, 10, 60);
	}
}