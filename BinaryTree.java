// Claire Lindstrom Binary Tree 

import java.util.List;
import java.util.Stack;
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

    public boolean isBalanced() {
        return isBalancedHelper(root) != -1; // Call the helper method with the root node
    }

    private int isBalancedHelper(Node node) {
        if (node == null) {
            return 0; // Base case: reached the leaf node
        }

        int leftHeight = isBalancedHelper(node.left);
        if (leftHeight == -1) {
            return -1; // Left subtree is not balanced
        }

        int rightHeight = isBalancedHelper(node.right);
        if (rightHeight == -1) {
            return -1; // Right subtree is not balanced
        }

        // Check if the current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return the height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isFull() {
        return isFullHelper(root);
    }

    private boolean isFullHelper(Node node) {
        // Base case: leaf nodes are always considered full
        if (node == null) {
            return true;
        }

        // Check if the current node has either 0 or 2 children
        if ((node.left == null && node.right != null) || (node.left != null && node.right == null)) {
            return false;
        }

        // Recursively check if both left and right subtrees are full
        return isFullHelper(node.left) && isFullHelper(node.right);
    }

    public boolean isProper() {
        return isProperHelper(root);
    }

    private boolean isProperHelper(Node node) {
        // Base case: an empty tree is considered proper
        if (node == null) {
            return true;
        }

        // Check if the current node has either 0 or 2 children
        if ((node.left == null && node.right != null) || (node.left != null && node.right == null)) {
            return false;
        }

        // Recursively check if both left and right subtrees are proper
        return isProperHelper(node.left) && isProperHelper(node.right);
    }

    public int getHeight() {
        return getHeightHelper(root);
    }

    private int getHeightHelper(Node node) {
        // Base case: an empty tree has height 0
        if (node == null) {
            return 0;
        }

        // Recursively calculate the height of the left and right subtrees
        int leftHeight = getHeightHelper(node.left);
        int rightHeight = getHeightHelper(node.right);

        // Return the maximum height among the left and right subtrees + 1 (for the
        // current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int getNodeCount() {
        return getNodeCountHelper(root);
    }

    private int getNodeCountHelper(Node node) {
        // Base case: an empty tree has 0 nodes
        if (node == null) {
            return 0;
        }

        // Recursively calculate the number of nodes in the left and right subtrees
        int leftNodeCount = getNodeCountHelper(node.left);
        int rightNodeCount = getNodeCountHelper(node.right);

        // Return the total number of nodes by adding the counts of left and right
        // subtrees and 1 for the current node
        return leftNodeCount + rightNodeCount + 1;
    }

    public String inorderTraversal() {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            sb.append(current.value).append(" ");
            current = current.right;
        }
        return sb.toString().trim();
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

    public BinaryTreeGui.TreeNode getRoot() {
        return null;
    }
}
