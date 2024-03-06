package command.modification;

import command.Command;
import geometry.Line;

public class CmdModifyLine implements Command {

	private Line oldState;
	private Line newState;
	private Line original = new Line();
	
	public CmdModifyLine(Line oldState, Line newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		original = oldState.clone();
		oldState.setStartPoint(newState.getStartPoint().clone());
		oldState.setEndPoint(newState.getEndPoint().clone());
		oldState.setColor(newState.getColor());
		
	}

	@Override
	public void unexecute() {
		oldState.setStartPoint(original.getStartPoint());
		oldState.setEndPoint(original.getEndPoint());
		oldState.setColor(original.getColor());
		
	}

	@Override
	public String toString() {
		return "Modified:" + this.original + "-->" + this.newState + "\n";
	}
}
