import java.util.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

class Time implements Comparable<Time> {
	public int start;
	public int finish;

	@Override
	public int compareTo(Time t) {
		Integer f1 = this.finish;
		Integer f2 = t.finish;
		return f1.compareTo(f2);
	}
}

class Item implements Comparable<Item> {
	public double value;
	public double weight;
	public double selPart;

	@Override
	public int compareTo(Item i) {
		Double r1 = this.value / this.weight;
		Double r2 = i.value / i.weight;
		return r2.compareTo(r1);
	}
}

class Edge implements Comparable<Edge> {
	public int weight;
	public String src;
	public String dest;

	@Override
	public int compareTo(Edge e1) {
		Integer ew1 = this.weight;
		Integer ew2 = e1.weight;
		return ew1.compareTo(ew2);
	}
}

class MyGreedy {
	public HashMap<String, ArrayList<Edge> > adjList = new HashMap<>();
	public ArrayList<Edge> e = new ArrayList<>();
	private int n;

	public void addVertex(String label) {
		ArrayList<Edge> edge = new ArrayList<>();
		this.adjList.put(label, edge);
		this.n ++;
	}

	public void addEdge(String src, String dest, int weight) {
		ArrayList<Edge> edges = this.adjList.get(src);
		Edge edge = new Edge();
		edge.src = src;
		edge.dest = dest;
		edge.weight = weight;
		edges.add(edge);
		this.e.add(edge);
		this.adjList.put(src, edges);
	}

	// Using Union/Find method
	private boolean hasCycle(ArrayList<Edge> e) {
		ArrayList<HashSet<String> > disj = new ArrayList<>();
		int size = e.size();
		for(int i = 0; i < size; i ++) {
			Edge edge = e.get(i);
			String src = edge.src, dest = edge.dest;
			int first = -1, second = -1;
			// Find
			int size1 = disj.size();
			for(int j = 0; j < size1; j ++) {
				HashSet<String> temp = disj.get(j);
				if(temp.contains(src))
					first = j;
				if(temp.contains(dest))
					second = j;
			}
			if(first == second && first != -1) 
				return true;
			if(first == second && first == -1) {
				HashSet<String> temp = new HashSet<>();
				temp.add(src);
				temp.add(dest);
				disj.add(temp);
			}
			if(first == -1 && second != -1) {
				HashSet<String> temp = disj.get(second);
				temp.add(src);
				disj.remove(second);
				disj.add(temp);
			}
			if(first != -1 && second == -1) {
				HashSet<String> temp = disj.get(first);
				temp.add(dest);
				disj.remove(first);
				disj.add(temp);
			}
			// Union
			if(first != - 1 && second != -1) {
				HashSet<String> one = disj.get(first);
				HashSet<String> two = disj.get(second);
				Iterator<String> it = two.iterator();
				while(it.hasNext()) {
					String str = it.next();
					one.add(str);
				}
				disj.remove(first);
				disj.remove(second);
				disj.add(one);
			}
		}
		return false;
	}

	public void kruskal() {
		ArrayList<Edge> mst = new ArrayList<>();
		Collections.sort(this.e);
		int size = this.e.size();
		for(int i = 0; i < size; i ++) {
			Edge tEdge = this.e.get(i);
			mst.add(tEdge);
			if(hasCycle(mst)) {
				mst.remove(mst.size() - 1);
			}
		}
		System.out.println("MST using Kruskal's algorithm is -\nEdge\tWeight");
		for(int i = 0; i < mst.size(); i ++)
			System.out.print(mst.get(i).src + "-" + mst.get(i).dest + "\t" + mst.get(i).weight + "\n");
	}

	public static void activitySelection(ArrayList<Time> times) {
		Collections.sort(times);
		ArrayList<Time> selTimes = new ArrayList<>();
		Time pre = times.get(0);
		selTimes.add(pre);
		for(int i = 1; i < times.size(); i ++) {
			Time next = times.get(i);
			if(next.start >= pre.finish) {
				selTimes.add(next);
				pre = next;
			}
		}
		System.out.print("Times of selected activities -\nStart\tFinish\n");
		for(int i = 0; i < selTimes.size(); i ++)
			System.out.print(selTimes.get(i).start + "\t" + selTimes.get(i).finish + "\n");
	}

	public static void fractionalKnapsack(ArrayList<Item> items, double capacity) {
		Collections.sort(items);
		double ws = 0, vs = 0;
		int i = 0;
		for(; i < items.size(); i ++) {
			Item temp = items.get(i);
			if(ws + temp.weight > capacity) {
				temp.selPart = (capacity - ws) / temp.weight;
				vs += temp.value * temp.selPart;
				ws += temp.weight * temp.selPart;
				break;
			} else {
				temp.selPart = 1;
				vs += temp.value;
				ws += temp.weight;
			}
		}
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.FLOOR);
		System.out.print("Selected items are -\nWeight\tValue\tSelected part\n");
		for(int j = 0; j <= i; j ++) {
			Item temp = items.get(j);
			System.out.print(temp.weight + "\t" + temp.value + "\t" + df.format(temp.selPart) + "\n");
		}
		System.out.print("Maximum value: " + df.format(vs) + "\n");
	}
}

public class Greedy {
	public static void main(String[] args) {
		MyGreedy mg = new MyGreedy();
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		while(choice != 2) {
			System.out.print("\n1: Activity Selection\n2: Exit\n3: Fractional Knapsack\n4: Add vertex\n5: Add edge\n6: Kruskal MST\n");
			System.out.print("Enter your choice: ");
			choice = scan.nextInt();
			switch(choice) {
				case 1: {
					System.out.print("Enter number of activities: ");
					int n = scan.nextInt();
					ArrayList<Time> times = new ArrayList<>(n);
					System.out.print("Enter start and finish time of activities -\n");
					for(int i = 0; i < n; i ++) {
						Time temp = new Time();
						temp.start = scan.nextInt();
						temp.finish = scan.nextInt();
						times.add(temp);
					}
					MyGreedy.activitySelection(times);
					break;
				}
				case 2: {
					break;
				}
				case 3: {
					System.out.print("Enter number of items: ");
					int n = scan.nextInt();
					ArrayList<Item> items = new ArrayList<>(n);
					System.out.print("Enter weight and value of items -\n");
					for(int i = 0; i < n; i ++) {
						Item temp = new Item();
						temp.weight = scan.nextDouble();
						temp.value = scan.nextDouble();
						temp.selPart = 0;
						items.add(temp);
					}
					System.out.print("Enter capacity of Knapsack: ");
					double capacity = scan.nextDouble();
					MyGreedy.fractionalKnapsack(items, capacity);
					break;
				}
				case 4: {
					System.out.print("Enter label of vertex: ");
					String label = scan.next();
					mg.addVertex(label);
					System.out.print("Vertex added.\n");
					break;
				}
				case 5: {
					System.out.print("Enter source vertex, destination vertex and weight -\n");
					String src = scan.next();
					String dest = scan.next();
					int weight = scan.nextInt();
					mg.addEdge(src, dest, weight);
					System.out.print("Edge added.\n");		
					break;			
				}
				case 6: {
					mg.kruskal();
					break;
				}
				default: {
					System.out.print("Invalid choice. Try again.\n");
				}
			}
		}
	}
}