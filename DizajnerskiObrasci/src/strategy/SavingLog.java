package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SavingLog implements Save {

	private DefaultListModel<String> dlm;

	public SavingLog(DefaultListModel<String> defaultListModel) {
		this.dlm = defaultListModel;
	}

	@Override
	public void save() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.enableInputMethods(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setDialogTitle("Save log");

		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File log = new File(chooser.getSelectedFile().getAbsolutePath() + ".log");

			if (log.exists()) {
				JOptionPane.showMessageDialog(null, "File with that name already exists! Try again!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(log.getAbsolutePath())));
					for (int i = 0; i < dlm.size(); i++) {
						bufferedWriter.write(dlm.get(i));
					}
					bufferedWriter.close();
					JOptionPane.showMessageDialog(null, "Successfully Saved!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error while saving the file!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
