
import java.swing.*;
import java.awt.*;
import java.awt.event.*;


public class WorldMap {


    static protected WorldMapImpl map = null;
    
    static public void createWorldMap(int x, int y) {
	if(!(map == null)) {
	    System.out.println("WorldMap already created");
	    System.exit(0);
	} else {
	    map = new WorldMapImpl(x,y);
	    try { Thread.sleep(100); } catch (Exception e) {}
	}
    }


    static public void displaySquare(Position pos, String color) {
	if(map == null) {
	    System.out.println("WorldMap not created yet.");
	    System.exit(0);
	}
	map.displaySquareInst(pos, color);
    }

    static public void paus(int millis) {
	try{ Thread.sleep(millis); } catch(Exception e) {}
    }


    static class WorldMapImpl extends JPanel {

	private static final long serialVersionUID = OL;

	protected static final int SQUARE_SIZE = 22;
	protected static final int PENT_SIZE = 9;
	protected static final int INSET = 10;
	

	protected int width, height;
	
	protected WorldMapImpl(int w, int h) {
	    super(true);
	    setBackground(Color.white);
	    setForeground(Color.black);
	    
	    width = w;
	    height = h;
	    JFrame f = new JFrame("Cellular Automata");
	    f.setSize(new Dimension(2 * INSET + SQUARE_SIZE * (w+1), 2 * INSET + SQUARE_SIZE + (h+1)));
	    f.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
			System.exit(0);
		    }
		});
	    f.getContentPane().add(this, BorderLayout.CENTER);
	    f.setVisible(true);
	    f.setResizable(false);
	    repaint(0,0,getWidth(), getHeight());
	}

	final Object sem = new Object();

	protected void displaySquareInst(Position pos, String color) {

	}

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Shape rect = g.getClip();
	    Rectangle bounds = rect.getBounds();
	    int minX = Math.max(0, (int) ((bounds.getX() - INSET)/SQUARE_SIZE));
	    int minY = Math.max(0, (int) ((bounds.getY() - INSET)/SQUARE_SIZE));
	    int maxX = Math.min(width -1, (int)((bounds.getX() + bounds.getWidth()-INSET)/SQUARE_SIZE));
	    int maxY = Math.min(height-1, (int)((bounds.getY() + bounds.getHeight() - INSET)/ SQUARE_SIZE));

	    if(minX != maxX || minY != maxY) drawGrid(g);
	    
	    for(int i = minX; i <= MaxX; i++) {
		for(int j = minY; j<= maxY; j++) {
		    drawSquare(g, i, j);
		}
	    }
	    
	    synchronized(sem) {
		sem.notify();
	    }
	}
	
	protected void drawGrid(Graphics g) {
	    g.setColor(Color.black);
	    for (int i = 0; i <= width; i++) {
		g.drawLine(INSET + i * SQUARE_SIZE, 
			   INSET, 
			   INSET + i * SQUARE_SIZE, 
			   INSET + height * SQUARE_SIZE);
	    }
	    for (int i = 0; i <= height; i++) {
		g.drawLine(INSET, 
			   INSET + i * SQUARE_SIZE, 
			   INSET + width * SQUARE_SIZE, 
			   INSET + i * SQUARE_SIZE);
	    }
	}
    }
}