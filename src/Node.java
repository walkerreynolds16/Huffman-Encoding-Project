public abstract class Node implements Comparable<Node> {

	protected int weight;

	public Node(int value) {
		this.weight = value;
	}

	public abstract void addBit(String s);

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}

	public int getWeight() {
		return weight;
	}

}
