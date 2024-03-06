package command.modification;

import adapter.HexagonAdapter;
import command.Command;

public class CmdModifyHexagon implements Command{
	
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original;
	
	public CmdModifyHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.moveBy(newState.getX(), newState.getY());
		oldState.setHexagonBorderColor(newState.getHexagonBorderColor());
		oldState.setHexagonInnerColor(newState.getHexagonInnerColor());
		oldState.setHexagonRadius(newState.getHexagonRadius());
	}

	@Override
	public void unexecute() {
		oldState.moveBy(original.getX(), newState.getY());
		oldState.setColor(original.getHexagonBorderColor());
		oldState.setInnerColor(original.getHexagonInnerColor());
		oldState.setHexagonRadius(original.getHexagonRadius());
		
	}

	@Override
	public String toString() {
		return "Modified:" + this.original+ "-->"+ this.newState + "\n";
	}
}
