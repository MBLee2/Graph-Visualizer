package graph;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements MouseListener, MouseMotionListener {
	
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public Panel() {
		
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
	}
	
	public void addNode(String label, Color color, int x, int y) {
		for(Node node : nodes) {
			if(node.getLabel().equals(label)) {
				return;
			}
		}
		nodes.add(new Node(label,color,x,y));
		repaint();
	}
	public void addEdge(int id1, int id2) {
		edges.add(new Edge(nodes.get(id1),nodes.get(id2)));
		repaint();
	}
	public void addEdge(int id1, int id2, int weight) {
		edges.add(new Edge(nodes.get(id1),nodes.get(id2),weight));
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		setBackground(Color.black);
		
		for (Edge edge : edges) {
			edge.draw((Graphics2D) g);
		}
		
		for (Node node : nodes) {
			node.draw(g);
		}
	}

	long sq(int d) {return d*d;}
	
	private Node dragged_node = null;
	
	public String toString() {
		String ret = this.nodes.size()+" "+this.edges.size()+"\n";
		for (Edge edge : edges) {ret += (edge.getAIndex()+1) + " " + (edge.getBIndex()+1) + "\n";}
		return ret;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("ok");
		
		for(Node node : nodes) {
			if(sq(Node.radius) >= sq(e.getX() - node.getX()) + sq(e.getY() - node.getY())) {
				dragged_node = node;
				dragged_node.setOutline(Color.yellow);
				repaint();
				break;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(dragged_node != null) {
			dragged_node.setX(e.getX());
			dragged_node.setY(e.getY());
		}
		repaint();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if(dragged_node != null) {
			dragged_node.setOutline(Color.white);
			dragged_node = null;
		}
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}
}
