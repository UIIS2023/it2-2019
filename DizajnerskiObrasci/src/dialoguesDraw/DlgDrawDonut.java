package dialoguesDraw;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DlgDrawDonut extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JButton btnDraw;
	private JButton btnCancel;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private boolean isConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDrawDonut dialog = new DlgDrawDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDrawDonut() {
		setBounds(100, 100, 430, 300);
		setTitle("Draw Donut");
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
		
		JLabel lblInnerRadius = new JLabel("Inner radius:");
		lblInnerRadius.setForeground(new Color(102, 0, 0));
		lblInnerRadius.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		
		JLabel lblDrawDonut = new JLabel("Draw Donut");
		lblDrawDonut.setForeground(new Color(102, 0, 0));
		lblDrawDonut.setBackground(new Color(240, 248, 255));
		lblDrawDonut.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(151)
					.addComponent(lblDrawDonut)
					.addContainerGap(164, Short.MAX_VALUE))
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRadius)
						.addComponent(lblInnerRadius))
					.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addComponent(lblDrawDonut)
							.addGap(59))
						.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblRadius)
							.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(40)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInnerRadius)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(74, Short.MAX_VALUE))
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
				btnDraw.setBackground(new Color(102, 0, 0));
				btnDraw.setForeground(new Color(240, 248, 255));
				btnDraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtRadius.getText().trim().equals("")|| txtInnerRadius.getText().trim().equals("")) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Fields are empty! Please insert radius", "Error", JOptionPane.ERROR_MESSAGE, null);
							isConfirm = false;
							return;
						} 
						try {
							validate(txtRadius.getText(),txtInnerRadius.getText());
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
							}
						 else if(Integer.parseInt(txtInnerRadius.getText()) < 1){
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Inner radius can't be less than 1!", "Error", JOptionPane.ERROR_MESSAGE, null);
								isConfirm = false;
								return;
							}
						 else if(Integer.parseInt(txtInnerRadius.getText())>Integer.parseInt(txtRadius.getText())){
							 getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Inner radius can't be greater than radius!", "Error", JOptionPane.ERROR_MESSAGE, null);
								isConfirm = false;
								return;
						 }
						 else if(Integer.parseInt(txtInnerRadius.getText())== Integer.parseInt(txtRadius.getText())){
							 getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Inner radius can't be the same value as radius!", "Error", JOptionPane.ERROR_MESSAGE, null);
								isConfirm = false;
								return;
						 }else {
								isConfirm = true;
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
				btnCancel.setBackground(new Color(102, 0, 0));
				btnCancel.setForeground(new Color(240, 248, 255));
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
						.addGap(128)
						.addComponent(btnDraw, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addGap(132))
			);
			gl_pnlSouth.setVerticalGroup(
				gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlSouth.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlSouth.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnDraw, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}
	public void validate(String radius,String innerRadius) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
	    if(!radius.matches(supp)){  
	    	throw new NumberFormatException();
	    }
	    else if(!innerRadius.matches(supp)){
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

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}
}
