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
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class DlgModifyPoint extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JButton btnModify;
	private JButton btnCancel;
	private JButton btnEdgeColor;
	private JLabel lblModifyPoint;

	private boolean isConfirm;

	private Color edgeColor = new Color(0, 0, 0);

	public static void main(String[] args) {
		try {
			DlgModifyPoint dialog = new DlgModifyPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgModifyPoint() {
		setTitle("Modify Point");
		setBounds(100, 100, 400, 350);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBackground(new Color(255, 228, 181));
		pnlCenter.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		JLabel lblXCoordinate = new JLabel("X coordinate:");
		lblXCoordinate.setForeground(new Color(102, 0, 0));
		lblXCoordinate.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lblYCoordinate = new JLabel("Y coordinate:");
		lblYCoordinate.setForeground(new Color(102, 0, 0));
		lblYCoordinate.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtX = new JTextField();
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.setColumns(10);
		btnEdgeColor = new JButton("Choose color");
		btnEdgeColor.setBackground(new Color(255, 160, 122));
		btnEdgeColor.setForeground(new Color(102, 0, 0));
		btnEdgeColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeColor = JColorChooser.showDialog(null, "Choose Point color", edgeColor);
				if (edgeColor != null) {
					btnEdgeColor.setBackground(edgeColor);
				}
			}
		});

		lblModifyPoint = new JLabel("Modify Point");
		lblModifyPoint.setForeground(new Color(102, 0, 0));
		lblModifyPoint.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addComponent(lblModifyPoint)
					.addGap(136))
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblYCoordinate)
						.addComponent(lblXCoordinate))
					.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(58))
				.addGroup(Alignment.LEADING, gl_pnlCenter.createSequentialGroup()
					.addGap(95)
					.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(113, Short.MAX_VALUE))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(42)
					.addComponent(lblModifyPoint)
					.addGap(39)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXCoordinate)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYCoordinate)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(btnEdgeColor)
					.addContainerGap(39, Short.MAX_VALUE))
		);
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			pnlSouth.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			pnlSouth.setBackground(new Color(255, 228, 181));
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnModify = new JButton("Modify");
				btnModify.setForeground(new Color(255, 255, 255));
				btnModify.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnModify.setBackground(new Color(102, 0, 0));
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (txtX.getText().trim().equals("") || txtY.getText().trim().equals("")) {
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Fields are empty!", "ERROR",
										JOptionPane.ERROR_MESSAGE, null);
								isConfirm = false;
								return;
							} else {
								isConfirm = true;
								setVisible(false);
							}
							validation(txtX.getText(), txtY.getText());
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Please insert numbers!", "ERROR",
									JOptionPane.ERROR_MESSAGE, null);
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
						.addGap(103)
						.addComponent(btnModify)
						.addGap(18)
						.addComponent(btnCancel)
						.addGap(143))
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

	public void validation(String x, String y) {
		String exp2 = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if (x.matches("") || y.matches("")) {

		} else if (!x.matches(exp2) || !y.matches(exp2)) {
			throw new NumberFormatException();
		}
	}

	public boolean isConfirm() {
		return isConfirm;
	}


	public JTextField getTxtX() {
		return txtX;
	}


	public JTextField getTxtY() {
		return txtY;
	}


	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

}
