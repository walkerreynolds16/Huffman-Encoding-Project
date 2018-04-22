public class Leaf extends Node {

	private char symbol;
	private String bitString;

	public Leaf(int value) {
		super(value);
		bitString = "";
	}

	public Leaf(int value, char symbol) {
		super(value);
		this.symbol = symbol;
		bitString = "";
	}

	@Override
	public void addBit(String s) {
		bitString += s;
	}

	public char getSymbol() {
		return symbol;
	}

	public String getBitString() {
		return bitString;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Leaf) {
			return ((Leaf) arg0).getSymbol() == this.getSymbol();
		}
		return false;
	}

	public void setBitString(String bitString) {
		this.bitString = bitString;
	}

}
