package command.shapes;

import java.util.ArrayList;
import java.util.Hashtable;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class CmdRemoveShape implements Command {

	private DrawingModel model;
	private Hashtable<Integer,Shape> shapesToDelete;
	
	public CmdRemoveShape(DrawingModel model, ArrayList<Shape> shapesToDelete) {
		this.model = model;
		this.shapesToDelete = new Hashtable<>();
		
		for(Shape shape : shapesToDelete) {
			this.shapesToDelete.put(model.getShapes().indexOf(shape), shape);
		}
	}

	@Override
	public void execute() {
		for(Shape shape : shapesToDelete.values()) {
			shape.setSelected(false);
			model.getShapes().remove(shape);
		}
	}

	@Override
	public void unexecute() {
		for(int key : shapesToDelete.keySet()) {
			Shape shape = shapesToDelete.get(key);
			if(key <= model.getShapes().size()) {
				model.getShapes().add(key,shape);
				shape.setSelected(true);
			} else {
				model.add(shape);
				shape.setSelected(true);
			}
		}
	}
	

	@Override
	public String toString() {
		String removeText = "Removed:";
		for(int key : shapesToDelete.keySet()) {
			Shape shape = shapesToDelete.get(key);
			removeText = removeText + shape.toString() + ",";
		}
		return removeText +"\n";
	}
}
