package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

import javax.swing.*;

public class Frame implements ActionListener {
	
	JFrame frame = new JFrame();
	Panel graph = new Panel();
	
	JLabel node_label = new JLabel("Node label:");
	JTextField field = new JTextField();
    JButton add_node = new JButton("+ Add Node");
    JButton del_node = new JButton("Delete Node");
    
    JLabel node_color = new JLabel("Node Color (hex):");
    JTextField hex_color = new JTextField();
    
    JLabel edge_label = new JLabel("Edge nodes:            Weight:");
    JTextField n1 = new JTextField(), n2 = new JTextField(), weight_tb = new JTextField();
    JButton add_edge = new JButton("+ Add Edge");
    JButton del_edge = new JButton("Delete Edge");
    JCheckBox show_weights = new JCheckBox("Show Weights");
    JCheckBox direct_edges = new JCheckBox("Direct Edges");
    
    JTextField file_name = new JTextField();
    JButton import_graph = new JButton("Import Graph");
    
    JTextField f_n1 = new JTextField(), f_n2 = new JTextField();
    String[] options = {"BFS", "Dijkstra", "Hopcroft-Karp", "Find Component", "Check DAG"};
    JComboBox<String> dropdown = new JComboBox<>(options);
    JButton start = new JButton("Start Algorithm");
    JButton stop = new JButton("Stop Algorithm");
    JLabel res_label = new JLabel("Result: ");
    JLabel res = new JLabel();
    
    JTextField rndir_n = new JTextField();
    JTextField rndir_m = new JTextField();
    JButton rand_undirected = new JButton("Random Simple Graph");
    

    JButton clear_graph = new JButton("Clear Graph");
    JButton print_graph = new JButton("Print Graph");
    
    Algs algs;
	
	
	public Frame() {
		frame.setSize(1200, 900);
		frame.setBackground(Color.black);

		graph.setSize(900,900);
		graph.setLayout(null);
	    graph.setLocation(0, 0);
	    
	    
	    node_label.setSize(node_label.getPreferredSize());
	    field.setSize(110, 25);
	    add_node.setSize(add_node.getPreferredSize());
	    del_node.setSize(del_node.getPreferredSize());
	    
	    
	    rand_undirected.setSize(rand_undirected.getPreferredSize());
	    rndir_n.setSize(50, 25);
	    rndir_m.setSize(50, 25);
	    
	    node_color.setSize(node_color.getPreferredSize());
	    hex_color.setSize(110, 25);

	    edge_label.setSize(edge_label.getPreferredSize());
	    weight_tb.setSize(110,25);
	    n1.setSize(50, 25); n2.setSize(50, 25);
	    add_edge.setSize(add_edge.getPreferredSize());
	    del_edge.setSize(del_edge.getPreferredSize());
	    show_weights.setSize(show_weights.getPreferredSize());
	    direct_edges.setSize(direct_edges.getPreferredSize());
	    
	    
	    import_graph.setSize(import_graph.getPreferredSize());
	    file_name.setSize(110, 25);
	    
	    f_n1.setSize(40, 25); f_n2.setSize(40, 25);
	    dropdown.setSize(dropdown.getPreferredSize());
	    start.setSize(start.getPreferredSize());
	    stop.setSize(stop.getPreferredSize());
	    res_label.setSize(res_label.getPreferredSize());
	    
	    print_graph.setSize(print_graph.getPreferredSize());
	    clear_graph.setSize(clear_graph.getPreferredSize());
	    

	    node_label.setLocation(graph.getWidth() + 10, 50);
	    field.setLocation(graph.getWidth() + 10, node_label.getY() + node_label.getHeight() + 10);
	    add_node.setLocation(field.getX() + field.getWidth() + 10, field.getY());
	    
	    node_color.setLocation(node_label.getX(), field.getY() + field.getHeight() + 10);
	    hex_color.setLocation(node_color.getX(), node_color.getY() + node_color.getHeight() + 5);
	    del_node.setLocation(add_node.getX(), hex_color.getY());
	    
	    edge_label.setLocation(node_label.getX(), 200);
	    
	    n1.setLocation(edge_label.getX(), 210 + edge_label.getHeight());
	    n2.setLocation(n1.getX() + n1.getWidth() + 10, 210 + edge_label.getHeight());
	    weight_tb.setLocation(n2.getX() + n2.getWidth() + 10, 210+edge_label.getHeight());
	    

	    add_edge.setLocation(edge_label.getX(), n1.getY() + n1.getHeight() + 10);
	    del_edge.setLocation(add_edge.getX() + add_edge.getWidth() + 10, add_edge.getY());
	    
	    show_weights.setLocation(add_edge.getX(), add_edge.getY() + add_edge.getHeight() + 10);
	    direct_edges.setLocation(show_weights.getX()+show_weights.getWidth()+10, show_weights.getY());

	    file_name.setLocation(show_weights.getX(), 350);
	    import_graph.setLocation(file_name.getX() + file_name.getWidth() + 10, 350);

	    rndir_n.setLocation(file_name.getX(), 400);
	    rndir_m.setLocation(rndir_n.getX() + rndir_n.getWidth() + 10,400);
	    rand_undirected.setLocation(rndir_n.getX(), rndir_m.getY() + rndir_m.getHeight() + 5);

	    f_n1.setLocation(rndir_n.getX(), 550);
	    f_n2.setLocation(f_n1.getX() + f_n1.getWidth() + 10, 550);
	    dropdown.setLocation(f_n2.getX() + f_n2.getWidth() + 10, 550);
	    
	    start.setLocation(f_n1.getX(), dropdown.getY() + dropdown.getHeight() + 10);
	    stop.setLocation(start.getX() + start.getWidth() + 10, start.getY());
	    
	    res_label.setLocation(start.getX(), start.getY() + start.getHeight() + 10);
	    res.setLocation(res_label.getX() + res_label.getWidth() + 10, res_label.getY());
	    
	    print_graph.setLocation(frame.getWidth() - print_graph.getWidth() - 10, 800);
	    clear_graph.setLocation(print_graph.getX() - clear_graph.getWidth() - 10, 800);
	    
	    
	    
		frame.add(graph);
		
		frame.add(node_label);
		frame.add(field);
		frame.add(add_node);
		frame.add(del_node);
		
		frame.add(node_color);
		frame.add(hex_color);
		
		frame.add(edge_label);
		frame.add(add_edge);
		frame.add(n1); 
		frame.add(n2);
		frame.add(weight_tb);
		frame.add(del_edge);
		frame.add(show_weights);
		frame.add(direct_edges);
		
		frame.add(file_name);
		frame.add(import_graph);
		
		frame.add(rand_undirected);
		frame.add(rndir_m);
		frame.add(rndir_n);
		
		frame.add(f_n1);
		frame.add(f_n2);
		frame.add(dropdown);
		frame.add(start);
		frame.add(stop);
		
		frame.add(res_label);
		frame.add(res);
		
		frame.add(print_graph);
		frame.add(clear_graph);
		
		
		add_node.setActionCommand("Add Node");
		add_node.addActionListener(this);
		del_node.setActionCommand("Delete Node");
		del_node.addActionListener(this);
		
		add_edge.setActionCommand("Add Edge");
		add_edge.addActionListener(this);
		del_edge.setActionCommand("Delete Edge");
		del_edge.addActionListener(this);
		show_weights.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Edge.showWeights = (e.getStateChange() == ItemEvent.SELECTED);
				graph.repaint();
			}
		});
		direct_edges.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Edge.directEdge = (e.getStateChange() == ItemEvent.SELECTED);
				graph.repaint();
			}
		});
		
		
		import_graph.setActionCommand("import");
		import_graph.addActionListener(this);
		
		rand_undirected.setActionCommand("rand undir");
		rand_undirected.addActionListener(this);
		print_graph.setActionCommand("print");
		print_graph.addActionListener(this);
		clear_graph.setActionCommand("clear");
		clear_graph.addActionListener(this);
		
		start.setActionCommand("start alg");
		start.addActionListener(this);
		stop.setActionCommand("stop alg");
		stop.addActionListener(this);
		

		
		frame.setLayout(null);
		graph.repaint();
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				frame.setSize(1200, 900);
				graph.repaint();
			}
		});
		
		
	}
	
	public Color rand_color() {
        return new Color((int)Math.random()*156 + 100,(int)Math.random()*156 + 100,(int)Math.random()*156 + 100);
    }
	
	public int rand_x() {
		return (int) (Math.random() * 800 + 10);
	}
	
	public int rand_y() {
		return (int) (Math.random() * 800 + 10);
	}
	
	public int get_node(String l) {
		for (int i=0;i<graph.nodes.size();i++) {
			Node node = graph.nodes.get(i);
			if (node.getLabel().equals(l)) {
				return i;
			}
		}
		return -1;
	}
	
	
	public void read_graph(String filepath) throws IOException {
		String f = System.getProperty("user.home") + filepath;
		
        Path path = Paths.get(f);
        Scanner in = new Scanner(path);

        graph.edges.clear();
        graph.nodes.clear();
        Node.counter = 0;
        
        int n = in.nextInt(), m = in.nextInt();
        // create N nodes with randomized colors
        for (int i=0;i<n;i++) {
            graph.addNode(String.valueOf(i+1),rand_color(),rand_x(),rand_y());
        }
        for (int i=0;i<m;i++) {
            int u = in.nextInt(), v = in.nextInt();
            int w = 1;
            if (Edge.showWeights) {w = in.nextInt();}
            graph.addEdge(--u,--v,w);
        }
        in.close();
    }
	
	
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		
		if(ac.equals("Add Node")) {
			Color c = Color.green;
			if (!(hex_color.getText().equals(""))) {
				c = Color.decode(hex_color.getText());
			}
			graph.addNode(field.getText(), c, graph.getWidth()/2, graph.getHeight()/2);
			field.setText("");
			hex_color.setText("");
		} else if (ac.equals("Delete Node")) {
			
			for(int i = 0; i < graph.edges.size(); i++) {
				int aIndex = graph.edges.get(i).getAIndex();
				int bIndex = graph.edges.get(i).getBIndex();
				
				if(graph.nodes.get(aIndex).getLabel().equals(field.getText()) 
						|| graph.nodes.get(bIndex).getLabel().equals(field.getText())) {
					graph.edges.remove(i);
					i--;
				}
			}
			
			for(int i = 0; i < graph.nodes.size(); i++) {
				if(graph.nodes.get(i).getLabel().equals(field.getText())) {
					graph.nodes.remove(i);
					break;
				}
			}
			
			for(int i = 0; i < graph.nodes.size(); i++) {
				graph.nodes.get(i).index = i;
			}
			
			field.setText("");
			
			
		} else if (ac.equals("Add Edge")) {
			int u = -1, v = -1;
			for (int i=0;i<graph.nodes.size();i++) {
				Node node = graph.nodes.get(i);
				if (node.getLabel().equals(n1.getText())) {
					u = i;
				}
				if (node.getLabel().equals(n2.getText())) {
					v = i;
				}
			}
			int w = 1;
			if (!(weight_tb.getText().equals(""))) {w = Integer.valueOf(weight_tb.getText());}
			n2.setText(""); n1.setText(""); weight_tb.setText("");
			if (Math.min(u,v)==-1) {return;}
			graph.addEdge(u, v, w);
		} else if(ac.equals("Delete Edge")){ 
			algs = new Algs(graph);
			algs.dehighlightAll();
			
			for(int i = 0; i < graph.edges.size(); i++) {
				
				int aIndex = graph.edges.get(i).getAIndex();
				int bIndex = graph.edges.get(i).getBIndex();
				if(!graph.edges.get(i).isDirected()) {
					if(graph.nodes.get(aIndex).getLabel().equals(n1.getText()) && graph.nodes.get(bIndex).getLabel().equals(n2.getText())
							|| graph.nodes.get(bIndex).getLabel().equals(n1.getText()) && graph.nodes.get(aIndex).getLabel().equals(n2.getText())) {
						graph.edges.remove(i);
						break;
					}
				} else {
					if(graph.nodes.get(aIndex).getLabel().equals(n1.getText()) && graph.nodes.get(bIndex).getLabel().equals(n2.getText())) {
						graph.edges.remove(i);
						break;
					}
				}
				
			}
			
			n1.setText(""); n2.setText("");
			weight_tb.setText("");
			
			
		} else if (ac.equals("import")) {
			
			try {
				read_graph(file_name.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
			}
			
			file_name.setText("");
			
		} else if (ac.equals("rand undir")) {
			graph.nodes.clear();
			graph.edges.clear();
			Node.counter = 0;
			
			int n = Integer.parseInt(rndir_n.getText()), m = Integer.parseInt(rndir_m.getText());
			if(m > n*(n-1)/2) {
				m = n*(n-1)/2;
				rndir_m.setText(String.valueOf(m));
			}
			//rndir_n.setText(""); rndir_m.setText("");
			
			for (int i=0;i<n;i++) {
				//int xp = (i%10* 70)+70, yp = (i/10*150)+150;
				Color c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
				graph.addNode(String.valueOf(i+1), c, (int) (Math.random() * 800 + 10), (int) (Math.random() * 800 + 10));
			}
			
			ArrayList<Pair> edges1 =  new ArrayList<Pair>();
			for (int i=0;i<n;i++) {
				for (int j=i+1;j<n;j++) {
					edges1.add(new Pair(i,j));
					if (Edge.directEdge) {edges1.add(new Pair(j,i));}
				}
			}
			Collections.shuffle(edges1);
			for (int i=0;i<m;i++) {
				graph.addEdge(edges1.get(i).f,edges1.get(i).s);
			}
			
		} else if (ac.equals("start alg")) {
			//System.out.println("alg started\n");
			algs = new Algs(graph);
			String val = (String)dropdown.getSelectedItem();
			//graph.repaint();
			
			if (val.equals("BFS")) {
				res.setText(String.valueOf(algs.bfs(get_node(f_n1.getText()),get_node(f_n2.getText()))));
				res.setSize(res.getPreferredSize());
			} else if (val.equals("Dijkstra")) {
				res.setText(String.valueOf(algs.dijkstra(get_node(f_n1.getText()), get_node(f_n2.getText()))));
				res.setSize(res.getPreferredSize());
			} else if (val.equals("Hopcroft-Karp")) {
				res.setText(String.valueOf(algs.hopcroft_karp()));
				res.setSize(res.getPreferredSize());
			} else if(val.equals("Find Component")) {
				algs.find_comp_dfs(get_node(f_n1.getText()));
			} else if(val.equals("Check DAG")) {
				res.setText(String.valueOf(algs.check_dag()));
				res.setSize(res.getPreferredSize());
			}
			
			f_n1.setText("");
			f_n2.setText("");
			
		} else if (ac.equals("stop alg")) {
			algs.dehighlightAll();
		} else if (ac.equals("print")) {
			System.out.println(graph);
		} else if (ac.equals("clear")) {
			graph.edges.clear();
			graph.nodes.clear();
			Node.counter = 0;
		}
		graph.repaint();
	}

	
	

}

class Pair implements Comparable<Pair> {
	int f, s;
	public Pair(int f, int s) {
		this.f = f; this.s = s;
	}

	public int compareTo(Pair o) {
		return this.f-o.f;
	}
}
