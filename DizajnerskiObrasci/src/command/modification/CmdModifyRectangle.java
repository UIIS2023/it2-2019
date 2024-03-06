package command.modification;

import command.Command;
import geometry.Rectangle;

public class CmdModifyRectangle implements Command{
	
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original = new Rectangle();
	
	public CmdModifyRectangle(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.setUpperLeftPoint(newState.getUpperLeftPoint().clone());
		oldState.setWidth(newState.getWidth());
		oldState.setHeight(newState.getHeight());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		oldState.setUpperLeftPoint(original.getUpperLeftPoint());
		oldState.setWidth(original.getWidth());
		oldState.setHeight(original.getHeight());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
		
	}

	@Override
	public String toString() {
		return "Modified:" + this.original + " --> "  + this.newState + "\n";
	}
}
