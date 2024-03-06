package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {

	private static final long serialVersionUID = 1L;
	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius); 
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		this.setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		this.setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		this.setInnerColor(innerColor);
	}
	
	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
		this(center, radius, innerRadius);
		this.setColor(color);
		this.setInnerColor(innerColor);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(this.getCenter().getX()-this.innerRadius, this.getCenter().getY()-this.innerRadius,
				this.innerRadius*2, this.innerRadius*2);
	}

	@Override
	public void draw(Graphics g) {
		/*super.draw(g);
		g.setColor(getColor());
		g.drawOval(this.getCenter().getX() - this.innerRadius, this.getCenter().getY() - this.innerRadius,
				this.innerRadius * 2, this.innerRadius * 2);*/
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		java.awt.Shape outer = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(),
				this.getCenter().getY() - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);

		java.awt.Shape inner = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius(),
				this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius() * 2, this.getInnerRadius() * 2);

		Area circle = new Area(outer);
		circle.subtract(new Area(inner));

		g2d.setColor(this.getInnerColor());
		g2d.fill(circle);

		g2d.setColor(this.getColor());
		g2d.draw(circle);

		g2d.dispose();

		if (isSelected()) {
			super.selectCircle(g);
		}
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}

	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			if (this.getCenter().equals(pomocni.getCenter()) && this.getRadius() == pomocni.getRadius()
					&& this.innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}


	@Override
	public String toString() {
		return "Donut: center"+"(" + getCenter().getX() + "," + getCenter().getY() + ")" + " radius:" + radius + " InnerRadius:" + 
				this.innerRadius + " "+ "EdgeColor("+ getColor().getRed()+","+ getColor().getGreen()+","+ getColor().getBlue()+")" + "InnerColor:("+ getInnerColor().getRed()+","+ getInnerColor().getGreen()+" "+ getInnerColor().getBlue()+")";
	}
	
	public Donut clone() {
		return new Donut(center.clone(), radius, innerRadius, getColor(), getInnerColor());
	}

}