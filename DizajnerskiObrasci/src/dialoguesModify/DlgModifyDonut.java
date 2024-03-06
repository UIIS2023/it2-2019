package dialoguesModify;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class DlgModifyDonut extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private JButton btnModify;
	private JButton btnCancel;
	private JButton btnEdgeColor;
	private JButton btnInnerColor;
	private JLabel lblModifyDonut;
	
	private boolean isConfirm;
	
	private Color edgeColor = new Color(0, 0, 0);
	private Color innerColor = new Color(255,255,255);

	
	public static void main(String[] args) {
		try {
			DlgModifyDonut dialog = new DlgModifyDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgModifyDonut() {
		setTitle("Modify Donut");
		setBounds(100, 100, 450, 425);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setForeground(new Color(102, 0, 0));
		pnlCenter.setBackground(new Color(255, 228, 181));
		pnlCenter.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		JLabel lblCenterX = new JLabel("Center X:");
		lblCenterX.setForeground(new Color(102, 0, 0));
		lblCenterX.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lblCenterY = new JLabel("Center Y:");
		lblCenterY.setForeground(new Color(102, 0, 0));
		lblCenterY.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setForeground(new Color(102, 0, 0));
		lblRadius.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lblInnerRadius = new JLabel("Inner radius:");
		lblInnerRadius.setForeground(new Color(102, 0, 0));
		lblInnerRadius.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnEdgeColor = new JButton("Choose edge color");
		btnEdgeColor.setBackground(new Color(255, 160, 122));
		btnEdgeColor.setForeground(new Color(102, 0, 0));
		btnEdgeColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeColor = JColorChooser.showDialog(null, "Choose Donut Egde color", edgeColor);
				if (edgeColor != null) {
					btnEdgeColor.setBackground(edgeColor);
				}
			}
		});
		
		btnInnerColor = new JButton("Choose inner color");
		btnInnerColor.setForeground(new Color(102, 0, 0));
		btnInnerColor.setBackground(new Color(255, 160, 122));
		btnInnerColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose Donut Inner color", innerColor);
				if (innerColor != null) {
					btnInnerColor.setBackground(innerColor);
				}
			}
		});
		
		txtCenterX = new JTextField();
		txtCenterX.setColumns(10);
		
		txtCenterY = new JTextField();
		txtCenterY.setColumns(10);
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		
		lblModifyDonut = new JLabel("Modify Donut");
		lblModifyDonut.setForeground(new Color(102, 0, 0));
		lblModifyDonut.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(66)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCenterY)
						.addComponent(lblCenterX)
						.addComponent(lblRadius)
						.addComponent(lblInnerRadius))
					.addPreferredGap(ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(74))
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(142)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInnerColor)
						.addComponent(btnEdgeColor))
					.addContainerGap(159, Short.MAX_VALUE))
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(154)
					.addComponent(lblModifyDonut, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(144, Short.MAX_VALUE))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(31)
					.addComponent(lblModifyDonut)
					.addGap(36)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCenterX))
					.addGap(18)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCenterY))
					.addGap(18)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRadius))
					.addGap(18)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInnerRadius))
					.addGap(18)
					.addComponent(btnEdgeColor)
					.addGap(18)
					.addComponent(btnInnerColor)
					.addGap(33))
		);
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			pnlSouth.setForeground(new Color(102, 0, 0));
			pnlSouth.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			pnlSouth.setBackground(new Color(255, 228, 181));
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnModify = new JButton("Modify");
				btnModify.setForeground(new Color(255, 255, 255));
				btnModify.setBackground(new Color(102, 0, 0));
				btnModify.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(txtCenterX.getText().trim().equals("") || txtCenterY.getText().trim().equals("") || txtRadius.getText().trim().equals("")|| txtInnerRadius.getText().trim().equals("")){
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Fields are empty!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
								isConfirm = false;
								return;
							}
							else if(Integer.parseInt(txtRadius.getText()) < 1){
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Radius can't be less than 1!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
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
							}
							else{
								isConfirm = true;
								setVisible(false);
							}
							validation(txtCenterX.getText(), txtCenterY.getText(), txtRadius.getText(),txtInnerRadius.getText());
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Please insert numbers!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							isConfirm = false;
							return;
						}
						
						
					}
				});
				btnModify.setActionCommand("OK");
				getRootPane().setDefaultButton(btnModify);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setForeground(new Color(255, 255, 255));
				btnCancel.setBackground(new Color(102, 0, 0));
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
						.addGap(146)
						.addComponent(btnModify)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnCancel)
						.addGap(176))
			);
			gl_pnlSouth.setVerticalGroup(
				gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_pnlSouth.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCancel)
							.addComponent(btnModify))
						.addContainerGap())
			);
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}
	
	
	public void validation(String x, String y, String radius,String innerRadius) {
		String exp2 = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if(x.matches("") || y.matches("") || radius.matches("")|| innerRadius.matches("")) {
			
		}
		else if(!x.matches(exp2) || !y.matches(exp2) || !radius.matches(exp2)|| !innerRadius.matches(exp2)){
        	throw new NumberFormatException();
        }
}

	public JTextField getTxtCenterX() {
		return txtCenterX;
	}

	public JTextField getTxtCenterY() {
		return txtCenterY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public boolean isConfirm() {
		return isConfirm;
	}
}
	
