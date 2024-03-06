package dialoguesDraw;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class DlgDrawRectangle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JButton btnDraw;
	private JButton btnCancel;
	private JLabel lblWidth;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private boolean isConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDrawRectangle dialog = new DlgDrawRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDrawRectangle() {
		setBounds(100, 100, 430, 300);
		setTitle("Draw Rectangle");
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 228, 181));
		pnlCenter.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		{
			lblWidth = new JLabel("Width:");
			lblWidth.setForeground(new Color(102, 0, 0));
			lblWidth.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setForeground(new Color(102, 0, 0));
		lblHeight.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		
		JLabel lblDrawRectangle = new JLabel("Draw Rectangle");
		lblDrawRectangle.setForeground(new Color(102, 0, 0));
		lblDrawRectangle.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWidth)
						.addComponent(lblHeight))
					.addGap(86)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtHeight)
						.addComponent(txtWidth, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
					.addContainerGap(111, Short.MAX_VALUE))
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addContainerGap(144, Short.MAX_VALUE)
					.addComponent(lblDrawRectangle)
					.addGap(139))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(28)
					.addComponent(lblDrawRectangle)
					.addGap(42)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addGap(29)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeight))
					.addContainerGap(60, Short.MAX_VALUE))
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
				btnDraw.setForeground(new Color(255, 255, 255));
				btnDraw.setBackground(new Color(102, 0, 0));
				btnDraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtWidth.getText().trim().equals("")||txtHeight.getText().trim().equals("")) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Field is empty! Please insert width or height", "Error", JOptionPane.ERROR_MESSAGE, null);
							isConfirm = false;
							return;
						} 
						try {
							validate(txtWidth.getText(),txtHeight.getText());
							
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Please insert only numbers!", "Error", JOptionPane.ERROR_MESSAGE, null);
							isConfirm = false;
							return;
						}
						if(Integer.parseInt(txtWidth.getText()) < 1){
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Width can't be less than 1!", "Error", JOptionPane.ERROR_MESSAGE, null);
							isConfirm = false;
							return;
						}
						else if(Integer.parseInt(txtHeight.getText()) < 1){
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Height can't be less than 1!", "Error", JOptionPane.ERROR_MESSAGE, null);
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
				btnCancel.setForeground(new Color(255, 255, 255));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
			}
			GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
			gl_pnlSouth.setHorizontalGroup(
				gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlSouth.createSequentialGroup()
						.addGap(116)
						.addComponent(btnDraw, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addGap(29)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addGap(139))
			);
			gl_pnlSouth.setVerticalGroup(
				gl_pnlSouth.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_pnlSouth.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDraw, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			pnlSouth.setLayout(gl_pnlSouth);
		}
	
}
	public void validate(String width,String height) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
	    if(!width.matches(supp)){  
	    	throw new NumberFormatException();
	    }
	    else if(!height.matches(supp)){
	    	throw new NumberFormatException();
	    }
	}
	

	public boolean isConfirm() {
		return isConfirm;
	}

	public void setConfirm(boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}


}
