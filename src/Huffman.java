import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Huffman {

	// These fields are used for summary reporting
	private static int totalChars = 0;
	private static int diffChars = 0;
	private static int maxCodeLen = 0;
	private static double aveCodeLen = 0;
	private static int fileLen = 0;
	private static int byteFileLen = 0;
	private static double huffmanReduction = 0;
	private static ArrayList<String> reportList = new ArrayList<>();

	public static void main(String[] args) {
		// call huffmanEncode 4 times
		huffmanEncode(System.getProperty("user.dir") + "/File1.txt");
		printSummaryReport("Summary Report");
		System.out.println("Finished Summary Report...\n");

		huffmanEncode(System.getProperty("user.dir") + "/File2.txt");
		printSummaryReport("Alice in Wonderland");
		System.out.println("Finished Alice in Wonderland...\n");

		huffmanEncode(System.getProperty("user.dir") + "/src/Huffman.java");
		printSummaryReport("Huffman.java");
		System.out.println("Finished Huffman.java...\n");

		huffmanEncode(System.getProperty("user.dir") + "/File4.txt");
		printSummaryReport("A_DEAD String");
		System.out.println("Finished A_DEAD String...\n");

	}

	private static void huffmanEncode(String path) {
		File file = new File(path);
		String str = "";
		try {
			Scanner scan = new Scanner(file);
			scan.useDelimiter("");

			while (scan.hasNext()) {
				str += scan.next();
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		HuffmanCoding hc = new HuffmanCoding(str);

		totalChars = str.length();
		byteFileLen = str.length() * 8;
		diffChars = hc.getUniqueCharacters();
		maxCodeLen = hc.getMaxCodeLen();

		fileLen = hc.getFileLength();
		aveCodeLen = Math.round(((double) fileLen / (double) totalChars) * 100.0) / 100.0;
		huffmanReduction = Math.round((fileLen / (double) byteFileLen) * 10000.0) / 100.0;
		reportList = hc.getReportList();

	}

	private static void printSummaryReport(String title) {
		System.out.println("\n_________" + title + "_________");
		System.out.println("[Total Chars         = " + totalChars + "]");
		System.out.println("[Different Chars     = " + diffChars + "]");
		System.out.println("[Max Code Length     = " + maxCodeLen + "]");
		System.out.println("[Average Code Length = " + aveCodeLen + "]");
		System.out.println("[File Length         = " + fileLen + "]");
		System.out.println("[Byte File Length    = " + byteFileLen + "]");
		System.out.println("[Huffman Reduction Percentage =" + huffmanReduction + "]\n");
		System.out.println("_________Detail Report_________");
		System.out.println("Char\tFreq\tCode");
		for (String s : reportList) {
			System.out.println(s);
		}
	}

}
