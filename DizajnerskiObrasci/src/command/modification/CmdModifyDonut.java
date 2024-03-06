package command.modification;

import command.Command;
import geometry.Donut;

public class CmdModifyDonut implements Command {
	
	private Donut oldState;
	private Donut newState;
	private Donut original = new Donut();
	
	public CmdModifyDonut(Donut oldState, Donut newState) {
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
		oldState.setInnerRadius(newState.getInnerRadius());
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
		oldState.setInnerRadius(original.getInnerRadius());
		oldState.setCenter(original.getCenter());
		oldState.setInnerColor(original.getInnerColor());
		oldState.setColor(original.getColor());
	}

	@Override
	public String toString() {
		return "Modified:" + this.original + " --> "+ this.newState + "\n";
	}
}
