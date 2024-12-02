package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs; // an instance of a class that implements the MouseListener interface
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private int frames=0;
    private long lastCheck=0;
    private Random random = new Random();
    private Color color = new Color(15, 255, 255);

    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this); // Initialize mouse input handling
        addKeyListener(new KeyboardInputs(this));

        // This is a method from a JComponent (like JPanel or JFrame) that registers
        // a MouseListener with the component so that the component can handle mouse events.
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
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
        updateRectangle();

        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 100, 100);
    }

    private void updateRectangle() {
        xDelta += xDir;
//        if xDelta goes over the bound=400
        if (xDelta > 400 || xDelta < 0){
//            it reverses the direction of x direction, 1*(-1)=-1
            xDir *= 1;
            color = getRndColor();
        }

        if (yDelta > 400 || yDelta < 0){
            yDir *= 1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int b = random.nextInt(255);
        int g = random.nextInt(255);
        return new Color(r, b, g);
    }
}
