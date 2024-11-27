package main;

public class Game {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    //created instance of the Game class without storing refernce to it
    public Game(){
        gameWindow = new GameWindow();
        gamePanel = new GamePanel();
    }
}
