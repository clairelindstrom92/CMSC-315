// Claire Lindstrom Binary Tree 

import java.util.List;
import java.util.ArrayList;

public class BinaryTree {

    // Static nested Node class
    public static class Node {
        char value;
        Node left;
        Node right;
        int depth; // Unique feature to store the depth of the node

        public Node(char value, int depth) {
            this.value = value;
            this.depth = depth;
            this.left = null;
            this.right = null;
        }
    }

    // BinaryTree class attributes and methods follow here
    private Node root;

    public BinaryTree() {
        root = null;
    }

    public void constructTree(String treeStr) throws InvalidTreeSyntax {
        this.root = constructTreeHelper(treeStr, 0);

    }

    private Node constructTreeHelper(String treeStr, int depth) throws InvalidTreeSyntax {
        // Base condition
        if (treeStr == null || treeStr.length() == 0) {
            return null;
        }

        // Check if the string starts and ends with parentheses
        if (treeStr.charAt(0) != '(' || treeStr.charAt(treeStr.length() - 1) != ')') {
            throw new InvalidTreeSyntax("Invalid tree syntax");
        }

        // Extract the node value which is the second character
        char nodeValue = treeStr.charAt(1);

        // Create a new node with the extracted value and depth
        Node node = new Node(nodeValue, depth);
        System.out.println("Node created with value: " + nodeValue);
        // If there's more to the string, recursively construct left and right subtrees
        if (treeStr.length() > 3) {
            String innerStr = treeStr.substring(2, treeStr.length() - 1); // Remove outer parentheses and node value
            List<String> subtrees = splitSubtrees(innerStr);
            if (subtrees.size() > 0) {
                node.left = constructTreeHelper(subtrees.get(0), depth + 1);
            }
            if (subtrees.size() > 1) {
                node.right = constructTreeHelper(subtrees.get(1), depth + 1);
            }
        }

        return node;
    }

    private List<String> splitSubtrees(String str) throws InvalidTreeSyntax {
        List<String> subtrees = new ArrayList<>();
        int openParentheses = 0;
        int lastSplit = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                openParentheses++;
            } else if (str.charAt(i) == ')') {
                openParentheses--;
                if (openParentheses == 0) {
                    subtrees.add(str.substring(lastSplit, i + 1));
                    lastSplit = i + 1;
                }
            }
        }

        if (openParentheses != 0) {
            throw new InvalidTreeSyntax("Mismatched parentheses");
        }

        return subtrees;
    }
}
