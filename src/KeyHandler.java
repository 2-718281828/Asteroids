package src;

import entity.EntityHandler;
import maths.Vector3;
import util.Console;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

	Player player;

	public KeyHandler(Player player){
		this.player = player;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player.keys[0] = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.keys[1] = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.keys[2] = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.keys[3] = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			player.space = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player.keys[0] = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.keys[1] = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.keys[2] = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.keys[3] = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			player.space = false;
		}
		if(!player.renderer.game) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				player.renderer.game = true;
				player.renderer.startGame();
				try {
					Sounds.play("1.wav");
				}
				catch (Exception q) {
					q.printStackTrace();
				}
			}
		}

	}
}
