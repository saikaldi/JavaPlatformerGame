package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs; // an instance of a class that implements the MouseListener interface
    private int xDelta = 100, yDelta = 100;


    public GamePanel() {
        mouseInputs = new MouseInputs(this); // Initialize mouse input handling
        addKeyListener(new KeyboardInputs(this));

        // This is a method from a JComponent (like JPanel or JFrame) that registers
        // a MouseListener with the component so that the component can handle mouse events.
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value){
        this.xDelta += value;
        repaint();
    }

    public void changeYDelta(int value){
        this.yDelta += value;
        repaint();
    }

    public void setRect(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }


    public void paint(Graphics g) {
//        actually, it is calling JComponent's paintComponent
//        JComponent is the superclass of JPanel
//        public class JPanel extends JComponent implements Accessible
        super.paintComponent(g);
        g.fillRect(xDelta, yDelta, 100, 100);
    }
}
