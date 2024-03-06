package dialoguesModify;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DlgModifyHexagon extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JButton btnModify;
	private JButton btnCancel;
	private JTextField txtCenterHX;
	private JTextField txtCenterHY;
	private JTextField txtRadiusH;
	private JButton btnEdgeColor;
	private JButton btnInnerColor;
	
	private boolean isConfirm;
	
	private Color edgeColor = new Color(0, 0, 0);
	private Color innerColor = new Color(255,255,255);
	
	
	public static void main(String[] args) {
		try {
			DlgModifyHexagon dialog = new DlgModifyHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgModifyHexagon() {
		setBounds(100, 100, 496, 407);
		setTitle("Modify Hexagon");
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 228, 181));
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		
		JLabel lblModifyHexagon = new JLabel("Modify Hexagon");
		lblModifyHexagon.setForeground(new Color(102, 0, 0));
		lblModifyHexagon.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblCenterHX = new JLabel("Center X:");
		lblCenterHX.setForeground(new Color(102, 0, 0));
		lblCenterHX.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblCenterHY = new JLabel("Center Y:");
		lblCenterHY.setForeground(new Color(102, 0, 0));
		lblCenterHY.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblRadiusH = new JLabel("Radius:");
		lblRadiusH.setForeground(new Color(102, 0, 0));
		lblRadiusH.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtCenterHX = new JTextField();
		txtCenterHX.setColumns(10);
		
		txtCenterHY = new JTextField();
		txtCenterHY.setColumns(10);
		
		txtRadiusH = new JTextField();
		txtRadiusH.setColumns(10);
		
		btnEdgeColor = new JButton("Choose edge color");
		btnEdgeColor.setBackground(new Color(255, 160, 122));
		btnEdgeColor.setForeground(new Color(102, 0, 0));
		btnEdgeColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeColor = JColorChooser.showDialog(null, "Choose Hexagon Egde color", edgeColor);
				if (edgeColor != null) {
					btnEdgeColor.setBackground(edgeColor);
				}
			}
		});
		
		btnInnerColor = new JButton("Choose inner color");
		btnInnerColor.setBackground(new Color(255, 160, 122));
		btnInnerColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInnerColor.setForeground(new Color(102, 0, 0));
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose Hexagon Inner color", innerColor);
				if (innerColor != null) {
					btnInnerColor.setBackground(innerColor);
				}
			}
		});
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_pnlCenter.createSequentialGroup()
									.addComponent(lblCenterHY, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtCenterHY, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlCenter.createSequentialGroup()
									.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRadiusH, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCenterHX, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
									.addGap(105)
									.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtCenterHX, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtRadiusH, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(145)
							.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
								.addComponent(lblModifyHexagon, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnInnerColor)
									.addComponent(btnEdgeColor)))))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblModifyHexagon, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCenterHX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCenterHX))
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(18)
							.addComponent(txtCenterHY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(29)
							.addComponent(lblCenterHY)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(24)
							.addComponent(lblRadiusH))
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(18)
							.addComponent(txtRadiusH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(38)
					.addComponent(btnEdgeColor)
					.addGap(18)
					.addComponent(btnInnerColor)
					.addGap(44))
		);
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			pnlSouth.setForeground(new Color(102, 0, 0));
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
							
							 if(txtCenterHX.getText().trim().equals("") || txtCenterHY.getText().trim().equals("") || txtRadiusH.getText().trim().equals("")){
									getToolkit().beep();
									JOptionPane.showMessageDialog(null, "Fields are empty!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
									
									return;
								}else if(Integer.parseInt(txtRadiusH.getText()) < 1){
									getToolkit().beep();
									JOptionPane.showMessageDialog(null, "Radius can't be less than 1!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
									
									return;
								}else{
									isConfirm = true;
									setVisible(false);
								}
							 validation(txtCenterHX.getText(), txtCenterHY.getText(), txtRadiusH.getText());
						
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Please insert numbers!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							
							return;
						}
					}
				});
				btnModify.setActionCommand("OK");
				getRootPane().setDefaultButton(btnModify);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setBackground(new Color(102, 0, 0));
				btnCancel.setForeground(new Color(255, 255, 255));
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
						.addGap(155)
						.addComponent(btnModify)
						.addGap(43)
						.addComponent(btnCancel)
						.addGap(158))
			);
			gl_pnlSouth.setVerticalGroup(
				gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlSouth.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCancel)
							.addComponent(btnModify))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}
	
	public void validation(String x, String y, String radius) {
		String exp2 = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if(x.matches("") || y.matches("") || radius.matches("")) {
			
		}
		else if(!x.matches(exp2) || !y.matches(exp2) || !radius.matches(exp2)){
        	throw new NumberFormatException();
        }
	}

	public JTextField getTxtCenterHX() {
		return txtCenterHX;
	}

	public JTextField getTxtCenterHY() {
		return txtCenterHY;
	}

	public JTextField getTxtRadiusH() {
		return txtRadiusH;
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
