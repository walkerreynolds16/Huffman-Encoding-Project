import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCoding {

	private String originalString;
	private PriorityQueue<Node> priorityQueue;
	private HashMap<Character, String> hashyBoi;
	private String reducedBitString;
	private ArrayList<Leaf> leafList;
	private ArrayList<String> reportList;
	private ArrayList<Node> tempPrio;

	public HuffmanCoding(String string) {
		this.originalString = string;
		hashyBoi = new HashMap<>();
		reducedBitString = "";
		leafList = new ArrayList<>();
		reportList = new ArrayList<>();
		tempPrio = new ArrayList<>();

		System.out.println("Filling Priority Queue...");
		fillPriorityQueue();

		System.out.println("Filling Leaf List");
		fillLeafList();

		System.out.println("Filling Encoding Tree...");
		formEncodeTree();

		// Fills hash map recursively rather than iterative, Slower
		// fillHashMapRecursive();

		System.out.println("Filling Hash Map...");
		fillHashMapI();

		System.out.println("Filling Report List...");
		fillReportList();

	}

	private void fillPriorityQueue() {

		Scanner scan = new Scanner(originalString);
		scan.useDelimiter("");

		while (scan.hasNext()) {
			String temp = scan.next();
			Leaf newLeaf = new Leaf(1, temp.charAt(0));

			if (!tempPrio.contains(newLeaf)) {
				tempPrio.add(newLeaf);
			} else {
				Leaf tempLeaf = (Leaf) tempPrio.get(tempPrio.indexOf(newLeaf));
				tempLeaf.weight++;
			}
		}

		priorityQueue = new PriorityQueue<>(tempPrio);

	}

	private void formEncodeTree() {
		while (priorityQueue.size() > 1) {
			Node left = priorityQueue.remove();
			Node right = priorityQueue.remove();

			int value = left.weight + right.weight;

			InternalNode temp = new InternalNode(value, left, right);

			priorityQueue.add(temp);

		}

		// System.out.println();
	}

	private void fillHashMapRecursive() {
		if (!priorityQueue.isEmpty()) {
			Node node = priorityQueue.peek();
			if (node instanceof InternalNode) {
				Node left = ((InternalNode) node).getLeft();
				Node right = ((InternalNode) node).getRight();
				if (left != null) {
					fillHashMapHelper(left, "0");
				}
				if (right != null) {
					fillHashMapHelper(right, "1");
				}
			}
		}
	}

	private void fillHashMapHelper(Node n, String bitString) {
		if (n instanceof Leaf) {
			hashyBoi.put(((Leaf) n).getSymbol(), bitString);
		} else {
			Node left = ((InternalNode) n).getLeft();
			Node right = ((InternalNode) n).getRight();
			if (left != null) {
				fillHashMapHelper(left, bitString + "0");
			}
			if (right != null) {
				fillHashMapHelper(right, bitString + "1");
			}
		}
	}

	private void fillHashMapI() {
		for (int i = 0; i < tempPrio.size(); i++) {
			Leaf tempLeaf = (Leaf) tempPrio.get(i);
			hashyBoi.put(tempLeaf.getSymbol(), tempLeaf.getBitString());
		}
	}

	private void fillReducedBitString() {
		for (int i = 0; i < originalString.length(); i++) {
			System.out.println(i);
			reducedBitString += hashyBoi.get(originalString.charAt(i));
		}
	}

	public String getReducedBitString() {
		if (reducedBitString.length() == 0) {
			fillReducedBitString();
		}
		return reducedBitString;
	}

	public int getFileLength() {
		int sum = 0;
		for (int i = 0; i < tempPrio.size(); i++) {
			Leaf leaf = (Leaf) tempPrio.get(i);
			sum += leaf.weight * leaf.getBitString().length();
		}

		return sum;
	}

	public int getUniqueCharacters() {
		return hashyBoi.size();
	}

	public int getMaxCodeLen() {
		int max = 0;
		Object[] values = hashyBoi.values().toArray();

		for (int i = 0; i < values.length; i++) {

			if (max < values[i].toString().length()) {
				max = values[i].toString().length();
			}
		}

		return max;
	}

	public double getAvgCodeLen() {
		int sum = 0;
		Object[] values = hashyBoi.values().toArray();

		for (int i = 0; i < values.length; i++) {
			sum += values[i].toString().length();
		}

		return sum / values.length;
	}

	private String decompress() {
		String decompressed = "";

		Node n = priorityQueue.peek();

		String rbString = getReducedBitString();

		for (int i = 0; i < rbString.length(); i++) {
			if (n instanceof Leaf) {
				decompressed += ((Leaf) n).getSymbol();
				n = priorityQueue.peek();
				i--;
			} else {
				if (rbString.charAt(i) == '0') {
					n = ((InternalNode) n).getLeft();
				} else {
					n = ((InternalNode) n).getRight();
				}
			}
		}
		decompressed += ((Leaf) n).getSymbol();
		n = priorityQueue.peek();

		return decompressed;
	}

	private void fillLeafList() {
		for (Node n : priorityQueue) {
			if (n instanceof Leaf) {
				leafList.add((Leaf) n);
			}
		}
		leafList.sort(new Comparator<Leaf>() {
			@Override
			public int compare(Leaf s1, Leaf s2) {
				if (s1.getSymbol() < s2.getSymbol()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
	}

	private void fillReportList() {
		for (int i = 0; i < leafList.size(); i++) {
			reportList.add(leafList.get(i).getSymbol() + ":\t" + leafList.get(i).weight + "\t"
					+ hashyBoi.get(leafList.get(i).getSymbol()));
		}
	}

	public ArrayList<String> getReportList() {
		return reportList;
	}
}
