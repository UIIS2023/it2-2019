package mvc;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.Command;
import command.modification.CmdModifyCircle;
import command.modification.CmdModifyDonut;
import command.modification.CmdModifyHexagon;
import command.modification.CmdModifyLine;
import command.modification.CmdModifyPoint;
import command.modification.CmdModifyRectangle;
import command.positions.CmdBringToBack;
import command.positions.CmdBringToFront;
import command.positions.CmdToBack;
import command.positions.CmdToFront;
import command.selection.CmdDeselectShape;
import command.selection.CmdSelectShape;
import command.shapes.CmdAddShape;
import command.shapes.CmdRemoveShape;

import java.awt.Color;

import dialoguesDraw.DlgDrawCircle;
import dialoguesDraw.DlgDrawDonut;
import dialoguesDraw.DlgDrawHexagon;
import dialoguesDraw.DlgDrawRectangle;
import dialoguesModify.DlgModifyCircle;
import dialoguesModify.DlgModifyDonut;
import dialoguesModify.DlgModifyHexagon;
import dialoguesModify.DlgModifyLine;
import dialoguesModify.DlgModifyPoint;
import dialoguesModify.DlgModifyRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import observer.Observable;
import observer.Observer;
import strategy.SavingLog;
import strategy.SavingManager;
import strategy.SavingDraw;

public class DrawingController implements Serializable {

	private static final long serialVersionUID = 1L;
	private DrawingModel model;
	private DrawingFrame frame;

	private Color edgeColor = Color.BLACK;
	private Color innerColor = Color.WHITE;

	private Color chosenEdgeColor;
	private Color chosenInnerColor;

	private Point startPoint, endPoint;

	private Stack<Command> undoRedoStack = new Stack<Command>();
	private int undoRedoCounter = -1;

	private Observable observable = new Observable();
	private Observer observer;

	private BufferedReader bufferedReader;
	private String line;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		startPoint = null;
		observer = new Observer(frame);
		observable.addListener(observer);
	}

	public Color edgeColor() {
		chosenEdgeColor = JColorChooser.showDialog(null, "Choose color", edgeColor);
		if (chosenEdgeColor != null) {
			edgeColor = chosenEdgeColor;
			return edgeColor;
		}
		return chosenEdgeColor;
	}

	public Color innerColor() {
		chosenInnerColor = JColorChooser.showDialog(null, "Choose color", innerColor);
		if (chosenInnerColor != null) {
			innerColor = chosenInnerColor;
			return innerColor;
		}
		return chosenInnerColor;
	}

	public void addPoint(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY(), edgeColor);
		Command cmd = new CmdAddShape(model, point);
		executeCommand(cmd);
	}

	public void addLine(MouseEvent e) {
		if (startPoint == null) {
			startPoint = new Point(e.getX(), e.getY(), edgeColor);
		} else {
			endPoint = new Point(e.getX(), e.getY(), edgeColor);
			Line line = new Line(startPoint, endPoint, edgeColor);
			Command cmd = new CmdAddShape(model, line);
			executeCommand(cmd);
			startPoint = null;
		}
	}

	public void addRectangle(MouseEvent e) {
		DlgDrawRectangle dlgDrawRectangle = new DlgDrawRectangle();
		dlgDrawRectangle.setVisible(true);
		if (dlgDrawRectangle.isConfirm()) {
			Rectangle rectangle = new Rectangle(new Point(e.getX(), e.getY()), Integer.parseInt(dlgDrawRectangle.getTxtWidth().getText()), Integer.parseInt(dlgDrawRectangle.getTxtHeight().getText()), edgeColor, innerColor);
			Command cmd = new CmdAddShape(model, rectangle);
			executeCommand(cmd);
		}
	}

	public void addCircle(MouseEvent e) {
		DlgDrawCircle dlgDrawCircle = new DlgDrawCircle();
		dlgDrawCircle.setVisible(true);
		if (dlgDrawCircle.isConfirm()) {
			Circle circle = new Circle(new Point(e.getX(), e.getY()), Integer.parseInt(dlgDrawCircle.getTxtRadius().getText()), edgeColor, innerColor);
			Command cmd = new CmdAddShape(model, circle);
			executeCommand(cmd);
		}
	}

	public void addDonut(MouseEvent e) {
		DlgDrawDonut dlgDrawDonut = new DlgDrawDonut();
		dlgDrawDonut.setVisible(true);
		if (dlgDrawDonut.isConfirm()) {
			Donut donut = new Donut(new Point(e.getX(), e.getY()), Integer.parseInt(dlgDrawDonut.getTxtRadius().getText()), Integer.parseInt(dlgDrawDonut.getTxtInnerRadius().getText()), edgeColor, innerColor);
			Command cmd = new CmdAddShape(model, donut);
			executeCommand(cmd);
		}
	}

	public void addHexagon(MouseEvent e) {
		DlgDrawHexagon dlgDrawHexagon = new DlgDrawHexagon();
		dlgDrawHexagon.setVisible(true);
		if (dlgDrawHexagon.isConfirm()) {
			HexagonAdapter hexagon = new HexagonAdapter(new Point(e.getX(), e.getY()), Integer.parseInt(dlgDrawHexagon.getTxtRadius().getText()));
			hexagon.setHexagonBorderColor(edgeColor);
			hexagon.setHexagonInnerColor(innerColor);
			Command cmd = new CmdAddShape(model, hexagon);
			executeCommand(cmd);
		}
	}

	public void selectShape(MouseEvent e) {
		for (int i = model.getShapes().size() - 1; i >= 0; i--) {
			if (model.getByIndex(i).contains(e.getX(), e.getY())) {
				if (model.getByIndex(i).isSelected() == false) {
					Command cmd = new CmdSelectShape(model.getByIndex(i));
					executeCommand(cmd);
					break;
				} else {
					Command cmd = new CmdDeselectShape(model.getByIndex(i));
					executeCommand(cmd);
					break;
				}
			}
		}
	}

	public Shape getSelectedShape() {
		Iterator<Shape> iterator = model.getShapes().iterator();
		while (iterator.hasNext()) {
			Shape shapeForModification = iterator.next();
			if (shapeForModification.isSelected())
				return shapeForModification;
		}
		return null;
	}

	public void updateShape() {
		Shape shape = getSelectedShape();
		if (shape instanceof Point)
			updatePoint((Point) shape);
		else if (shape instanceof Line)
			updateLine((Line) shape);
		else if (shape instanceof Rectangle)
			updateRectangle((Rectangle) shape);
		else if (shape instanceof Donut)
			updateDonut((Donut) shape);
		else if (shape instanceof Circle)
			updateCircle((Circle) shape);
		else if (shape instanceof HexagonAdapter)
			updateHexagon((HexagonAdapter) shape);
	}

	public void updatePoint(Point oldPoint) {
		DlgModifyPoint dlgModifyPoint = new DlgModifyPoint();
		dlgModifyPoint.getTxtX().setText(Integer.toString(oldPoint.getX()));
		dlgModifyPoint.getTxtY().setText(Integer.toString(oldPoint.getY()));
		dlgModifyPoint.getBtnEdgeColor().setBackground(oldPoint.getColor());
		dlgModifyPoint.setVisible(true);
		if (dlgModifyPoint.isConfirm()) {
			Point newPoint = new Point(Integer.parseInt(dlgModifyPoint.getTxtX().getText()), Integer.parseInt(dlgModifyPoint.getTxtY().getText()), true, dlgModifyPoint.getBtnEdgeColor().getBackground());
			Command cmd = new CmdModifyPoint(oldPoint, newPoint);
			executeCommand(cmd);
		}
	}

	public void updateLine(Line oldLine) {
		DlgModifyLine dlgModifyLine = new DlgModifyLine();
		dlgModifyLine.getTxtStartPointX().setText(Integer.toString(oldLine.getStartPoint().getX()));
		dlgModifyLine.getTxtStartPointY().setText(Integer.toString(oldLine.getStartPoint().getY()));
		dlgModifyLine.getTxtEndPointX().setText(Integer.toString(oldLine.getEndPoint().getX()));
		dlgModifyLine.getTxtEndPointY().setText(Integer.toString(oldLine.getEndPoint().getY()));
		dlgModifyLine.getBtnEdgeColor().setBackground(oldLine.getColor());
		dlgModifyLine.setVisible(true);
		if (dlgModifyLine.isConfirm()) {
			Line newLine = new Line(new Point(Integer.parseInt(dlgModifyLine.getTxtStartPointX().getText()), Integer.parseInt(dlgModifyLine.getTxtStartPointY().getText())), new Point(Integer.parseInt(dlgModifyLine.getTxtEndPointX().getText()), Integer.parseInt(dlgModifyLine.getTxtEndPointY().getText())), true, dlgModifyLine.getBtnEdgeColor().getBackground());
			Command cmd = new CmdModifyLine(oldLine, newLine);
			executeCommand(cmd);
		}
	}

	public void updateRectangle(Rectangle oldRectangle) {
		DlgModifyRectangle dlgModifyRectangle = new DlgModifyRectangle();
		dlgModifyRectangle.getTxtUpperLeftPointX().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getX()));
		dlgModifyRectangle.getTxtUpperLeftPointY().setText(Integer.toString(oldRectangle.getUpperLeftPoint().getY()));
		dlgModifyRectangle.getTxtWidth().setText(Integer.toString(oldRectangle.getWidth()));
		dlgModifyRectangle.getTxtHeight().setText(Integer.toString(oldRectangle.getHeight()));
		dlgModifyRectangle.getBtnEdgeColor().setBackground(oldRectangle.getColor());
		dlgModifyRectangle.getBtnInnerColor().setBackground(oldRectangle.getInnerColor());
		dlgModifyRectangle.setVisible(true);
		if (dlgModifyRectangle.isConfirm()) {
			Rectangle newRectangle = new Rectangle(new Point(Integer.parseInt(dlgModifyRectangle.getTxtUpperLeftPointX().getText()), Integer.parseInt(dlgModifyRectangle.getTxtUpperLeftPointY().getText())), Integer.parseInt(dlgModifyRectangle.getTxtWidth().getText()), Integer.parseInt(dlgModifyRectangle.getTxtHeight().getText()), true, dlgModifyRectangle.getBtnEdgeColor().getBackground(), dlgModifyRectangle.getBtnInnerColor().getBackground());
			Command cmd = new CmdModifyRectangle(oldRectangle, newRectangle);
			executeCommand(cmd);
		}
	}

	public void updateCircle(Circle oldCircle) {
		DlgModifyCircle dlgModifyCircle = new DlgModifyCircle();
		dlgModifyCircle.getTxtCenterX().setText(Integer.toString(oldCircle.getCenter().getX()));
		dlgModifyCircle.getTxtCenterY().setText(Integer.toString(oldCircle.getCenter().getY()));
		dlgModifyCircle.getTxtRadius().setText(Integer.toString(oldCircle.getRadius()));
		dlgModifyCircle.getBtnEdgeColor().setBackground(oldCircle.getColor());
		dlgModifyCircle.getBtnInnerColor().setBackground(oldCircle.getInnerColor());
		dlgModifyCircle.setVisible(true);
		if (dlgModifyCircle.isConfirm()) {
			Circle newCircle = new Circle(new Point(Integer.parseInt(dlgModifyCircle.getTxtCenterX().getText()), Integer.parseInt(dlgModifyCircle.getTxtCenterY().getText())), Integer.parseInt(dlgModifyCircle.getTxtRadius().getText()), true, dlgModifyCircle.getBtnEdgeColor().getBackground(), dlgModifyCircle.getBtnInnerColor().getBackground());
			Command cmd = new CmdModifyCircle(oldCircle, newCircle);
			executeCommand(cmd);
		}
	}

	public void updateDonut(Donut oldDonut) {
		DlgModifyDonut dlgModifyDonut = new DlgModifyDonut();
		dlgModifyDonut.getTxtCenterX().setText(Integer.toString(oldDonut.getCenter().getX()));
		dlgModifyDonut.getTxtCenterY().setText(Integer.toString(oldDonut.getCenter().getY()));
		dlgModifyDonut.getTxtRadius().setText(Integer.toString(oldDonut.getRadius()));
		dlgModifyDonut.getTxtInnerRadius().setText(Integer.toString(oldDonut.getInnerRadius()));
		dlgModifyDonut.getBtnEdgeColor().setBackground(oldDonut.getColor());
		dlgModifyDonut.getBtnInnerColor().setBackground(oldDonut.getInnerColor());
		dlgModifyDonut.setVisible(true);
		if (dlgModifyDonut.isConfirm()) {
			Donut newDonut = new Donut(new Point(Integer.parseInt(dlgModifyDonut.getTxtCenterX().getText()), Integer.parseInt(dlgModifyDonut.getTxtCenterY().getText())), Integer.parseInt(dlgModifyDonut.getTxtRadius().getText()), Integer.parseInt(dlgModifyDonut.getTxtInnerRadius().getText()), true, dlgModifyDonut.getBtnEdgeColor().getBackground(), dlgModifyDonut.getBtnInnerColor().getBackground());
			Command cmd = new CmdModifyDonut(oldDonut, newDonut);
			executeCommand(cmd);
		}
	}

	public void updateHexagon(HexagonAdapter oldHexagon) {
		DlgModifyHexagon dlgModifyHexagon = new DlgModifyHexagon();
		dlgModifyHexagon.getTxtCenterHX().setText(Integer.toString(oldHexagon.getHexagonCenter().getX()));
		dlgModifyHexagon.getTxtCenterHY().setText(Integer.toString(oldHexagon.getHexagonCenter().getY()));
		dlgModifyHexagon.getTxtRadiusH().setText(Integer.toString(oldHexagon.getHexagonRadius()));
		dlgModifyHexagon.getBtnEdgeColor().setBackground(oldHexagon.getHexagonBorderColor());
		dlgModifyHexagon.getBtnInnerColor().setBackground(oldHexagon.getHexagonInnerColor());
		dlgModifyHexagon.setVisible(true);
		if (dlgModifyHexagon.isConfirm()) {
			HexagonAdapter newHexagon = new HexagonAdapter(new Point(Integer.parseInt(dlgModifyHexagon.getTxtCenterHX().getText()), Integer.parseInt(dlgModifyHexagon.getTxtCenterHY().getText())), Integer.parseInt(dlgModifyHexagon.getTxtRadiusH().getText()), true, dlgModifyHexagon.getBtnEdgeColor().getBackground(), dlgModifyHexagon.getBtnInnerColor().getBackground());
			Command cmd = new CmdModifyHexagon(oldHexagon, newHexagon);
			executeCommand(cmd);
		}
	}

	public void deleteShapes() {
		ArrayList<Shape> shapes = new ArrayList<>();
		if (frame.getBtnDelete().isEnabled()) {
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to remove selected shapes?", "Warning!", JOptionPane.YES_NO_OPTION) == 0) {
				for (Shape shape : model.getShapes()) {
					if (shape.isSelected()) {
						shapes.add(shape);
					}
				}
				Command cmd = new CmdRemoveShape(model, shapes);
				executeCommand(cmd);
			}
		}
	}

	public void executeCommand(Command command) {
		clearUndoRedoStack(undoRedoCounter);
		undoRedoStack.push(command);
		command.execute();
		undoRedoCounter++;
		observeButtons();
		frame.addToLogList(command.toString());
		frame.getView().repaint();
		frame.getBtnUndo().setEnabled(true);
		frame.getBtnRedo().setEnabled(false);
		frame.getTglBtnSelect().setEnabled(true);
	}

	public void clearUndoRedoStack(int undoRedoCounter) {
		if (undoRedoStack.size() < 1) {
			return;
		}
		for (int i = undoRedoStack.size() - 1; i > undoRedoCounter; i--) {
			undoRedoStack.remove(i);
		}
	}

	public void undo() {
		undoRedoStack.get(undoRedoCounter).unexecute();
		frame.addToLogList("Undo:" + undoRedoStack.get(undoRedoCounter).toString());
		undoRedoCounter--;
		observeButtons();
		frame.repaint();
		frame.getBtnRedo().setEnabled(true);
		if (undoRedoCounter == -1)
			frame.getBtnUndo().setEnabled(false);
	}

	public void redo() {
		undoRedoCounter++;
		undoRedoStack.get(undoRedoCounter).execute();
		frame.addToLogList("Redo:" + undoRedoStack.get(undoRedoCounter).toString());
		observeButtons();
		frame.repaint();
		frame.getBtnUndo().setEnabled(true);
		if (undoRedoCounter >= undoRedoStack.size() - 1) {
			frame.getBtnRedo().setEnabled(false);
		}
	}

	public void toFront() {
		Shape shape = getSelectedShape();
		Command cmd = new CmdToFront(model, shape);
		executeCommand(cmd);
	}

	public void bringToFront() {
		Shape shape = getSelectedShape();
		Command cmd = new CmdBringToFront(model, shape);
		executeCommand(cmd);
	}

	public void toBack() {
		Shape shape = getSelectedShape();
		Command cmd = new CmdToBack(model, shape);
		executeCommand(cmd);
	}

	public void bringToBack() {
		Shape shape = getSelectedShape();
		Command cmd = new CmdBringToBack(model, shape);
		executeCommand(cmd);
	}

	public void observeButtons() {
		int count = countOfSelectedShapes();
		if (model.getShapes().size() != 0) {
			observable.setSelectBtnActivated(true);
			if (count != 0) {
				if (count == 1) {
					observable.setModifyBtnActivated(true);
					observePositionButtons();
				} else { // vise selektovanih
					observable.setModifyBtnActivated(false);
					observable.setToFrontBtnActivated(false);
					observable.setToBackBtnActivated(false);
					observable.setBringToFrontBtnActivated(false);
					observable.setBringToBackBtnActivated(false);
				}
				observable.setDeleteBtnActivated(true); // i ako je selektovan 1 i ako je vise
			} else { // ako nijedan nije selektovan
				observable.setModifyBtnActivated(false);
				observable.setDeleteBtnActivated(false);
				observable.setToFrontBtnActivated(false);
				observable.setToBackBtnActivated(false);
				observable.setBringToFrontBtnActivated(false);
				observable.setBringToBackBtnActivated(false);
			}
		} else { // ako nista nije nacrtano
			observable.setSelectBtnActivated(false);
			observable.setModifyBtnActivated(false);
			observable.setDeleteBtnActivated(false);
			observable.setToFrontBtnActivated(false);
			observable.setToBackBtnActivated(false);
			observable.setBringToFrontBtnActivated(false);
			observable.setBringToBackBtnActivated(false);
		}
	}

	public void observePositionButtons() {
		Iterator<Shape> it = model.getShapes().iterator();
		Shape shape;

		while (it.hasNext()) {
			shape = it.next();
			if (shape.isSelected()) {
				if (model.getShapes().size() == 1) {
					observable.setToFrontBtnActivated(false);
					observable.setToBackBtnActivated(false);
					observable.setBringToFrontBtnActivated(false);
					observable.setBringToBackBtnActivated(false);
				} else {
					if (shape.equals(model.getByIndex(model.getShapes().size() - 1))) {
						observable.setToFrontBtnActivated(false);
						observable.setToBackBtnActivated(true);
						observable.setBringToFrontBtnActivated(false);
						observable.setBringToBackBtnActivated(true);
					} else if (shape.equals(model.getByIndex(0))) {
						observable.setToFrontBtnActivated(true);
						observable.setToBackBtnActivated(false);
						observable.setBringToFrontBtnActivated(true);
						observable.setBringToBackBtnActivated(false);
					} else {
						observable.setToFrontBtnActivated(true);
						observable.setToBackBtnActivated(true);
						observable.setBringToFrontBtnActivated(true);
						observable.setBringToBackBtnActivated(true);
					}
				}
			}
		}
	}

	public int countOfSelectedShapes() {
		int counter = 0;
		for (Shape shape : model.getShapes()) {
			if (shape.isSelected()) {
				counter++;
			}
		}
		return counter;
	}

	public void saveDrawing() {
		SavingManager saveDrawing = new SavingManager(new SavingDraw(model.getShapes()));
		saveDrawing.save();
	}

	public void saveLog() {
		SavingManager saveLog = new SavingManager(new SavingLog(frame.getDlm()));
		saveLog.save();
	}

	@SuppressWarnings("unchecked")
	public void openDrawing() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("ser file (.ser)", "ser"));
		chooser.setDialogTitle("Open Drawing");
		int option = chooser.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = new File(chooser.getSelectedFile().getAbsolutePath());
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				frame.getBtnUndo().setEnabled(false);
				undoRedoStack.clear();
				undoRedoCounter = -1;
				model.getShapes().clear();
				frame.getDlm().clear();
				ArrayList<Shape> shapesList = (ArrayList<Shape>) objectInputStream.readObject();
				for (Shape shape : shapesList) {
					model.add(shape);
					observeButtons();
					if (shape.isSelected()) {
						shape.setSelected(true);
					}
				}
				frame.getTglBtnSelect().setEnabled(true);
				objectInputStream.close();
				fileInputStream.close();
				JOptionPane.showMessageDialog(null, "Drawing loaded successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				frame.getView().repaint();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error while opening the file", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void openLog() throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		chooser.setDialogTitle("Open Log");
		int option = chooser.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = new File(chooser.getSelectedFile().getAbsolutePath());
			try {
				frame.getBtnUndo().setEnabled(false);
				undoRedoStack.clear();
				undoRedoCounter = -1;
				model.getShapes().clear();
				frame.getDlm().clear();
				frame.getBtnLoadNext().setEnabled(true);
				bufferedReader = new BufferedReader(new FileReader(file));
				frame.getView().repaint();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error while opening log file", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void loadNext() {
		try {
			if ((line = bufferedReader.readLine()) != null)  {
				bufferedReader.mark(1);
				checkLine(); 
				String[] command = line.split("\\W"); 
				if (command[1].equals("Point")) {
					String action = command[0];
					Point point = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]), new Color(Integer.parseInt(command[5]), Integer.parseInt(command[6]), Integer.parseInt(command[7])));
					switch (action) {
					case "Added":
						executeCommand(new CmdAddShape(model, point));
						observeButtons();
						break;
					case "Selected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Point) {
								if (point.equals((Point) s)) {
									executeCommand(new CmdSelectShape(s));
								}
							}
						}
						break;
					case "Deselected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Point) {
								if (point.equals((Point) s)) {
									executeCommand(new CmdDeselectShape(s));
								}
							}
						}
						break;
					case "Modified":
						Point newPoint = new Point(Integer.parseInt(command[12]), Integer.parseInt(command[13]), new Color(Integer.parseInt(command[15]), Integer.parseInt(command[16]), Integer.parseInt(command[17])));
						for (Shape shape : model.getShapes()) {
							if (shape instanceof Point) {
								if (point.equals((Point) shape)) {
									executeCommand(new CmdModifyPoint((Point) shape, newPoint));
								}
							}
						}
						break;
					default:
						break;
					}
				} else if (command[1].equals("Line")) {
					String action = command[0];
					Line line = new Line(new Point(Integer.parseInt(command[3]), Integer.parseInt(command[4])), new Point(Integer.parseInt(command[6]), Integer.parseInt(command[7])), new Color(Integer.parseInt(command[9]), Integer.parseInt(command[10]), Integer.parseInt(command[11])));
					switch (action) {
					case "Added":
						executeCommand(new CmdAddShape(model, line));
						observeButtons();
						break;
					case "Selected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Line) {
								if (line.equals((Shape) s)) {
									executeCommand(new CmdSelectShape(s));
								}
							}
						}
						break;
					case "Deselected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Line) {
								if (line.equals((Shape) s)) {
									executeCommand(new CmdDeselectShape(s));
								}
							}
						}
						break;
					case "Modified":
						Line newLine = new Line(new Point(Integer.parseInt(command[17]), Integer.parseInt(command[18])), new Point(Integer.parseInt(command[20]), Integer.parseInt(command[21])), new Color(Integer.parseInt(command[23]), Integer.parseInt(command[24]), Integer.parseInt(command[25])));
						for (Shape shape : model.getShapes()) {
							if (shape instanceof Line) {
								if (line.equals((Shape) shape)) {
									executeCommand(new CmdModifyLine((Line) shape, newLine));
								}
							}
						}
						break;
					default:
						break;
					}
				} else if (command[1].equals("Circle")) {

					String action = command[0];
					Circle circle = new Circle(new Point(Integer.parseInt(command[3]), Integer.parseInt(command[4])), Integer.parseInt(command[7]), new Color(Integer.parseInt(command[9]), Integer.parseInt(command[10]), Integer.parseInt(command[11])), new Color(Integer.parseInt(command[14]), Integer.parseInt(command[15]), Integer.parseInt(command[16])));
					switch (action) {
					case "Added":
						executeCommand(new CmdAddShape(model, circle));
						observeButtons();
						break;
					case "Selected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Circle) {
								if (circle.equals((Shape) s)) {
									executeCommand(new CmdSelectShape(s));
								}
							}
						}
						break;
					case "Deselected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Circle) {
								if (circle.equals((Shape) s)) {
									executeCommand(new CmdDeselectShape(s));
								}
							}
						}
						break;
					case "Modified":
						Circle newCircle = new Circle(new Point(Integer.parseInt(command[22]), Integer.parseInt(command[23])), Integer.parseInt(command[26]), new Color(Integer.parseInt(command[28]), Integer.parseInt(command[29]), Integer.parseInt(command[30])), new Color(Integer.parseInt(command[33]), Integer.parseInt(command[34]), Integer.parseInt(command[35])));
						for (Shape shape : model.getShapes()) {
							if (shape instanceof Circle) {
								if (circle.equals((Shape) shape)) {
									executeCommand(new CmdModifyCircle((Circle) shape, newCircle));
								}
							}
						}
						break;
					default:
						break;
					}
				} else if (command[1].equals("Rectangle")) {
					String action = command[0];
					Rectangle rectangle = new Rectangle(new Point(Integer.parseInt(command[5]), Integer.parseInt(command[6])), Integer.parseInt(command[14]), Integer.parseInt(command[10]), new Color(Integer.parseInt(command[16]), Integer.parseInt(command[17]), Integer.parseInt(command[18])), new Color(Integer.parseInt(command[21]), Integer.parseInt(command[22]), Integer.parseInt(command[23])));
					switch (action) {
					case "Added":
						executeCommand(new CmdAddShape(model, rectangle));
						observeButtons();
						break;
					case "Selected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Rectangle) {
								if (rectangle.equals((Shape) s)) {
									executeCommand(new CmdSelectShape(s));
								}
							}
						}
						break;
					case "Deselected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Rectangle) {
								if (rectangle.equals((Shape) s)) {
									executeCommand(new CmdDeselectShape(s));
								}
							}
						}
						break;
					case "Modified":
						Rectangle newRectangle = new Rectangle(new Point(Integer.parseInt(command[33]), Integer.parseInt(command[34])), Integer.parseInt(command[42]), Integer.parseInt(command[38]), new Color(Integer.parseInt(command[44]), Integer.parseInt(command[45]), Integer.parseInt(command[46])), new Color(Integer.parseInt(command[49]), Integer.parseInt(command[50]), Integer.parseInt(command[51])));
						for (Shape shape : model.getShapes()) {
							if (shape instanceof Rectangle) {
								if (rectangle.equals((Shape) shape)) {
									executeCommand(new CmdModifyRectangle((Rectangle) shape, newRectangle));
								}
							}
						}
						break;
					default:
						break;
					}
				} else if (command[1].equals("Donut")) {
					String action = command[0];
					Donut donut = new Donut(new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])), Integer.parseInt(command[8]), Integer.parseInt(command[10]), new Color(Integer.parseInt(command[12]), Integer.parseInt(command[13]), Integer.parseInt(command[14])), new Color(Integer.parseInt(command[17]), Integer.parseInt(command[18]), Integer.parseInt(command[19])));
					switch (action) {
					case "Added":
						executeCommand(new CmdAddShape(model, donut));
						observeButtons();
						break;
					case "Selected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Donut) {
								if (donut.equals((Shape) s)) {
									executeCommand(new CmdSelectShape(s));
								}
							}
						}
						break;
					case "Deselected":
						for (Shape s : model.getShapes()) {
							if (s instanceof Donut) {
								if (donut.equals((Shape) s)) {
									if (s.isSelected()) {
										executeCommand(new CmdDeselectShape(s));
									}
								}
							}
						}
						break;
					case "Modified":
						Donut newDonut = new Donut(new Point(Integer.parseInt(command[28]), Integer.parseInt(command[29])), Integer.parseInt(command[32]), Integer.parseInt(command[34]), new Color(Integer.parseInt(command[36]), Integer.parseInt(command[37]), Integer.parseInt(command[38])), new Color(Integer.parseInt(command[41]), Integer.parseInt(command[42]), Integer.parseInt(command[43])));
						for (Shape shape : model.getShapes()) {
							if (shape instanceof Donut) {
								if (donut.equals((Shape) shape)) {
									executeCommand(new CmdModifyDonut((Donut) shape, newDonut));
								}
							}
						}
						break;
					default:
						break;
					}
				} else if (command[1].equals("Hexagon")) {
					String action = command[0];
					Hexagon hexagon = new Hexagon(Integer.parseInt(command[4]), Integer.parseInt(command[5]), Integer.parseInt(command[7]));
					HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon, new Color(Integer.parseInt(command[10]), Integer.parseInt(command[11]), Integer.parseInt(command[12])), new Color(Integer.parseInt(command[15]), Integer.parseInt(command[16]), Integer.parseInt(command[17])));
					switch (action) {
					case "Added":
						executeCommand(new CmdAddShape(model, hexagonAdapter));
						observeButtons();
						break;
					case "Selected":
						for (Shape s : model.getShapes()) {
							if (s instanceof HexagonAdapter) {
								if (hexagonAdapter.equals((Shape) s)) {
									executeCommand(new CmdSelectShape(s));
								}
							}
						}
						break;
					case "Deselected":
						for (Shape s : model.getShapes()) {
							if (s instanceof HexagonAdapter) {
								if (hexagonAdapter.equals((Shape) s)) {
									if (s.isSelected()) {
										executeCommand(new CmdDeselectShape(s));
									}
								}
							}
						}
						break;
					case "Modified":
						Hexagon newHexagon = new Hexagon(Integer.parseInt(command[24]), Integer.parseInt(command[25]), Integer.parseInt(command[27]));
						HexagonAdapter newHexagonAdapter = new HexagonAdapter(newHexagon, new Color(Integer.parseInt(command[30]), Integer.parseInt(command[31]), Integer.parseInt(command[32])), new Color(Integer.parseInt(command[35]), Integer.parseInt(command[36]), Integer.parseInt(command[37])));
						for (Shape shape : model.getShapes()) {
							if (shape instanceof HexagonAdapter) {
								if (hexagonAdapter.equals((Shape) shape)) {
									executeCommand(new CmdModifyHexagon((HexagonAdapter) shape, newHexagonAdapter));
								}
							}
						}
						break;
					default:
						break;
					}
				}
				String action = command[0];
				switch (action) {
				case "Undo":
					undo();
					break;
					
				case "Redo":
					redo();
					break;
					
				case "ToFront":
					toFront();
					break;
					
				case "ToBack":
					toBack();
					break;
					
				case "BringToFront":
					bringToFront();
					break;
					
				case "BringToBack":
					bringToBack();
					break;
				
				case "Removed": 
					ArrayList<Shape> shapes = new ArrayList<>();
					for(Shape shape : model.getShapes()) {
						if(shape.isSelected()) {
							shapes.add(shape);
						}
					}
					executeCommand(new CmdRemoveShape(model, shapes));
					break;				
				default:
					break;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkLine() {
		try {
			if (bufferedReader.readLine() == null) {
				frame.getBtnLoadNext().setEnabled(false);
				bufferedReader.close();
			} else {
				bufferedReader.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error while opening the file.", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
}