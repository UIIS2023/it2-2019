package mvc;

import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;
import java.awt.Graphics;

public class DrawingView extends JPanel {

	private static final long serialVersionUID = 1L;

	public DrawingView() {
	}

	private DrawingModel model = new DrawingModel();
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
 