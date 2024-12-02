package main;
import javax.swing.*;

public class GameWindow {
    // jframe is declared as a private instance variable, so it can be used across methods within the class.
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {

        // Create a JFrame instance
        jframe = new JFrame();
        jframe.setSize(800, 600);
        // On click close exit the program
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        // Window to be visible
        jframe.setVisible(true);
    }
}
