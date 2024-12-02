package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utilz.Constant.PlayerConstants.*;
import static utilz.Constant.Directions.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs; // an instance of a class that implements the MouseListener interface
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;


    public GamePanel() {
        mouseInputs = new MouseInputs(this); // Initialize mouse input handling

        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        // This is a method from a JComponent (like JPanel or JFrame) that registers
        // a MouseListener with the component so that the component can handle mouse events.
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
    }

    public void importImg(){
        InputStream is = getClass().getResourceAsStream("/player.png");
        try {
            img = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1200, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }


    public void  setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void  setMoving(boolean moving){
        this.moving = moving;

    }

    public void changeYDelta(int value){
        this.yDelta += value;
    }

    private void updateAnimationTick(){ // Updates animation frame based on timing

        aniTick++; // Increment animation tick counter
        if(aniTick >= aniSpeed){ // If tick count reaches animation speed threshold
            aniTick = 0; // Reset tick counter
            aniIndex++; // Move to the next frame
            if(aniIndex >= GetSpriteAmount(playerAction)){ // If end of animation frames is reached
                aniIndex = 0; // Reset frame index to loop animation
            }
        }
    }



    private void setAnimation() {
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    private void updatePos() {
        if(moving){
            switch (playerDir){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    public void updateGame(){
        setAnimation();
        updateAnimationTick();
        updatePos();
    }



    public void paint(Graphics g) {
//        actually, it is calling JComponent's paintComponent
//        JComponent is the superclass of JPanel
//        public class JPanel extends JComponent implements Accessible
        super.paintComponent(g);
        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta,128, 80, null);
    }



}
