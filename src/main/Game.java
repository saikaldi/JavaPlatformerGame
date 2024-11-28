package main;

public class Game {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    //created instance of the Game class without storing refernce to it
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        // When you call gamePanel.requestFocus(), you're asking the gamePanel to gain focus so that
        // it can start receiving input events like keyboard presses or mouse clicks.
        gamePanel.requestFocus();
    }
}
