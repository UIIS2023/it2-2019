package mvc;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.border.BevelBorder;

public class DrawingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	private Color color = Color.BLACK;
	
	private JToggleButton tglBtnPoint, tglBtnLine, tglBtnRectangle, tglBtnDonut, tglBtnCircle, tglBtnHexagon, tglBtnSelect;
	
	private JButton btnModify, btnDelete, btnEdgeColor, btnInnerColor, btnToBack, btnToFront, btnBringToBack, btnBringToFront, btnLoadNext;
	
	private JButton btnUndo, btnRedo;
	
	private ButtonGroup groupShapes = new ButtonGroup();
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmOpenLog;
	private JMenuItem mntmOpenPainting;
	private JMenuItem mntmSaveLog;
	private JMenuItem mntmSavePainting;

	private JList<String> logList;
	private DefaultListModel<String> dlm = new DefaultListModel<String>(); 
	
	public DrawingFrame () {
		getContentPane().setBackground(new Color(255, 228, 181));
		setBounds(100,100,1100,750);
		
		JPanel pnlShapes = new JPanel();
		pnlShapes.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlShapes.setToolTipText("");
		pnlShapes.setBackground(new Color(255, 160, 122));
		
		JPanel pnlLoadNext = new JPanel();
		pnlLoadNext.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlLoadNext.setBackground(new Color(255, 160, 122));
		
		JPanel pnlMain = new JPanel();
		
		JPanel pnlLog = new JPanel();
		
		JPanel pnlPositions = new JPanel();
		pnlPositions.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlPositions.setBackground(new Color(255, 160, 122));
		
		JPanel pnlUndoRedo = new JPanel();
		pnlUndoRedo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlUndoRedo.setBackground(new Color(255, 160, 122));
		
		btnUndo = new JButton("Undo");
		btnUndo.setForeground(new Color(255, 255, 255));
		btnUndo.setBackground(new Color(153, 0, 0));
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		
		btnRedo = new JButton("Redo");
		btnRedo.setForeground(new Color(255, 255, 255));
		btnRedo.setBackground(new Color(153, 0, 0));
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		GroupLayout gl_pnlUndoRedo = new GroupLayout(pnlUndoRedo);
		gl_pnlUndoRedo.setHorizontalGroup(
			gl_pnlUndoRedo.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlUndoRedo.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnUndo, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		gl_pnlUndoRedo.setVerticalGroup(
			gl_pnlUndoRedo.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlUndoRedo.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_pnlUndoRedo.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUndo)
						.addComponent(btnRedo))
					.addGap(20))
		);
		pnlUndoRedo.setLayout(gl_pnlUndoRedo);
		
		btnToBack = new JButton("To Back");
		btnToBack.setForeground(new Color(255, 255, 255));
		btnToBack.setBackground(new Color(102, 0, 0));
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		
		btnToFront = new JButton("To Front");
		btnToFront.setForeground(new Color(255, 255, 255));
		btnToFront.setBackground(new Color(102, 0, 0));
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		
		btnBringToBack = new JButton("Bring to back");
		btnBringToBack.setForeground(new Color(255, 255, 255));
		btnBringToBack.setBackground(new Color(102, 0, 0));
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		
		btnBringToFront = new JButton("Bring to Front");
		btnBringToFront.setForeground(new Color(255, 255, 255));
		btnBringToFront.setBackground(new Color(102, 0, 0));
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		GroupLayout gl_pnlPositions = new GroupLayout(pnlPositions);
		gl_pnlPositions.setHorizontalGroup(
			gl_pnlPositions.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlPositions.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBringToBack, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBringToFront, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(57))
		);
		gl_pnlPositions.setVerticalGroup(
			gl_pnlPositions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPositions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlPositions.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToBack)
						.addComponent(btnToFront)
						.addComponent(btnBringToBack)
						.addComponent(btnBringToFront))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		pnlPositions.setLayout(gl_pnlPositions);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_pnlLog = new GroupLayout(pnlLog);
		gl_pnlLog.setHorizontalGroup(
			gl_pnlLog.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLog.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlLog.setVerticalGroup(
			gl_pnlLog.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLog.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		pnlLog.setLayout(gl_pnlLog);
		
		logList = new JList<String>();
		scrollPane.setViewportView(logList);
		logList.setModel(dlm);
		
		btnLoadNext = new JButton("Load Next");
		btnLoadNext.setForeground(new Color(255, 255, 255));
		btnLoadNext.setBackground(new Color(153, 0, 0));
		btnLoadNext.setEnabled(false);
		btnLoadNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadNext();
			}
		});
		
		GroupLayout gl_pnlLoadNext = new GroupLayout(pnlLoadNext);
		gl_pnlLoadNext.setHorizontalGroup(
			gl_pnlLoadNext.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLoadNext.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLoadNext, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlLoadNext.setVerticalGroup(
			gl_pnlLoadNext.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLoadNext.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLoadNext)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		pnlLoadNext.setLayout(gl_pnlLoadNext);
		
		tglBtnPoint = new JToggleButton("Point");
		tglBtnPoint.setForeground(new Color(255, 255, 255));
		tglBtnPoint.setBackground(new Color(153, 0, 0));
		
		tglBtnLine = new JToggleButton("Line");
		tglBtnLine.setForeground(new Color(255, 255, 255));
		tglBtnLine.setBackground(new Color(153, 0, 0));
		
		tglBtnRectangle = new JToggleButton("Rectangle");
		tglBtnRectangle.setBackground(new Color(153, 0, 0));
		tglBtnRectangle.setForeground(new Color(255, 255, 255));
		
		tglBtnCircle = new JToggleButton("Circle");
		tglBtnCircle.setForeground(new Color(255, 255, 255));
		tglBtnCircle.setBackground(new Color(153, 0, 0));
		
		tglBtnDonut = new JToggleButton("Donut");
		tglBtnDonut.setForeground(new Color(255, 255, 255));
		tglBtnDonut.setBackground(new Color(153, 0, 0));
		
		tglBtnHexagon = new JToggleButton("Hexagon");
		tglBtnHexagon.setForeground(new Color(255, 255, 255));
		tglBtnHexagon.setBackground(new Color(153, 0, 0));
		
		groupShapes.add(tglBtnPoint);
		groupShapes.add(tglBtnLine);
		groupShapes.add(tglBtnCircle);
		groupShapes.add(tglBtnRectangle);
		groupShapes.add(tglBtnDonut);
		groupShapes.add(tglBtnHexagon);
		
		GroupLayout gl_pnlShapes = new GroupLayout(pnlShapes);
		gl_pnlShapes.setHorizontalGroup(
			gl_pnlShapes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlShapes.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.TRAILING)
						.addComponent(tglBtnCircle, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglBtnPoint, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.LEADING)
						.addComponent(tglBtnLine, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglBtnDonut, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.LEADING)
						.addComponent(tglBtnHexagon, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglBtnRectangle, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_pnlShapes.setVerticalGroup(
			gl_pnlShapes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlShapes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglBtnPoint)
						.addComponent(tglBtnLine)
						.addComponent(tglBtnRectangle))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_pnlShapes.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglBtnHexagon)
						.addComponent(tglBtnCircle)
						.addComponent(tglBtnDonut))
					.addContainerGap())
		);
		pnlShapes.setLayout(gl_pnlShapes);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlActions.setBackground(new Color(255, 160, 122));
		
		tglBtnSelect = new JToggleButton("Select");
		tglBtnSelect.setForeground(new Color(255, 255, 255));
		tglBtnSelect.setBackground(new Color(102, 0, 0));
		tglBtnSelect.setEnabled(false);
		
		btnModify = new JButton("Modify");
		btnModify.setForeground(new Color(255, 255, 255));
		btnModify.setBackground(new Color(102, 0, 0));
		btnModify.setEnabled(false);
		
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(102, 0, 0));
		btnDelete.setEnabled(false);
		GroupLayout gl_pnlActions = new GroupLayout(pnlActions);
		gl_pnlActions.setHorizontalGroup(
			gl_pnlActions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlActions.createSequentialGroup()
					.addGap(19)
					.addComponent(tglBtnSelect, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		gl_pnlActions.setVerticalGroup(
			gl_pnlActions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlActions.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_pnlActions.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglBtnSelect)
						.addComponent(btnDelete)
						.addComponent(btnModify))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		pnlActions.setLayout(gl_pnlActions);
		groupShapes.add(tglBtnSelect);
		
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.updateShape();
					}
				});
				
						btnDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.deleteShapes();
							}
						});
		
		JPanel pnlColors = new JPanel();
		pnlColors.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlColors.setBackground(new Color(255, 160, 122));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlMain, GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlLog, GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(pnlLoadNext, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(pnlUndoRedo, 0, 0, Short.MAX_VALUE))
										.addComponent(pnlShapes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(pnlActions, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(pnlColors, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
										.addComponent(pnlPositions, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE))))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlActions, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
						.addComponent(pnlColors, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
						.addComponent(pnlShapes, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlLoadNext, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(pnlPositions, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlUndoRedo, 0, 0, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlLog, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		
		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.setForeground(new Color(255, 255, 255));
		btnInnerColor.setBackground(new Color(102, 0, 0));
		
		btnEdgeColor = new JButton("Edge Color");
		btnEdgeColor.setForeground(new Color(255, 255, 255));
		btnEdgeColor.setBackground(new Color(102, 0, 0));
		GroupLayout gl_pnlColors = new GroupLayout(pnlColors);
		gl_pnlColors.setHorizontalGroup(
			gl_pnlColors.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlColors.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEdgeColor, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlColors.setVerticalGroup(
			gl_pnlColors.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlColors.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_pnlColors.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInnerColor)
						.addComponent(btnEdgeColor))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		pnlColors.setLayout(gl_pnlColors);
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = controller.edgeColor();
				if (color != null) {
					btnEdgeColor.setBackground(color);
				}
			}
		});
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = controller.innerColor();
				if (color != null) {
					btnInnerColor.setBackground(color);
				}
			}
		});
		getContentPane().setLayout(groupLayout);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnMenu = new JMenu("File");
		menuBar.add(mnMenu);
		
		mntmOpenLog = new JMenuItem("Open Log");
		mntmOpenLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openLog();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		mnMenu.add(mntmOpenLog);
		
		mntmOpenPainting = new JMenuItem("Open Drawing");
		mntmOpenPainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openDrawing();
			}
		});
		mnMenu.add(mntmOpenPainting);
		
		mntmSaveLog = new JMenuItem("Save Log");
		mntmSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
		mnMenu.add(mntmSaveLog);
		
		mntmSavePainting = new JMenuItem("Save Drawing");
		mntmSavePainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDrawing();
			}
		});
		mnMenu.add(mntmSavePainting);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglBtnPoint.isSelected()) controller.addPoint(e);
				else if(tglBtnLine.isSelected()) controller.addLine(e);
				else if(tglBtnRectangle.isSelected()) controller.addRectangle(e);
				else if(tglBtnCircle.isSelected()) controller.addCircle(e);
				else if(tglBtnDonut.isSelected()) controller.addDonut(e);
				else if(tglBtnHexagon.isSelected()) controller.addHexagon(e);
				else if(tglBtnSelect.isSelected()) controller.selectShape(e);
			}
		});

		pnlMain.add(view, GroupLayout.DEFAULT_SIZE);
		view.setBackground(Color.WHITE);
		view.setPreferredSize(new Dimension(1060, 900));
	}
	
	public void addToLogList(String string)
	{
		this.dlm.addElement(string);
	}


	public DrawingView getView() {
		return view;
	}


	public void setController(DrawingController controller) {
		this.controller = controller;
	}


	public JToggleButton getTglBtnRectangle() {
		return tglBtnRectangle;
	}


	public void setTglBtnRectangle(JToggleButton tglBtnRectangle) {
		this.tglBtnRectangle = tglBtnRectangle;
	}


	public JToggleButton getTglBtnSelect() {
		return tglBtnSelect;
	}


	public void setTglBtnSelect(JToggleButton tglBtnSelect) {
		this.tglBtnSelect = tglBtnSelect;
	}


	public JToggleButton getTglBtnPoint() {
		return tglBtnPoint;
	}


	public JToggleButton getTglBtnLine() {
		return tglBtnLine;
	}


	public JToggleButton getTglBtnDonut() {
		return tglBtnDonut;
	}


	public JToggleButton getTglBtnCircle() {
		return tglBtnCircle;
	}


	public JToggleButton getTglBtnHexagon() {
		return tglBtnHexagon;
	}



	public JButton getBtnToBack() {
		return btnToBack;
	}


	public JButton getBtnToFront() {
		return btnToFront;
	}


	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}


	public JButton getBtnUndo() {
		return btnUndo;
	}


	public JButton getBtnRedo() {
		return btnRedo;
	}


	public JButton getBtnModify() {
		return btnModify;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}


	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}


	public JButton getBtnLoadNext() {
		return btnLoadNext;
	}


	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}


	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}


	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}


	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	public void setDlm(DefaultListModel<String> dlm) {
		this.dlm = dlm;
	}
}
