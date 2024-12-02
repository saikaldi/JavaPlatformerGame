package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs; // an instance of a class that implements the MouseListener interface
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;


    public GamePanel() {
        mouseInputs = new MouseInputs(this); // Initialize mouse input handling

        importImg();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        // This is a method from a JComponent (like JPanel or JFrame) that registers
        // a MouseListener with the component so that the component can handle mouse events.
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void importImg(){
        InputStream is = getClass().getResourceAsStream("/player.png");
        try {
            img = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1200, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    public void changeXDelta(int value){
        this.xDelta += value;
    }

    public void changeYDelta(int value){
        this.yDelta += value;
    }

    public void setRect(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }




    public void paint(Graphics g) {
//        actually, it is calling JComponent's paintComponent
//        JComponent is the superclass of JPanel
//        public class JPanel extends JComponent implements Accessible
        super.paintComponent(g);

        subImg = img.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImg, (int)xDelta, (int)yDelta,128, 80, null);
    }




}
