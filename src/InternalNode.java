public class InternalNode extends Node {

	private Node left, right;

	public InternalNode(int value, Node l, Node r) {
		super(value);
		this.left = l;
		this.right = r;

		left.addBit("0");
		right.addBit("1");

	}

	@Override
	public void addBit(String s) {
		if (left instanceof Leaf) {
			((Leaf) left).setBitString(s + ((Leaf) left).getBitString());
		} else if (left instanceof InternalNode) {
			left.addBit(s);
		}

		if (right instanceof Leaf) {
			((Leaf) right).setBitString(s + ((Leaf) right).getBitString());
		} else if (right instanceof InternalNode) {
			right.addBit(s);
		}

	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

}
