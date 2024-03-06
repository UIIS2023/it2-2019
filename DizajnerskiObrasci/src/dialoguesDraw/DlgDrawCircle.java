package dialoguesDraw;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class DlgDrawCircle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JButton btnDraw;
	private JButton btnCancel;
	private JTextField txtRadius;
	private boolean isConfirm;
	
	public static void main(String[] args) {
		try {
			DlgDrawCircle dialog = new DlgDrawCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public DlgDrawCircle() {
		setBounds(100, 100, 430, 260);
		setTitle("Draw Circle");
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 228, 181));
		pnlCenter.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		
		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setForeground(new Color(102, 0, 0));
		lblRadius.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		JLabel lblDrawCircle = new JLabel("Draw Circle");
		lblDrawCircle.setForeground(new Color(102, 0, 0));
		lblDrawCircle.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlCenter.createSequentialGroup()
					.addContainerGap(94, Short.MAX_VALUE)
					.addComponent(lblRadius)
					.addGap(41)
					.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGap(92))
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(148)
					.addComponent(lblDrawCircle)
					.addContainerGap(173, Short.MAX_VALUE))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(24)
					.addComponent(lblDrawCircle)
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(75))
		);
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			pnlSouth.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			pnlSouth.setBackground(new Color(255, 228, 181));
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnDraw = new JButton("Draw");
				btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnDraw.setForeground(new Color(240, 248, 255));
				btnDraw.setBackground(new Color(102, 0, 0));
				btnDraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtRadius.getText().trim().equals("")) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Field is empty! Please insert radius", "Error", JOptionPane.ERROR_MESSAGE, null);
							isConfirm = false;
							return;
						} 
						try {
							validate(txtRadius.getText());
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Please insert only numbers!", "Error", JOptionPane.ERROR_MESSAGE, null);
							isConfirm = false;
							return;
						}
						
						 if(Integer.parseInt(txtRadius.getText()) < 1){
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Radius can't be less than 1!", "Error", JOptionPane.ERROR_MESSAGE, null);
							isConfirm = false;
							return;
						} else {
							isConfirm = true;
							setVisible(false);
							dispose();
						}	
					}
				});
				btnDraw.setActionCommand("OK");
				getRootPane().setDefaultButton(btnDraw);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnCancel.setForeground(new Color(240, 248, 255));
				btnCancel.setBackground(new Color(102, 0, 0));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
			}
			GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
			gl_pnlSouth.setHorizontalGroup(
				gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlSouth.createSequentialGroup()
						.addGap(136)
						.addComponent(btnDraw)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnCancel)
						.addGap(150))
			);
			gl_pnlSouth.setVerticalGroup(
				gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlSouth.createSequentialGroup()
						.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDraw, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCancel))
						.addGap(5))
			);
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}
	
	public void validate(String radius) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
	    if(!radius.matches(supp)){  
	    	throw new NumberFormatException();
	    }
	}
	

	public boolean isConfirm() {
		return isConfirm;
	}

	public void setConfirm(boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}
}


