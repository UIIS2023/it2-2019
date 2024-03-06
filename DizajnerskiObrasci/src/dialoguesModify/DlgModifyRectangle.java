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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class DlgModifyRectangle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JButton btnModify;
	private JButton btnCancel;
	private JTextField txtUpperLeftPointX;
	private JTextField txtUpperLeftPointY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JButton btnEdgeColor, btnInnerColor;
	private JLabel lblModifyRectangle;

	private boolean isConfirm;
	
	private Color edgeColor = new Color(0, 0, 0);
	private Color innerColor = new Color(255,255,255);
	
	
	public static void main(String[] args) {
		try {
			DlgModifyRectangle dialog = new DlgModifyRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public DlgModifyRectangle() {
		setTitle("Modify Rectangle");
		setBounds(100, 100, 430, 450);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 228, 181));
		pnlCenter.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		JLabel lblUpperLeftPointX = new JLabel("Upper Left Point X:");
		lblUpperLeftPointX.setForeground(new Color(102, 0, 0));
		lblUpperLeftPointX.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lblUpperLeftPointY = new JLabel("Upper Left Point Y:");
		lblUpperLeftPointY.setForeground(new Color(102, 0, 0));
		lblUpperLeftPointY.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setForeground(new Color(102, 0, 0));
		lblWidth.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setForeground(new Color(102, 0, 0));
		lblHeight.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtUpperLeftPointX = new JTextField();
		txtUpperLeftPointX.setColumns(10);
		
		txtUpperLeftPointY = new JTextField();
		txtUpperLeftPointY.setColumns(10);
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		
		lblModifyRectangle = new JLabel("Modify Rectangle");
		lblModifyRectangle.setForeground(new Color(102, 0, 0));
		lblModifyRectangle.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		btnEdgeColor = new JButton("Edge Color");
		btnEdgeColor.setBackground(new Color(255, 160, 122));
		btnEdgeColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEdgeColor.setForeground(new Color(102, 0, 0));
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeColor = JColorChooser.showDialog(null, "Choose edge color", edgeColor);
				if (edgeColor != null) {
					btnEdgeColor.setBackground(edgeColor);
				}
			}
		});
		
		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.setBackground(new Color(255, 160, 122));
		btnInnerColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInnerColor.setForeground(new Color(102, 0, 0));
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose inner color", innerColor);
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
							.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCenter.createSequentialGroup()
									.addGap(42)
									.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUpperLeftPointX)
										.addComponent(lblUpperLeftPointY)))
								.addGroup(gl_pnlCenter.createSequentialGroup()
									.addGap(71)
									.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
										.addComponent(lblHeight)
										.addComponent(lblWidth))))
							.addGap(95)
							.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
								.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtUpperLeftPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtUpperLeftPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(119)
							.addComponent(lblModifyRectangle))
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(143)
							.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
								.addComponent(btnInnerColor)
								.addComponent(btnEdgeColor))))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(44)
					.addComponent(lblModifyRectangle)
					.addGap(43)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUpperLeftPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUpperLeftPointX))
					.addGap(18)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUpperLeftPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUpperLeftPointY))
					.addGap(18)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addGap(18)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeight))
					.addGap(31)
					.addComponent(btnEdgeColor)
					.addGap(18)
					.addComponent(btnInnerColor)
					.addGap(29))
		);
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			pnlSouth.setBackground(new Color(255, 228, 181));
			pnlSouth.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnModify = new JButton("Modify");
				btnModify.setForeground(new Color(255, 255, 255));
				btnModify.setBackground(new Color(102, 0, 0));
				btnModify.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(txtUpperLeftPointX.getText().trim().equals("") || txtUpperLeftPointY.getText().trim().equals("") || txtWidth.getText().trim().equals("")|| txtHeight.getText().trim().equals("")){
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Fields are empty!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
								return;
							}else if(Integer.parseInt(txtWidth.getText()) < 1 ){
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Width can't be less than 1!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
								return;
							}else if(Integer.parseInt(txtHeight.getText()) < 1 ){
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Height can't be less than 1!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
								return;
							}else{
								isConfirm = true;
								setVisible(false);
							}
							validation(txtUpperLeftPointX.getText(), txtUpperLeftPointY.getText(), txtWidth.getText(),txtHeight.getText());
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
				btnCancel.setForeground(new Color(255, 255, 255));
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnCancel.setBackground(new Color(102, 0, 0));
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
						.addComponent(btnModify)
						.addGap(18)
						.addComponent(btnCancel)
						.addGap(170))
			);
			gl_pnlSouth.setVerticalGroup(
				gl_pnlSouth.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_pnlSouth.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnModify)
							.addComponent(btnCancel))
						.addContainerGap())
			);
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}
	
	
	public void validation(String x, String y, String width,String height) {
		String exp2 = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if(x.matches("") || y.matches("") || width.matches("")|| height.matches("")) {
			
		}
		else if(!x.matches(exp2) || !y.matches(exp2) || !width.matches(exp2)|| !height.matches(exp2)){
        	throw new NumberFormatException();
        }
	}

	public JTextField getTxtUpperLeftPointX() {
		return txtUpperLeftPointX;
	}

	public JTextField getTxtUpperLeftPointY() {
		return txtUpperLeftPointY;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
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
