package main;

public class Game implements Runnable {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    //gameThread: A thread to run the game loop independently of the main application thread.
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200; // UPS: controls how often the game state is updated
    //created instance of the Game class without storing refernce to it
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        // When you call gamePanel.requestFocus(), you're asking the gamePanel to gain focus so that
        // it can start receiving input events like keyboard presses or mouse clicks.
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        gamePanel.updateGame();
    }


    @Override
    public void run() {
//      TimePerFrame calculates how much time (in nanoseconds) each frame should take to achieve that FPS.
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

//        The time (in nanoseconds) when the loop started.
        long previousTime = System.nanoTime();

//        frames and updates: Counters to track frames rendered and updates performed.
        int frames = 0;
        int updates = 0;
        long lastCheck = System.nanoTime();

//        Track how much time has passed since the last update and frame render.
        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

//            If enough time has passed for an update (deltaU >= 1), the game state is updated, and the updates counter is incremented.
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
//            If enough time has passed for a frame render (deltaF >= 1), the GamePanel is repainted, and the frames counter is incremented.
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }
    }
}

