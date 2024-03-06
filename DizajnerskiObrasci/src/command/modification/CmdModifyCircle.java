package command.modification;

import command.Command;
import geometry.Circle;

public class CmdModifyCircle implements Command {

	private Circle oldState;
	private Circle newState;
	private Circle original = new Circle();
	
	public CmdModifyCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		original = oldState.clone();
		try {
			oldState.setRadius(newState.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldState.setCenter(newState.getCenter().clone());
		oldState.setInnerColor(newState.getInnerColor());
		oldState.setColor(newState.getColor());
		
	}

	@Override
	public void unexecute() {
		try {
			oldState.setRadius(original.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldState.setCenter(original.getCenter());
		oldState.setInnerColor(original.getInnerColor());
		oldState.setColor(original.getColor());
		
	}

	@Override
	public String toString() {
		return "Modified:" + this.original + "-->" + this.newState + "\n";
	}
}
