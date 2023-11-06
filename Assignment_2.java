import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class HuffmanNode implements Comparable<HuffmanNode> {
    Character data;
    int freq;
    HuffmanNode left, right;

    public HuffmanNode(Character data, int freq) {
        this.data = data;
        this.freq = freq;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.freq - other.freq;
    }
}

public class HuffmanCoding {
    public static HuffmanNode buildHuffmanTree(String data) {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char c : data.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            minHeap.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (minHeap.size() > 1) {
            HuffmanNode leftNode = minHeap.poll();
            HuffmanNode rightNode = minHeap.poll();
            HuffmanNode mergedNode = new HuffmanNode(null, leftNode.freq + rightNode.freq);
            mergedNode.left = leftNode;
            mergedNode.right = rightNode;
            minHeap.add(mergedNode);
        }

        return minHeap.poll();
    }

    public static void buildHuffmanCodes(HuffmanNode node, String currentCode, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.data != null) {
            huffmanCodes.put(node.data, currentCode);
            return;
        }

        buildHuffmanCodes(node.left, currentCode + "0", huffmanCodes);
        buildHuffmanCodes(node.right, currentCode + "1", huffmanCodes);
    }

    public static String huffmanEncoding(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        HuffmanNode root = buildHuffmanTree(data);
        Map<Character, String> huffmanCodes = new HashMap<>();
        buildHuffmanCodes(root, "", huffmanCodes);

        StringBuilder encodedData = new StringBuilder();
        for (char c : data.toCharArray()) {
            encodedData.append(huffmanCodes.get(c));
        }

        return encodedData.toString();
    }

    public static String huffmanDecoding(String encodedData, HuffmanNode root) {
        if (encodedData == null || encodedData.isEmpty()) {
            return null;
        }

        StringBuilder decodedData = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedData.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.data != null) {
                decodedData.append(current.data);
                current = root;
            }
        }

        return decodedData.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the data to encode: ");
        String data = scanner.nextLine();
        scanner.close();

        String encodedData = huffmanEncoding(data);
        System.out.println("Original data: " + data);
        System.out.println("Encoded data: " + encodedData);

        HuffmanNode root = buildHuffmanTree(data);
        String decodedData = huffmanDecoding(encodedData, root);
        System.out.println("Decoded data: " + decodedData);
    }
}
