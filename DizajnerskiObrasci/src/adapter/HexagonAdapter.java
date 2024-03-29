package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import geometry.SurfaceShape;
import hexagon.Hexagon;


public class HexagonAdapter extends SurfaceShape {

	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	

	public HexagonAdapter() {

	}

	public HexagonAdapter(Point center, int radius) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
	}

	public HexagonAdapter(Point center, int radius, boolean selected) {
		this(center, radius);
		this.hexagon.setSelected(selected);
	}

	public HexagonAdapter(Point center, int radius, boolean selected, Color edgeColor) {
		this(center, radius, selected);
		this.hexagon.setBorderColor(edgeColor);
	}

	public HexagonAdapter(Point center, int radius, boolean selected, Color edgeColor, Color innerColor) {
		this(center, radius, selected, edgeColor);
		this.hexagon.setAreaColor(innerColor);
	}

	public HexagonAdapter(Point center, int radius, Color edgeColor, Color innerColor) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
		this.hexagon.setBorderColor(edgeColor);
		this.hexagon.setAreaColor(innerColor);
	}
	
	public HexagonAdapter(Hexagon hexagon,Color color,Color innerColor) {
		this.hexagon=hexagon;
		this.setHexagonBorderColor(color);
		this.setHexagonInnerColor(innerColor);
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.hexagon.setX(byX);
		this.hexagon.setY(byY);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Hexagon) {
			return (this.hexagon.getR() - ((Hexagon) o).getR());
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter hexAdapter = (HexagonAdapter) obj;
			if (this.hexagon.getX() == hexAdapter.getHexagon().getX()
					&& this.hexagon.getY() == hexAdapter.getHexagon().getY()
					&& this.hexagon.getR() == hexAdapter.getHexagon().getR()) {

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public double area() {
		return (int) (((3 * Math.sqrt(3)) / 2) * this.hexagon.getR() * this.hexagon.getR());
	}

	@Override
	public void fill(Graphics g) {

	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);
	}

	@Override
	public boolean contains(int x, int y) {

		return hexagon.doesContain(x, y);

	}

	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void setSelected(boolean selected) {
		this.hexagon.setSelected(selected);
	}

	public Hexagon getHexagon() {
		return this.hexagon;
	}

	public void setHexagon(Point center, int radius, Color edgeColor, Color innerColor) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
		this.hexagon.setBorderColor(edgeColor);
		this.hexagon.setAreaColor(innerColor);
		hexagon.setSelected(true);
	}

	public Point getHexagonCenter() {
		return new Point(this.hexagon.getX(), this.hexagon.getY());
	}

	public void setHexagonCenter(Point center) {
		this.hexagon.setX(center.getX());
		this.hexagon.setY(center.getY());
	}

	public int getHexagonRadius() {
		return this.hexagon.getR();
	}

	public void setHexagonRadius(int radius) {
		this.hexagon.setR(radius);
	}

	public Color getHexagonBorderColor() {
		return this.hexagon.getBorderColor();
	}

	public void setHexagonBorderColor(Color edgeColor) {
		this.hexagon.setBorderColor(edgeColor);
	}

	public Color getHexagonInnerColor() {
		return this.hexagon.getAreaColor();
	}

	public void setHexagonInnerColor(Color innerColor) {
		this.hexagon.setAreaColor(innerColor);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public String toString() {
		return "Hexagon: Center" +"(" + getHexagonCenter().getX() + "," + getHexagonCenter().getY() + ")" + "radius("
				+ getHexagonRadius() + ")" + "EdgeColor:(" + this.getHexagonBorderColor().getRed() + "." + getHexagonBorderColor().getGreen() + "."
				+ getHexagonBorderColor().getBlue() + ")"
				+"InnerColor:(" + getHexagonInnerColor().getRed() + "." + getHexagonInnerColor().getGreen() + "."
				+ getHexagonInnerColor().getBlue() + ")";
	}
	
	public HexagonAdapter clone () {	
		HexagonAdapter h = new HexagonAdapter(getHexagonCenter(),getHexagonRadius());
		h.setHexagonBorderColor(getHexagonBorderColor());
		h.setHexagonInnerColor(getHexagonInnerColor());
		return h;
	}

}
