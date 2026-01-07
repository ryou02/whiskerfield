package main;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	Gamepanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, rPressed, tPressed;

	public KeyHandler(Gamepanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_ESCAPE) {
			if (gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			} else if (gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}

		// Exit dialogue state when WASD is pressed
		if (gp.gameState == gp.dialogueState) {
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_A || 
				code == KeyEvent.VK_S || code == KeyEvent.VK_D) {
				gp.gameState = gp.playState;
				gp.ui.resetDialogue(); // Reset typing effect when exiting dialogue
				gp.stopDialogueMusic(); // Stop dialogue music
			}
		}

		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_E) {
			ePressed = true;
		}
		if (code == KeyEvent.VK_R) {
			rPressed = true;
		}
		if (code == KeyEvent.VK_T) {
			tPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_E) {
			ePressed = false;
		}
		if (code == KeyEvent.VK_R) {
			rPressed = false;
		}
		if (code == KeyEvent.VK_T) {
			tPressed = false;
		}
	}
}
