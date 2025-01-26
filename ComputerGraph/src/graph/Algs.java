package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Algs {
	
	Panel graph;
	List<List<Pair>> adj;
    int n, m;

	public Algs(Panel graph) {
		this.graph = graph;
		n = graph.nodes.size();
		m = graph.edges.size();
		make_adj();
	}
	
    public void make_adj() {
        adj = new ArrayList<List<Pair>>();
        for (int i=0;i<n;i++) {adj.add(new ArrayList<Pair>());}
        for (int i=0;i<m;i++) {
        	Edge e = graph.edges.get(i);
            adj.get(e.getAIndex()).add(new Pair(e.getBIndex(),i));
            if (!e.isDirected()) {
            	adj.get(e.getBIndex()).add(new Pair(e.getAIndex(),i));
            }
        }
    }
    
    public void dehighlightAll() {
    	for(Edge ed : graph.edges) {
    		ed.dehighlight();
    	}
    }
	
    public void find_comp_dfs(int u) {
    	boolean vis[] = new boolean[n];
    	class Method {
    		public void dfs(int cur) {
    			vis[cur] = true;
    			for (Pair x : adj.get(cur)) {
    				graph.edges.get(x.s).highlight();
    				if (!vis[x.f]) {dfs(x.f);}
    			}
    		}
    	} 
    	Method m = new Method();
    	m.dfs(u);
    }
	
	public int bfs(int u, int v) {
		
        Pair dist[] = new Pair[n];
        for (int i=0;i<n;i++) {
            dist[i] = new Pair(Integer.MAX_VALUE,-1);
        }
        dist[u] = new Pair(0,-1);

        // adjacency list is represented with {to,index}
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(u);
        while (q.size() > 0) {
            int cur = q.poll();
            for (Pair x : adj.get(cur)) {
                if (dist[x.f].f==Integer.MAX_VALUE) {
                    dist[x.f] = new Pair(dist[cur].f+1, x.s);
                    q.add(x.f);
                }
            }
            //System.out.println(cur+1);
        }
        // track back the path from the end node
        if (dist[v].f==Integer.MAX_VALUE) {return Integer.MAX_VALUE;}
        int last = v;
        while (last!=u) {
        	graph.edges.get(dist[last].s).highlight();
        	if (graph.edges.get(dist[last].s).getAIndex()==last) {
        		last = graph.edges.get(dist[last].s).getBIndex();
        	} else {last = graph.edges.get(dist[last].s).getAIndex();}
        }
        
        return dist[v].f;
        
    }
	
	 public int hopcroft_karp() {
        Pair match[] = new Pair[n+1];
        int[] dist = new int[n+1];
        
        for (int i=0;i<=n;i++) {match[i] = new Pair(0,-1);}

        class Methods {
            public boolean bfs_hopcroft() {
	            Queue<Integer> q = new LinkedList<Integer>();
                for (int i=1;i<=n;i++) {
                    if (match[i].f == 0) {
                        dist[i] = 0;
                        q.add(i);
                    } else {dist[i] = Integer.MAX_VALUE;}
                }
                dist[0] = Integer.MAX_VALUE;

                while (q.size()>0) {
                    int cur = q.poll();
                    if(cur != 0) {
                    	for (Pair x : adj.get(cur-1)) {
                    		if (dist[match[x.f+1].f]==Integer.MAX_VALUE) {
                    			dist[match[x.f+1].f] = dist[cur]+1;
                    			q.add(match[x.f+1].f);
                    		}
                    	}
                    }
                }
                return (dist[0]!=Integer.MAX_VALUE);
            }
            public boolean kuhn_hopcroft(int cur) {
                if (cur==0) {return true;}
                for (Pair x : adj.get(cur-1)) {
                    if (dist[match[x.f+1].f]==dist[cur]+1 && kuhn_hopcroft(match[x.f+1].f)) {
                    	//if (x.f+1==1 || cur==1) {System.out.println("no");}
                        match[x.f+1].f = cur;
                        match[cur].f = x.f+1;
                        match[x.f+1].s = x.s;
                        match[cur].s = x.s;
                        return true;
                    }
                } 
                dist[cur] = Integer.MAX_VALUE;
                return false;
            }
        }
        int ctr = 0;
        Methods m = new Methods();
        //System.out.println(match[1]);
        while (m.bfs_hopcroft()) {
            for (int i=1;i<=n;i++) {
                if (match[i].f==0 && m.kuhn_hopcroft(i)) {
                	ctr++;
                }
                //System.out.println("e");
                //System.out.println(match[1]); break;
            }
        }
        for (int i=1;i<=n;i++) {
        	if (match[i].f!=0) {
        		graph.edges.get(match[i].s).highlight();
        	}
        } return ctr;
	 }
	 
	 
	 public int dijkstra(int u, int v) {
		 PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		 // implement comparable for pair

		 Pair dist[] = new Pair[n];
		 for (int i=0;i<n;i++) {dist[i] = new Pair(Integer.MAX_VALUE,-1);}
		 dist[u].f = 0;
		 pq.add(new Pair(0,u));

		 while (pq.size()>0) {
			 Pair cur = pq.poll();
			 if (cur.f!=dist[cur.s].f) {continue;}

			 for (Pair x : adj.get(cur.s)) {
				 int w = graph.edges.get(x.s).getWeight();
				 if (w+cur.f<dist[x.f].f) {
					 dist[x.f].f = w+cur.f;
					 dist[x.f].s = x.s;
					 pq.add(new Pair(dist[x.f].f,x.f));
				 }
			 }
		 }
		 // copy over the bfs path-tracing
	     if (dist[v].f==Integer.MAX_VALUE) {return Integer.MAX_VALUE;}
	     int last = v;
	     while (last!=u) {
	    	 graph.edges.get(dist[last].s).highlight();
	    	 if (graph.edges.get(dist[last].s).getAIndex()==last) {
	    		 last = graph.edges.get(dist[last].s).getBIndex();
	    	 } else {last = graph.edges.get(dist[last].s).getAIndex();}
	     }
	     
	     return dist[v].f;
	 }
	 
	 public boolean check_dag() {
		 ArrayList<Node> top_sort = new ArrayList<Node>();
		 int[] in_deg = new int[n];
		 
		 for(Edge ed : graph.edges) {
			 in_deg[ed.getBIndex()]++;
		 }
		 
		 Queue<Integer> q = new LinkedList<Integer>();
		 
		 for(int i = 0; i < n; i++) {
			 if(in_deg[i] == 0) {
				 q.add(i);
			 }
		 }
		 
		 while(q.size() > 0) {
			 int index = q.poll();
			 top_sort.add(graph.nodes.get(index));
			 for(Pair p : adj.get(index)) {
				 in_deg[p.f]--;
				 if(in_deg[p.f] == 0) {
					 q.add(p.f);
				 }
			 }
		 }
		 
		 return top_sort.size() == n;
	 }
}
