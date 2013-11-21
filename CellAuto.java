import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class CellAuto extends JPanel {

    protected static final int SQUARE_SIZE = 5;
    protected static final int INSET = 10;

    protected int width, height;
    int[][] cells;
    int[] rule;
    protected int timestep;
    protected Game game; 

    public CellAuto(int w, int h, Game g) {
	super(true);
	setBackground(Color.white);
	setForeground(Color.black);
	
	width = w;
	height = h;
	cells = new int[w][h];
	Game.init(cells);
	timestep = 0;
	rule = r;
	game = g;

	JFrame f = new JFrame("Cellular Automata");
	f.setSize(new Dimension(2*INSET + SQUARE_SIZE * (w+1), 2*INSET + SQUARE_SIZE*(h+1)));
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
	f.getContentPane().add(this, BorderLayout.CENTER);
	f.setVisible(true);
	f.setResizable(false);
	repaint(0,0, getWidth(), getHeight());
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	drawGrid(g);
	drawCells(g);
    }

    public void drawCells(Graphics g) {
	for(int i=0; i<height; i++) {
	    for(int j=0; j<width; j++) {
		if(cells[i][j] == -1) {
		    /*
		    g.setColor(Color.white);
		    g.fillRect(INSET + SQUARE_SIZE*j,
			       INSET + SQUARE_SIZE*i,
			       SQUARE_SIZE,
			       SQUARE_SIZE
			       );
		    */
		}
		if(cells[i][j] == 1) {
		    g.setColor(Color.black);
		    g.fillRect(INSET + SQUARE_SIZE*j,
			       INSET + SQUARE_SIZE*i,
			       SQUARE_SIZE,
			       SQUARE_SIZE
			       );
		}
		if(cells[i][j] ==0 ) {
		}
	    }
	}

    }

    public void drawGrid(Graphics g) {
	g.setColor(Color.black);
	for(int i = 0; i <= width; i++) {
	    g.drawLine(INSET + SQUARE_SIZE *i, 
		       INSET, 
		       INSET + i*SQUARE_SIZE,
		       INSET + height*SQUARE_SIZE);
	}
	for(int j = 0; j <= height; j++) {
	    g.drawLine(INSET,
		       INSET + SQUARE_SIZE*j,
		       INSET + width*SQUARE_SIZE,
		       INSET + j*SQUARE_SIZE);
	}

    }

    public void play(int sleeptimemillis) {
	while(true) {
	    Game.update(cells);
	    repaint(0,0, getWidth(), getHeight());
	    try {
		Thread.sleep(sleeptimemillis);
	    } catch (Exception e) {}
	}
    }

    public void update(int[][] board) {
	timestep++;
	int index;
	if(timestep < height) {
	    for(int i=0; i < width; i++) {
		index = (cells[timestep - 1][(i-1 + width)%width] + 1)/2 +
		    (cells[timestep - 1][i] + 1) +
		    (cells[timestep - 1][(i+1)%width] + 1) * 2;
		cells[timestep][i] = rule[index];
	    }
	}
    }
    

    public static void main(String args[]) {
	int size = 150;
	Random randy = new Random();
	int[] init = new int[size];
	int[] init2 = new int[size];
	init2[size/2] = 1;
	for(int i=0; i<size; i++) {
	    if(randy.nextBoolean()) {
		init[i] = 1;
	    } else {
		init[i] = -1;
	    }
	}
	int[] rule = {-1, 1, 1, 1, 1,-1, -1, -1};
	CellAuto c = new CellAuto(size, size, rule, init);
	c.play(100);
    }

}