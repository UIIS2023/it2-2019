package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class Observer implements PropertyChangeListener {

	private DrawingFrame frame;

	public Observer(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "selectBtn":
			frame.getTglBtnSelect().setEnabled((boolean) evt.getNewValue());
			break;
		case "deleteBtn":
			frame.getBtnDelete().setEnabled((boolean) evt.getNewValue());
			break;
		case "modifyBtn":
			frame.getBtnModify().setEnabled((boolean) evt.getNewValue());
			break;
		case "btnToFront":
			frame.getBtnToFront().setEnabled((boolean) evt.getNewValue());
			break;
		case "btnToBack":
			frame.getBtnToBack().setEnabled((boolean) evt.getNewValue());
			break;
		case "btnBringToFront":
			frame.getBtnBringToFront().setEnabled((boolean) evt.getNewValue());
			break;
		case "btnBringToBack":
			frame.getBtnBringToBack().setEnabled((boolean) evt.getNewValue());
			break;
		default:
			break;
		}
	}
}