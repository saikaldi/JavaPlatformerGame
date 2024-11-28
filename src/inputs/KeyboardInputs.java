package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    // Stores a reference to the GamePanel object to send updates about direction or movement.
    private GamePanel gamePanel;

    //    This is a constructor that accepts a GamePanel as a parameter.
    //    When we create a KeyboardInputs object with new KeyboardInputs(this),
    //    it takes the GamePanel reference and saves it in gamePanel.
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {  // getKeyCode() that returns an integer representing the key code of the pressed key
            case KeyEvent.VK_W:
                gamePanel.changeYDelta(-5);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(5);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(5);
                break;
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-5);
                break;


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}