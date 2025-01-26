package graph;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Node extends JComponent {
	
	public static int counter;
	public static int radius = 10;
	
	private String label;
	private Color color, outline = Color.white;
	public int index;
	private int x, y;
	

	public Node(String label, Color color, int x, int y) {
		this.label = label;
		this.color = color;
		index = counter++;
		this.x = x; this.y = y;
		
		
	}
	
	public String getLabel() {
		return label;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setOutline(Color color) {
		this.outline = color;
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		BasicStroke stroke = new BasicStroke(3.0f);
		g2.setStroke(stroke);
		
		g.setColor(this.color);
		g.fillOval(this.x - radius, this.y - radius, 2*radius, 2*radius);
		g.setColor(outline);
		g.drawOval(this.x - radius, this.y - radius, 2*radius, 2*radius);
		g.drawString(label, this.x + 2*radius, this.y + 2*radius);
	}

	
}
