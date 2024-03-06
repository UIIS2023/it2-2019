package mvc;

import javax.swing.JFrame;

public class DrawingApp {

	public static void main(String[] args) {

		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model);
		DrawingController contoller = new DrawingController(model,frame);
		frame.setController(contoller);
		frame.setTitle("IT2/2019 Lukac Eva");
		
		
		frame.setSize(1100, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

}
