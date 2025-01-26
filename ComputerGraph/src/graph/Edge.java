package graph;

import java.awt.*;

public class Edge {

	private static float width = 2.5f;
	private Node a, b;
	private boolean directed = false;
	private boolean highlighted = false;
	public static boolean showWeights = false;
	public static boolean directEdge = false;
	Color c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
	
	private int weight = 1;
	
	public Edge(Node a, Node b) { 
		this.a = a;
		this.b = b;
		if(directEdge) {
			this.directed = true;
		}
	}
	
	public Edge(Node a, Node b, int weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
		if(directEdge) {
			this.directed = true;
		}
	}
	
	public void draw(Graphics2D g) {
		BasicStroke stroke = new BasicStroke(width);
		
		//g.setColor(new Color(200,200,200));
		if(highlighted) {
			g.setColor(Color.red);
			stroke = new BasicStroke(2 * width);
		} else {
			g.setColor(c);
		}
		
		g.setStroke(stroke);
		g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
		if(showWeights) {
			float dy = (a.getY() - b.getY()), dx = (a.getX() - b.getX());
			
			if (dy==0) {dy=dx;}
			
			float slope = (float)-dx/dy;
			int xbuffer = (slope>0 ? 10 : -10);
			//g.drawString("tu mama", ((a.getX() + b.getX()) / 2)+xbuffer , ((a.getY() + b.getY()) / 2) - 10);

			g.drawString(String.valueOf(weight), ((a.getX() + b.getX()) / 2)+xbuffer , ((a.getY() + b.getY()) / 2) - 10);
		}
		
		if(directed) {
			Pair v = new Pair(b.getX() - a.getX(), b.getY() - a.getY());
			double dist = Math.sqrt(v.f*v.f + v.s*v.s);
			double v_ux = v.f/dist, v_uy = v.s/dist;
			
			//Pair u = new Pair(a.getX() - b.getX(), b.getY() - a.getY());
			Pair u = new Pair(b.getY() - a.getY(),b.getX() - a.getX());
			double u_ux = u.f/dist, u_uy = u.s/dist;
			
			int c = 10, d = 6;
			
			int[] xPoints = {(int) (b.getX() - Node.radius * v_ux), 
								(int) (b.getX() - (Node.radius + c) * v_ux + d * u_ux),
								(int) (b.getX() - (Node.radius + c) * v_ux - d * u_ux)};
			int[] yPoints = {(int) (b.getY() - Node.radius * v_uy), 
								(int) (b.getY() - (Node.radius + c) * v_uy - d * u_uy),
								(int) (b.getY() - (Node.radius + c) * v_uy + d * u_uy)};
			
			g.fillPolygon(xPoints, yPoints, xPoints.length);
		}
	}
	
	public int getAIndex() {return this.a.index;}
	public int getBIndex() {return this.b.index;}
	public int getWeight() {return this.weight;}
	public boolean isDirected() {return this.directed;}
	
	public void highlight() {
		highlighted = true;
	}
	public void dehighlight() {
		highlighted = false;
	}
}
