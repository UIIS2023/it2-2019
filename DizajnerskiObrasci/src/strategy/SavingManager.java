package strategy;


public class SavingManager implements Save {

	private Save save;

	public SavingManager(Save save) {
		this.save = save;
	}

	public void setSave(Save save) {
		this.save = save;
	}

	@Override
	public void save() {
		save.save();
	}
}
