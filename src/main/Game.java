package main;

public class Game implements Runnable {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    private final int FPS_SET = 120;
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

    @Override
    public void run() {
//        TimePerFrame calculates how much time (in nanoseconds) each frame should take to achieve that
        double TimePerFrame = 1000000000.0 / FPS_SET;
//        LastFrame keeps track of the time when the last frame was displayed, so we know when the next frame should be drawn.
        long lastFrame = System.nanoTime();
//        now represents the current time at any given point in the game loop, fetched with System.nanoTime().
        long now = System.nanoTime();
        int frames = 0;
        long lastCheck = System.nanoTime();

        while(true){
//            gets the current time in nanoseconds
            now = System.nanoTime();
//            This condition checks if the differnece between the current time(now) and the time of the last frame(lastFrame)
//            is greater than ot equal to timePerFrame
//            if this is true, it means enough time has passed since the last frame, so its time to repaint the screen
            if(now - lastCheck > TimePerFrame){
//                it triggers the component to redraw itself, updating the visual on the screen
                gamePanel.repaint();
//                updating the lastCheck to the current time so that the timer is reset for the next frame
                lastCheck = now;
                frames++;
            }
//            if one second have passed since the last FPS, we do a new FPS check
//            save the last FPS check as the lastFPS check and request
            if(System.currentTimeMillis()-lastFrame > 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: "+frames);
                frames = 0;
            }
        }
    }
}
