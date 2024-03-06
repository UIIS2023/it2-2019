package command.modification;

import command.Command;
import geometry.Point;

public class CmdModifyPoint implements Command {

	private Point oldState;
	private Point newState;
	private Point original;

	public CmdModifyPoint(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.moveBy(newState.getX(), newState.getY());
		oldState.setColor(newState.getColor());
	}

	@Override
	public void unexecute() {
		oldState.moveBy(original.getX(), original.getY());
		oldState.setColor(original.getColor());
	}

	@Override
	public String toString() {
		return "Modified:" + original.toString() + "-->" + newState.toString()+"\n";
	}
}
