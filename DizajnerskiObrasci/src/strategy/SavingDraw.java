package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import geometry.Shape;

public class SavingDraw implements Save {

	private ArrayList<Shape> shapes;

	public SavingDraw(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	@Override
	public void save() {

		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("ser file (.ser)", "ser"));
		chooser.setDialogTitle("Save drawing");

		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

			File drawing = new File(chooser.getSelectedFile().getAbsolutePath() + ".ser");

			if (drawing.exists()) {
				JOptionPane.showMessageDialog(null, "File with that name already exists! Try again!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				FileOutputStream fileOutputStream;
				ObjectOutputStream objectOutputStream;
				try {
					fileOutputStream = new FileOutputStream(drawing);
					objectOutputStream = new ObjectOutputStream(fileOutputStream);
					objectOutputStream.writeObject(shapes);
					objectOutputStream.close();
					fileOutputStream.close();

					JOptionPane.showMessageDialog(null, "Successfully Saved!", "Message", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error while saving the draw!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
