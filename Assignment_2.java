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

public class HuffmanCodings {
    // Function to build the Huffman tree
    public static HuffmanNode buildHuffmanTree(String data) {
        Map<Character, Integer> freqMap = new HashMap<>();

        // Calculate the frequency of each character in the input data
        for (char c : data.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Create a priority queue (min heap) to store Huffman nodes
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();

        // Initialize the priority queue with leaf nodes (character and frequency)
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            minHeap.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman tree by merging nodes with the lowest frequencies
        while (minHeap.size() > 1) {
            HuffmanNode leftNode = minHeap.poll();
            HuffmanNode rightNode = minHeap.poll();
            HuffmanNode mergedNode = new HuffmanNode(null, leftNode.freq + rightNode.freq);
            mergedNode.left = leftNode;
            mergedNode.right = rightNode;
            minHeap.add(mergedNode);
        }

        // Return the root of the Huffman tree
        return minHeap.poll();
    }

    // Function to build Huffman codes for each character
    public static void buildHuffmanCodes(HuffmanNode node, String currentCode, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.data != null) {
            // If it's a leaf node, store the Huffman code for the character
            huffmanCodes.put(node.data, currentCode);
        }

        // Recursively build Huffman codes for left and right subtrees
        buildHuffmanCodes(node.left, currentCode + "0", huffmanCodes);
        buildHuffmanCodes(node.right, currentCode + "1", huffmanCodes);
    }

    // Function to encode the input data using Huffman codes
    public static Map<Character, String> huffmanEncoding(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        // Build the Huffman tree
        HuffmanNode root = buildHuffmanTree(data);

        // Create a map to store Huffman codes for each character
        Map<Character, String> huffmanCodes = new HashMap<>();

        // Build Huffman codes and store them in the map
        buildHuffmanCodes(root, "", huffmanCodes);

        // Return the map of Huffman codes
        return huffmanCodes;
    }

    // Function to decode the encoded data using the Huffman tree
    public static String huffmanDecoding(String encodedData, HuffmanNode root) {
        if (encodedData == null || encodedData.isEmpty()) {
            return null;
        }

        StringBuilder decodedData = new StringBuilder();
        HuffmanNode current = root;

        // Traverse the Huffman tree to decode the encoded data
        for (char bit : encodedData.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.data != null) {
                // If a leaf node is reached, append the character to the decoded data
                decodedData.append(current.data);
                current = root;
            }
        }

        // Return the decoded data
        return decodedData.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the data to encode: ");
        String data = scanner.nextLine();
        scanner.close();

        // Generate Huffman codes for each character in the input data
        Map<Character, String> huffmanCodes = huffmanEncoding(data);

        System.out.println("Original data: " + data);
        System.out.println("Huffman Codes:");

        // Print the Huffman codes for each character
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Encode the input data using Huffman codes
        StringBuilder encodedData = new StringBuilder();
        for (char c : data.toCharArray()) {
            encodedData.append(huffmanCodes.get(c));
        }
        System.out.println("Encoded data: " + encodedData.toString());
    }
}
