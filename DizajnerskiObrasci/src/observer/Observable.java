package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Observable {
	
	private boolean selectBtnActivated;
	private boolean deleteBtnActivated;
	private boolean modifyBtnActivated;
	private boolean toFrontBtnActivated;
	private boolean toBackBtnActivated;
	private boolean bringToFrontBtnActivated;
	private boolean bringToBackBtnActivated;
	
	private PropertyChangeSupport propertyChangeSupport;
	
	public Observable() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	
	public void addListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}
	
	public void removeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}
	
	public void setDeleteBtnActivated(boolean deleteBtnActivated) {
		propertyChangeSupport.firePropertyChange("deleteBtn", this.deleteBtnActivated, deleteBtnActivated);
		this.deleteBtnActivated = deleteBtnActivated;
	}

	public void setModifyBtnActivated(boolean modifyBtnActivated) {
		propertyChangeSupport.firePropertyChange("modifyBtn", this.modifyBtnActivated, modifyBtnActivated);
		this.modifyBtnActivated = modifyBtnActivated;
	}

	public void setSelectBtnActivated(boolean selectBtnActivated) {
		propertyChangeSupport.firePropertyChange("selectBtn", this.selectBtnActivated, selectBtnActivated);
		this.selectBtnActivated = selectBtnActivated;
	}

	public void setBringToFrontBtnActivated(boolean bringToFrontBtnActivated) {
		propertyChangeSupport.firePropertyChange("btnBringToFront", this.bringToFrontBtnActivated,
				bringToFrontBtnActivated);
		this.bringToFrontBtnActivated = bringToFrontBtnActivated;
	}

	public void setBringToBackBtnActivated(boolean bringToBackBtnActivated) {
		propertyChangeSupport.firePropertyChange("btnBringToBack", this.bringToBackBtnActivated,
				bringToBackBtnActivated);
		this.bringToBackBtnActivated = bringToBackBtnActivated;
	}

	public void setToFrontBtnActivated(boolean toFrontBtnActivated) {
		propertyChangeSupport.firePropertyChange("btnToFront", this.toFrontBtnActivated, toFrontBtnActivated);
		this.toFrontBtnActivated = toFrontBtnActivated;
	}

	public void setToBackBtnActivated(boolean toBackBtnActivated) {
		propertyChangeSupport.firePropertyChange("btnToBack", this.toBackBtnActivated, toBackBtnActivated);
		this.toBackBtnActivated = toBackBtnActivated;
	}
}
