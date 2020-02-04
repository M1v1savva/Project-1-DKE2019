package build;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

// This class takes care of all the graphics to display a certain state
public class UI extends JPanel {
    private int[][] state;
    private int size;

    // Constructor: sets everything up
    public UI(int y, int x, int _size) throws FileNotFoundException, UnsupportedEncodingException {
        size = _size;
        
        state = new int[y][x];
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                state[i][j] = -1;
            }
        }
    }

    // Paint function, called by the system if required for a new frame, uses the state stored by the UI class
    public void paintComponent(Graphics g) {
        Graphics2D localGraphics2D = (Graphics2D) g;

        localGraphics2D.setColor(Color.LIGHT_GRAY);
        localGraphics2D.fill(getVisibleRect());

        // draw lines
        localGraphics2D.setColor(Color.GRAY);
        for (int i = 0; i <= state.length; i++) {
            localGraphics2D.drawLine(0, i * size, state[0].length * size, i * size);
        }
        for (int i = 0; i <= state[0].length; i++) {
            localGraphics2D.drawLine(i * size, 0, i * size, state.length * size);
        }

        // draw blocks
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                localGraphics2D.setColor(Home.GetColorOfID(state[i][j]));
                localGraphics2D.fill(new Rectangle2D.Double(j * size + 1, i * size + 1, size - 1, size - 1));
            }
        }
    }

    // This function should be called to update the displayed state (Makes a copy)
    public void setState(int[][] _state) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                state[i][j] = _state[i][j];
            }
        }

        // Tells the system a frame update is required
        repaint();
        setVisible(true);
    }
}